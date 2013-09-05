package fr.gfi.cmg.QuizzCmg.metier.service;

import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.beans.InfoGenerationQuizz;
import fr.gfi.cmg.QuizzCmg.metier.beans.InfoReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.beans.Questionnaire;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.QuestionsNonTrouveesException;


public interface QuizzBusinessService  {

	/**
	 * génère un questionnaire en fonction du type de sujet et du niveau de
	 * difficulté d'une question.
	 * 
	 * @param infoGenerationQuestionnnaire
	 * @return
	 * @throws BusinessServiceException
	 * @throws QuestionsNonTrouveesException
	 */

	public Questionnaire genererQuizz(
			InfoGenerationQuizz infoGenerationQuestionnnaire)
			throws BusinessServiceException, QuestionsNonTrouveesException;

	/**
	 * enregistre les réponses répondues par le candidat à un quizz.
	 * 
	 * @param infoReponsesCandidat
	 * @throws BusinessServiceException
	 */
	public void enregistrerReponsesQuizz(InfoReponseCandidat infoReponsesCandidat) throws BusinessServiceException;

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

}
