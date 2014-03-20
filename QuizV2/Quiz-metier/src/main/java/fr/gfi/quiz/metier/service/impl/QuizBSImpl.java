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
import fr.gfi.quiz.entite.InfoGenerationQuizz;
import fr.gfi.quiz.entite.InfoReponseCandidat;
import fr.gfi.quiz.entite.Questionnaire;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.QuizzQuestion;
import fr.gfi.quiz.entite.hibernate.QuizzSujet;
import fr.gfi.quiz.entite.hibernate.Reponse;
import fr.gfi.quiz.entite.hibernate.ReponseCandidat;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Personne;
import fr.gfi.quiz.json.entite.Quiz;
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
	public Questionnaire genererQuizz(InfoGenerationQuizz infoGenerationQuizz)
			throws BusinessServiceException, QuestionsNonTrouveesException {

		/*
		 * on récupère du questionnaire selon le(s) type(s) de sujet et niveau
		 * de difficulté.
		 */
		final List<Question> lQuestions = quizDAO.getListQuestionsByListTypesSujetsAndNiveauQuestion(infoGenerationQuizz.getlSujetDifficulte());

		/*
		 * on alimente l'entité quizz
		 */

		if (lQuestions == null || lQuestions.isEmpty()) {
			throw new QuestionsNonTrouveesException(
					" Il n'y a pas de questions correspondant à vos critères de géneration");
		}

		/**
		 * TransactionManager.begin();
		 */

		Quizz quizz = new Quizz();
		quizz.setUser(infoGenerationQuizz.getUser());
		quizz.setDatQuizz(new Date());
		quizz.setPrenomCandidat(infoGenerationQuizz.getPrenomCandidat());
		quizz.setNomCandidat(infoGenerationQuizz.getNomCandidat());
		// enregistrement du quizz
		quizz = quizDAO.enregistreQuizz(quizz);

		// On récupère ici la vraie liste des types de sujet du
		// questionnaire
		// qui peut être différente de la liste choisie par l'utilisateur
		// lors de la génération.
		final List<TypeSujet> lTypesSujetsForQuestionnairePossible = getTypesSujetsForQuestionnaire(lQuestions);

		// on associe l'entité Quizz à un type de sujet.
		for (TypeSujet typeSujet : lTypesSujetsForQuestionnairePossible) {
			QuizzSujet quizzSujet = new QuizzSujet();
			quizzSujet.setTypeSujet(typeSujet);
			quizzSujet.setQuizz(quizz);
			// enregistrement du quizzSujet
			quizzSujet = quizDAO.enregistrerQuizzSujet(quizzSujet);

		}

		for (Question question : lQuestions) {
			QuizzQuestion quizzQuestion = new QuizzQuestion();
			quizzQuestion.setQuestion(question);
			quizzQuestion.setQuizz(quizz);
			// enregistrement du quizzQuestion
			quizzQuestion = quizDAO.enregistrerQuizzQuestion(quizzQuestion);

		}


		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setQuizz(quizz);
		questionnaire.setlQuestions(lQuestions);

		return questionnaire;

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

		final Quizz quizz = quizDAO.getDetailsQuizz(id);
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
						.getlSujetDifficulte());

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
			sujet.setId(quizzSujet.getTypeSujet().getId());
			sujet.setLibelle(quizzSujet.getTypeSujet().getLibelle());
			
			IdLibelle difficulte = new IdLibelle();
			
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
			
			IdLibelle typeSujet = new IdLibelle();
			typeSujet.setId(questionBD.getTypeSujet().getId());
			typeSujet.setLibelle(questionBD.getTypeSujet().getLibelle());
			question.setTypeSujet(typeSujet);
			
			IdLibelle langage = new IdLibelle();
			langage.setId(questionBD.getTypeSujet().getLangage().getId());
			langage.setLibelle(questionBD.getTypeSujet().getLangage().getLibelle());
			question.setLangage(langage);
			
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
}
