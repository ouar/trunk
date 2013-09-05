/**
 * 
 */
package fr.gfi.cmg.QuizzCmg.persistance.service;

import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;


/**
 * @author ehabumuremyi-e
 * 
 */
public interface QuizzPersistenceService {

	/**
	 * 
	 * @param quizz
	 * @return
	 * @
	 */
	public Quizz enregistreQuizz(Quizz quizz) ;

	/**
	 * 
	 * @param quizzSujet
	 * @return
	 * @
	 */
	public QuizzSujet enregistrerQuizzSujet(QuizzSujet quizzSujet) ;

	/**
	 * 
	 * @param quizzQuestion
	 * @
	 */
	public QuizzQuestion enregistrerQuizzQuestion(QuizzQuestion quizzQuestion) ;

	/**
	 * enregistre une r�ponse coch�e par un candidat pour une question au cours
	 * d'un quizz.
	 * 
	 * @param reponseCandidat
	 * @return
	 * @
	 */
	public ReponseCandidat enregistrerReponseCandidat(ReponseCandidat reponseCandidat) ;

	/**
	 * Met � jour un quizz avec la dur�e pass�e par un candidat pour r�pondre
	 * aux questions.
	 * 
	 * @param idQuizz
	 * @param duree
	 * @
	 */
	public void enrengistrerDureeQuizz(Integer idQuizz, Integer duree) ;
	
	/**
	 * retourne une liste des questions en fonction du type de sujet et du
	 * niveau de question
	 * @param listNiveauTypeSujet
	 * @return
	 * @
	 */
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion( List<BeanNiveauTypeSujet> listNiveauTypeSujet)
			;

	/**
	 * 
	 * @return
	 * @
	 */
	public List<Quizz> getListQuizzByDate() ;

	/**
	 * 
	 * @param id
	 * @return
	 * @
	 */
	public Quizz getDetailsQuizz(Integer id) ;

	/**
	 * R�cup�re la liste des r�ponses candidats correspondant � un quizz.
	 * 
	 * @param Id
	 * @return
	 * @
	 */
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer Id) ;

	/**
	 * r�cup�ration des d�tails d'un quizz avec les r�ponses candidats.
	 * 
	 * @param id
	 * @return
	 * @
	 */
	public Quizz getDetailsQuizzAvecReponsesCandidats(Integer id) ;

	/**
	 * r�cup�re les d�tails des r�ponses candidats.
	 * 
	 * @return
	 * @
	 */
	public List<ReponseCandidat> getDetailsReponseCandidat() ;

	/**
	 * 
	 * @param entity
	 * @
	 */
	public void deleteEntity(Object entity) ;

}
