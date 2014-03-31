/**
 * 
 */
package fr.gfi.quiz.dao;

import java.util.List;

import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.QuizzQuestion;
import fr.gfi.quiz.entite.hibernate.QuizzSujet;
import fr.gfi.quiz.entite.hibernate.ReponseCandidat;
import fr.gfi.quiz.json.entite.ChoixQuiz;


public interface QuizDAO extends AbstractDAO{

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
	 * enregistre une réponse cochée par un candidat pour une question au cours
	 * d'un quizz.
	 * 
	 * @param reponseCandidat
	 * @return
	 * @
	 */
	public ReponseCandidat enregistrerReponseCandidat(ReponseCandidat reponseCandidat) ;

	/**
	 * Met à jour un quizz avec la durée passée par un candidat pour répondre
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
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion( List<ChoixQuiz>  listNiveauTypeSujet)
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
	public Quizz getDetailsQuizz(Integer id, List<String> lAssociations) ;

	/**
	 * Récupère la liste des réponses candidats correspondant à un quizz.
	 * 
	 * @param Id
	 * @return
	 * @
	 */
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer Id) ;

	/**
	 * récupération des détails d'un quizz avec les réponses candidats.
	 * 
	 * @param id
	 * @return
	 * @
	 */
	public Quizz getDetailsQuizzAvecReponsesCandidats(Integer id) ;

	/**
	 * récupère les détails des réponses candidats.
	 * 
	 * @return
	 * @
	 */
	public List<ReponseCandidat> getDetailsReponseCandidat() ;

	
	public List<Langage> getListLangage(List<String> lAssociations);}
