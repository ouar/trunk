package fr.gfi.quiz.metier.service;

import java.util.List;

import fr.gfi.quiz.entite.InfoGenerationQuizz;
import fr.gfi.quiz.entite.InfoReponseCandidat;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.ReponseCandidat;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.StatsQuiz;
import fr.gfi.quiz.metier.exception.BusinessServiceException;
import fr.gfi.quiz.metier.exception.QuestionsNonTrouveesException;



public interface QuizBS {


	/**
	 * génère un questionnaire en fonction du type de sujet et du niveau de
	 * difficulté d'une question.
	 *
	 * @param infoGenerationQuestionnnaire
	 * @return
	 * @throws BusinessServiceException
	 * @throws QuestionsNonTrouveesException
	 */

	public Quizz genererQuizz(InfoGenerationQuizz infoGenerationQuestionnnaire) throws BusinessServiceException, QuestionsNonTrouveesException;

	/**
	 * enregistre les réponses répondues par le candidat à un quizz.
	 *
	 * @param infoReponsesCandidat
	 * @throws BusinessServiceException
	 */
	public void enregistrerReponsesQuizz(InfoReponseCandidat infoReponsesCandidat) throws BusinessServiceException;

	/**
	 * Enregistre les réponses sélectionnées par le candidat
	 * @param quiz
	 */
	public void enregistrerReponsesQuizz(Quiz quiz);
	/**
	 *
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<Quizz> getListQuizzByDate() throws BusinessServiceException;

	/**
	 * récupère le détail d'un quizz.
	 *
	 * @param id
	 * @return
	 * @throws BusinessServiceException
	 */
	public Quizz getDetailsQuizz(Integer id) throws BusinessServiceException;

	/**
	 * Récupère la liste des réponses candidats correspondant à un quizz.
	 *
	 * @param Id
	 * @return
	 * @throws PersistenceServiceException
	 */
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer id) throws BusinessServiceException;

	/**
	 *
	 * @param entity
	 * @throws BusinessServiceException
	 */
	public void deleteWorkEntity(Object entity) throws BusinessServiceException;

	/**
	 *
	 * @param infoGenerationQuizz
	 * @return
	 * @throws BusinessServiceException
	 * @throws QuestionsNonTrouveesException
	 */
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion (InfoGenerationQuizz infoGenerationQuizz) throws BusinessServiceException, QuestionsNonTrouveesException;


	public List<Langage> getListLangage(List<String> lAssociations);

	public StatsQuiz getStatQuiz(int idQuiz);

	public Quiz convertQuizBDtoQuizJson(Quizz quizzBD);
	
	public StatsQuiz convertQuizBDtoStatsJson(Quizz quizzBD);

}
