package fr.gfi.cmg.QuizzCmg.metier.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.cmg.QuizzCmg.metier.beans.InfoGenerationQuizz;
import fr.gfi.cmg.QuizzCmg.metier.beans.InfoReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.beans.Questionnaire;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.QuestionsNonTrouveesException;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.persistance.service.QuizzPersistenceService;

@Service("quizzBusinessService")
public class QuizzBusinessServiceImpl implements QuizzBusinessService {

	@Resource(name = "quizzPersistenceService")
	private QuizzPersistenceService quizzPersistenceService;

	/**
	 * g�n�re un quizz.
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
		 * on r�cup�re du questionnaire selon le(s) type(s) de sujet et niveau
		 * de difficult�.
		 */
		final List<Question> lQuestions = quizzPersistenceService
				.getListQuestionsByListTypesSujetsAndNiveauQuestion(infoGenerationQuizz
						.getListNiveauTypeSujet());

		/*
		 * on alimente l'entit� quizz
		 */

		if (lQuestions == null || lQuestions.isEmpty()) {
			throw new QuestionsNonTrouveesException(
					" Il n'y a pas de questions correspondant � vos crit�res de g�neration");
		}

		/**
		 * TransactionManager.begin();
		 */

		Quizz quizz = new Quizz();
		quizz.setUser(infoGenerationQuizz.getUser());
		quizz.setDatQuizz(new Date());
		quizz.setLibNomCandidat(infoGenerationQuizz.getPrenomCandidat() + " "
				+ infoGenerationQuizz.getNomCandidat());
		// enregistrement du quizz
		quizz = quizzPersistenceService.enregistreQuizz(quizz);

		// On r�cup�re ici la vraie liste des types de sujet du
		// questionnaire
		// qui peut �tre diff�rente de la liste choisie par l'utilisateur
		// lors de la g�n�ration.
		final List<TypeSujet> lTypesSujetsForQuestionnairePossible = getTypesSujetsForQuestionnaire(lQuestions);

		// on associe l'entit� Quizz � un type de sujet.
		for (TypeSujet typeSujet : lTypesSujetsForQuestionnairePossible) {
			QuizzSujet quizzSujet = new QuizzSujet();
			quizzSujet.setTypeSujet(typeSujet);
			quizzSujet.setQuizz(quizz);
			// enregistrement du quizzSujet
			quizzSujet = quizzPersistenceService
					.enregistrerQuizzSujet(quizzSujet);

		}

		// on associe l'entit� Quizz aux questions pos�es.
		for (Question question : lQuestions) {
			QuizzQuestion quizzQuestion = new QuizzQuestion();
			quizzQuestion.setQuestion(question);
			quizzQuestion.setQuizz(quizz);
			// enregistrement du quizzQuestion
			quizzQuestion = quizzPersistenceService
					.enregistrerQuizzQuestion(quizzQuestion);

		}

		/**
		 * TransactionManager.commit();
		 */

		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setQuizz(quizz);
		questionnaire.setlQuestions(lQuestions);

		return questionnaire;

	}

	/**
	 * persister les r�ponses du candidat faisant un quizz.
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

		// on met � jour un quizz avec la dur�e pass�e par un candidat pour
		// r�pondre

		quizzPersistenceService.enrengistrerDureeQuizz(quizz.getId(), duree);

		final Map<Question, List<Reponse>> mapQuestionsReponses = infoReponsesCandidat
				.getMapQuestionsReponses();

		/*
		 * on enregistre des r�ponses candidats.
		 */
		for (Map.Entry<Question, List<Reponse>> e : mapQuestionsReponses
				.entrySet()) {
			Question questionRepondue = e.getKey();
			List<Reponse> lReponsesCochees = e.getValue();

			/*
			 * Pour chaque question r�pondue, on alimentera la table
			 * "reponse_candidat" autant des fois qu'il ya des r�ponses coch�es
			 * par le candidat.
			 */
			for (Reponse reponse : lReponsesCochees) {
				ReponseCandidat reponseCandidat = new ReponseCandidat(
						questionRepondue, reponse, quizz);
				// alimentation de la table reponse_candidat
				quizzPersistenceService
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
	private List<TypeSujet> getTypesSujetsForQuestionnaire(
			final List<Question> lQuestions) {
		Map<String, TypeSujet> hashMap = new HashMap<String, TypeSujet>();
		for (Question question : lQuestions) {
			hashMap.put(question.getTypeSujet().getLibelle(),
					question.getTypeSujet());
		}

		final List<TypeSujet> lTypesSujetsForQuestionnairePossible = new ArrayList<TypeSujet>(
				hashMap.values());
		return lTypesSujetsForQuestionnairePossible;
	}

	/**
	 * r�cup�re tous les objets quizz ordonn�s par la date de cr�ation.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Quizz> getListQuizzByDate() throws BusinessServiceException {

		final List<Quizz> lQuizz = quizzPersistenceService.getListQuizzByDate();
		return lQuizz;

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Quizz getDetailsQuizz(Integer id) throws BusinessServiceException {

		final Quizz quizz = quizzPersistenceService.getDetailsQuizz(id);
		return quizz;

	}

	/**
	 * retourne les objets r�ponses candidats attach�s � un quizz.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer id)
			throws BusinessServiceException {

		final List<ReponseCandidat> lCandidats = quizzPersistenceService
				.getListReponsesCandidatsByQuizz(id);
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
		quizzPersistenceService.deleteEntity(entity);
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
		 * on r�cup�re du questionnaire selon le(s) type(s) de sujet et niveau
		 * de difficult�.
		 */
		lQuestions = quizzPersistenceService
				.getListQuestionsByListTypesSujetsAndNiveauQuestion(infoGenerationQuizz
						.getListNiveauTypeSujet());

		/*
		 * on alimente l'entit� quizz
		 */

		if (lQuestions == null || lQuestions.isEmpty()) {
			throw new QuestionsNonTrouveesException(
					" Il n'y a pas de questions correspondant � vos crit�res de g�neration");
		}

		return lQuestions;

	}
}
