package fr.gfi.quiz.metier.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.AdminDAO;
import fr.gfi.quiz.dao.QuizDAO;
import fr.gfi.quiz.dao.utils.HibConst;
import fr.gfi.quiz.entite.InfoGenerationQuizz;
import fr.gfi.quiz.entite.InfoReponseCandidat;
import fr.gfi.quiz.entite.PairInt;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.QuizzQuestion;
import fr.gfi.quiz.entite.hibernate.QuizzSujet;
import fr.gfi.quiz.entite.hibernate.Reponse;
import fr.gfi.quiz.entite.hibernate.ReponseCandidat;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.entite.hibernate.TypeSujetId;
import fr.gfi.quiz.json.entite.ChoixQuiz;
import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Personne;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.StatsLangage;
import fr.gfi.quiz.json.entite.StatsQuestion;
import fr.gfi.quiz.json.entite.StatsQuiz;
import fr.gfi.quiz.json.entite.StatsSujet;
import fr.gfi.quiz.json.entite.TypeQuestion;
import fr.gfi.quiz.metier.exception.BusinessServiceException;
import fr.gfi.quiz.metier.exception.QuestionsNonTrouveesException;
import fr.gfi.quiz.metier.service.QuizBS;

@Service("quizBS")
public class QuizBSImpl implements QuizBS {


	@Resource(name = "quizDAO")
	private QuizDAO quizDAO;

	@Resource(name="adminDAO")
	private AdminDAO adminDAO;



	/**
	 * génère un quizz.
	 *
	 * @param infoGenerationQuestionnnaire
	 * @return
	 * @throws BusinessServiceException
	 * @throws QuestionsNonTrouveesException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Quizz genererQuizz(InfoGenerationQuizz infoGenerationQuizz)
			throws BusinessServiceException, QuestionsNonTrouveesException {

		/*
		 * on récupère du questionnaire selon le(s) type(s) de sujet et niveau
		 * de difficulté.
		 */
		final List<Question> lQuestions = quizDAO.getListQuestionsByListTypesSujetsAndNiveauQuestion(infoGenerationQuizz.getlChoix());

		/*
		 * on alimente l'entité quizz
		 */

		if (lQuestions == null || lQuestions.isEmpty()) {
			throw new QuestionsNonTrouveesException(
					" Il n'y a pas de questions correspondant à vos critères de géneration");
		}


		Quizz quizz = new Quizz();
		quizz.setUser(infoGenerationQuizz.getUser());
		quizz.setDatQuizz(new Date());
		quizz.setPrenomCandidat(infoGenerationQuizz.getPrenomCandidat());
		quizz.setNomCandidat(infoGenerationQuizz.getNomCandidat());


		//enregistrement du quizz
		quizz = quizDAO.enregistreQuizz(quizz);

		//enregistrement des sujets et niveaux de difficultés choisis
		for(ChoixQuiz choix : infoGenerationQuizz.getlChoix()){
			QuizzSujet sujetDifficulteBD = new QuizzSujet();
			sujetDifficulteBD.setQuizz(quizz);

			TypeSujetId sujetId = new TypeSujetId();
			sujetId.setId(choix.getSujet().getId());
			sujetId.setRefDifficulte(choix.getDifficulte().getId());
			TypeSujet sujet = new TypeSujet();
			sujet.setId(sujetId);
			sujetDifficulteBD.setTypeSujet(sujet);

			quizDAO.enregistrerQuizzSujet(sujetDifficulteBD);
		}

		//enregistrement des questions qui seront posées
		for (Question question : lQuestions) {
			QuizzQuestion quizzQuestion = new QuizzQuestion();
			quizzQuestion.setQuizz(quizz);
			quizzQuestion.setQuestion(question);

			quizDAO.enregistrerQuizzQuestion(quizzQuestion);
		}


		return quizz;

	}

	/**
	 * persister les réponses du candidat faisant un quizz.
	 *
	 * @param infoReponsesCandidat
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void enregistrerReponsesQuizz(
			InfoReponseCandidat infoReponsesCandidat)
			throws BusinessServiceException {

		/**
		 * TransactionManager.begin();
		 */

		Quizz quizz = infoReponsesCandidat.getQuizz();
		int duree = (int) (new Date().getTime() - quizz.getDatQuizz().getTime());

		// on met à jour un quizz avec la durée passée par un candidat pour
		// répondre

		quizDAO.enrengistrerDureeQuizz(quizz.getId(), duree);

		final Map<Question, List<Reponse>> mapQuestionsReponses = infoReponsesCandidat
				.getMapQuestionsReponses();

		/*
		 * on enregistre des réponses candidats.
		 */
		for (Map.Entry<Question, List<Reponse>> e : mapQuestionsReponses
				.entrySet()) {
			Question questionRepondue = e.getKey();
			List<Reponse> lReponsesCochees = e.getValue();

			/*
			 * Pour chaque question répondue, on alimentera la table
			 * "reponse_candidat" autant des fois qu'il ya des réponses cochées
			 * par le candidat.
			 */
			for (Reponse reponse : lReponsesCochees) {
				ReponseCandidat reponseCandidat = new ReponseCandidat(
						questionRepondue, reponse, quizz);
				// alimentation de la table reponse_candidat
				quizDAO
						.enregistrerReponseCandidat(reponseCandidat);
			}
		}

		/**
		 * TransactionManager.commit();
		 */

	}

	/**
	 *
	 * @param lQuestions
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	private List<TypeSujet> getTypesSujetsForQuestionnaire(final List<Question> lQuestions) {
		Map<String, TypeSujet> hashMap = new HashMap<String, TypeSujet>();
		for (Question question : lQuestions) {
			hashMap.put(question.getTypeSujet().getLibelle(), question.getTypeSujet());
		}

		final List<TypeSujet> lTypesSujetsForQuestionnairePossible = new ArrayList<TypeSujet>(hashMap.values());
		return lTypesSujetsForQuestionnairePossible;
	}

	/**
	 * récupère tous les objets quizz ordonnés par la date de création.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Quizz> getListQuizzByDate() throws BusinessServiceException {

		final List<Quizz> lQuizz = quizDAO.getListQuizzByDate();
		return lQuizz;

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Quizz getDetailsQuizz(Integer id) throws BusinessServiceException {
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.QuizzEnum.LangageSujet.getValue());
		lAssociations.add(HibConst.QuizzEnum.DifficulteSujet.getValue());
		lAssociations.add(HibConst.QuizzEnum.User.getValue());
		lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		lAssociations.add(HibConst.QuizzEnum.DifficulteQuestion.getValue());
		lAssociations.add(HibConst.QuizzEnum.ReponsesCandidat.getValue());

		final Quizz quizz = quizDAO.getDetailsQuizz(id,lAssociations);
		return quizz;

	}

	/**
	 * retourne les objets réponses candidats attachés à un quizz.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer id)
			throws BusinessServiceException {

		final List<ReponseCandidat> lCandidats = quizDAO.getListReponsesCandidatsByQuizz(id);
		return lCandidats;

	}

	/**
	 *
	 * @param entity
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteWorkEntity(Object entity) throws BusinessServiceException {

		/**
		 * TransactionManager.begin();
		 */
		quizDAO.delete(entity);
		/**
		 * TransactionManager.commit();
		 */

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion(
			InfoGenerationQuizz infoGenerationQuizz)
			throws BusinessServiceException, QuestionsNonTrouveesException {
		List<Question> lQuestions = null;

		/*
		 * on récupère du questionnaire selon le(s) type(s) de sujet et niveau
		 * de difficulté.
		 */
		lQuestions = quizDAO
				.getListQuestionsByListTypesSujetsAndNiveauQuestion(infoGenerationQuizz
						.getlChoix());

		/*
		 * on alimente l'entité quizz
		 */

		if (lQuestions == null || lQuestions.isEmpty()) {
			throw new QuestionsNonTrouveesException(
					" Il n'y a pas de questions correspondant � vos crit�res de g�neration");
		}

		return lQuestions;

	}

	@Override
	public Quiz convertQuizBDtoQuizJson(Quizz quizzBD) {
		Quiz quiz = new Quiz();

		quiz.setId(quizzBD.getId());

		Personne examinateur = new Personne();
		examinateur.setId(quizzBD.getUser().getId());
		examinateur.setNom(quizzBD.getUser().getNom());
		examinateur.setPrenom(quizzBD.getUser().getPrenom());
		quiz.setExaminateur(examinateur);

		Personne candidat = new Personne();
		candidat.setPrenom(quizzBD.getPrenomCandidat());
		candidat.setNom(quizzBD.getNomCandidat());
		quiz.setCandidat(candidat);

		List<TypeQuestion> lTypesQuestions = new ArrayList<TypeQuestion>();
		for(QuizzSujet quizzSujet : quizzBD.getQuizzSujets()){
			TypeQuestion typeQuestion = new TypeQuestion();

			IdLibelle sujet = new IdLibelle();
			sujet.setId(quizzSujet.getTypeSujet().getId().getId());
			sujet.setLibelle(quizzSujet.getTypeSujet().getLibelle());

			IdLibelle difficulte = new IdLibelle();
			difficulte.setId(quizzSujet.getTypeSujet().getDifficulte().getId());
			difficulte.setLibelle(quizzSujet.getTypeSujet().getDifficulte().getLibDifficulte());

			IdLibelle langage = new IdLibelle();
			langage.setId(quizzSujet.getTypeSujet().getLangage().getId());
			langage.setLibelle(quizzSujet.getTypeSujet().getLangage().getLibelle());

			typeQuestion.setDifficulte(difficulte);
			typeQuestion.setLangage(langage);
			typeQuestion.setTypeSujet(sujet);

			lTypesQuestions.add(typeQuestion);
		}
		quiz.setLTypesQuestions(lTypesQuestions);

		List<fr.gfi.quiz.json.entite.Question> lQuestions = new ArrayList<fr.gfi.quiz.json.entite.Question>();
		for(QuizzQuestion quizzQuestion : quizzBD.getQuizzQuestions()){
			fr.gfi.quiz.json.entite.Question question = new fr.gfi.quiz.json.entite.Question();
			Question questionBD = quizzQuestion.getQuestion();
			question.setId(questionBD.getId());
			question.setDureeReflexionEnSec(questionBD.getIntDureeReflexion());
			question.setLibelle(questionBD.getLibQuestion());
			question.setUniqueReponseCorrecte(questionBD.getBolUniqueReponse());

			IdLibelle langage = new IdLibelle();
			langage.setId(questionBD.getTypeSujet().getLangage().getId());
			langage.setLibelle(questionBD.getTypeSujet().getLangage().getLibelle());
			question.setLangage(langage);

			IdLibelle typeSujet = new IdLibelle();
			typeSujet.setId(questionBD.getTypeSujet().getId().getId());
			typeSujet.setLibelle(questionBD.getTypeSujet().getLibelle());
			question.setTypeSujet(typeSujet);

			IdLibelle difficulte = new IdLibelle();
			difficulte.setId(questionBD.getTypeSujet().getDifficulte().getId());
			difficulte.setLibelle(questionBD.getTypeSujet().getDifficulte().getLibDifficulte());
			question.setDifficulte(difficulte);

			if(StringUtils.isNotEmpty(questionBD.getUrlImage())){
				question.setUrlInmage(questionBD.getUrlImage());
			}

			List<fr.gfi.quiz.json.entite.Reponse> lReponses = new ArrayList<fr.gfi.quiz.json.entite.Reponse>();
			for(Reponse reponseBD : questionBD.getReponses()){
				fr.gfi.quiz.json.entite.Reponse reponse = new fr.gfi.quiz.json.entite.Reponse();

				IdLibelle reponseIdLib = new IdLibelle();
				reponseIdLib.setId(reponseBD.getId());
				reponseIdLib.setLibelle(reponseBD.getLibReponse());
				reponse.setReponse(reponseIdLib);
				reponse.setCorrecte(reponseBD.getBolTypeReponse());

				lReponses.add(reponse);
			}
			question.setlReponses(lReponses);
			lQuestions.add(question);
		}
		quiz.setLQuestions(lQuestions);

		return quiz;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Langage> getListLangage(List<String> lAssociations){
		List<Langage> listLangages = quizDAO.getListLangage(lAssociations);
		return listLangages;
	}


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void enregistrerReponsesQuizz(Quiz quiz) {
		for(fr.gfi.quiz.json.entite.Question question : quiz.getLQuestions()){

			if(question.isRepondue() == true){
				for(fr.gfi.quiz.json.entite.Reponse reponse : question.getlReponses()){

					if(reponse.isChoisie() != null && reponse.isChoisie() == true){
						ReponseCandidat reponseChoisie = new ReponseCandidat();

						Quizz quizBD = new Quizz();
						quizBD.setId(quiz.getId());

						Question questionBD = new Question();
						questionBD.setId(question.getId());

						Reponse reponseBD = new Reponse();
						reponseBD.setId(reponse.getReponse().getId());

						reponseChoisie.setQuizz(quizBD);
						reponseChoisie.setQuestion(questionBD);
						reponseChoisie.setReponse(reponseBD);

						quizDAO.enregistrerReponseCandidat(reponseChoisie);
					}
				}
			}
		}
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public StatsQuiz getStatQuiz(int idQuiz) {

		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.QuizzEnum.LangageQuestion.getValue());
		lAssociations.add(HibConst.QuizzEnum.DifficulteQuestion.getValue());
		lAssociations.add(HibConst.QuizzEnum.ReponsesCandidatAvecQuestions.getValue());
		lAssociations.add(HibConst.QuizzEnum.User.getValue());

		Quizz quiz = quizDAO.getDetailsQuizzFini(idQuiz,lAssociations);
		return convertQuizBDtoStatsJson(quiz);
	}

	@Override
	public StatsQuiz convertQuizBDtoStatsJson(Quizz quizzBD) {
		Map<Integer,StatsLangage> mStatsLangage = new HashMap<Integer, StatsLangage>();
		Map<TypeSujetId,StatsSujet> mStatsSujet = new HashMap<TypeSujetId, StatsSujet>();

		StatsQuiz statsQuiz = new StatsQuiz();
		statsQuiz.setCandidat(new Personne(0,quizzBD.getPrenomCandidat(),quizzBD.getNomCandidat()));
		statsQuiz.setExaminateur(new Personne(quizzBD.getUser().getId(),quizzBD.getUser().getPrenom(),quizzBD.getUser().getNom()));
		if(quizzBD.getIntDuree()!=null){
			statsQuiz.setDureePrevue(quizzBD.getIntDuree());
		}
		statsQuiz.setDate(quizzBD.getDatQuizz());

		for(QuizzQuestion quizQuestion : quizzBD.getQuizzQuestions()){
			Question question = quizQuestion.getQuestion();
			StatsLangage statsLangage = null;
			StatsSujet statsSujet = null;

			//on récupère le langage et le sujet de la question à traiter
			Langage langageBD = question.getTypeSujet().getLangage();
			TypeSujet typeSujetBD = question.getTypeSujet();

			//on vérifie que le langage a déjà été traité dans la boucle
			if(mStatsLangage.containsKey(langageBD.getId())){
				statsLangage = mStatsLangage.get(langageBD.getId());
			}else{
				statsLangage = new StatsLangage();
				statsLangage.setLangage(new IdLibelle(langageBD.getId(), langageBD.getLibelle()));
				mStatsLangage.put(langageBD.getId(), statsLangage);
			}
			//on vérifie que le sujet est déjà dans le langage
			PairInt sujetId = new PairInt(typeSujetBD.getId().getId(),typeSujetBD.getId().getRefDifficulte());
			if(statsLangage.getmSujets().containsKey(sujetId)){
				statsSujet = statsLangage.getmSujets().get(sujetId);
			}else{
				statsSujet = new StatsSujet();
				statsSujet.setSujet(new IdLibelle(typeSujetBD.getId().getId(), typeSujetBD.getLibelle()));
				statsSujet.setDifficulte(new IdLibelle(typeSujetBD.getDifficulte().getId(), typeSujetBD.getDifficulte().getLibDifficulte()));
				mStatsSujet.put(typeSujetBD.getId(), statsSujet);
				statsLangage.getmSujets().put(sujetId, statsSujet);
			}

			int nbReponsesNonTrouvees = 0;
			int nbBonnesReponses = 0;
			int nbMauvaisesReponses = 0;
			for(Reponse reponse: question.getReponses()){
				if(reponse.getBolTypeReponse() == Boolean.TRUE){
					if(reponse.getReponseCandidats().size()>0){
						nbBonnesReponses++;
					}else{
						nbReponsesNonTrouvees++;
					}
				}else{
					if(reponse.getReponseCandidats().size()>0){
						nbMauvaisesReponses++;
					}
//					else{
//						nbBonneReponse++;
//					}
				}
			}
			StatsQuestion statsQuestion = new StatsQuestion(typeSujetBD.getDifficulte().getId(),
					(nbReponsesNonTrouvees>0 || nbMauvaisesReponses>0)?0:typeSujetBD.getDifficulte().getId(),
					nbBonnesReponses,
					nbMauvaisesReponses,
					nbReponsesNonTrouvees);
			statsSujet.addStatsQuestion(statsQuestion);
		}
		//Les statistiques du quiz ont été effectuées au niveau Question et sujet.
		//on doit mettre à jour le quiz qui mettra à jour les langages
		//en se basant sur les statistiques des sujets
		List<StatsLangage> lStatsLangage = new ArrayList<StatsLangage>(mStatsLangage.size());
		for(Integer mapKey : mStatsLangage.keySet()){
			lStatsLangage.add(mStatsLangage.get(mapKey));
		}
		statsQuiz.setlStatsLangages(lStatsLangage);
		statsQuiz.process();

		return statsQuiz;
	}


}
