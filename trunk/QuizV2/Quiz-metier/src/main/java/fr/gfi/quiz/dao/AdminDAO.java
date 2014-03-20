package fr.gfi.quiz.dao;

import java.util.List;

import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.NiveauQuestion;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Reponse;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.entite.hibernate.User;
import fr.gfi.quiz.metier.exception.BusinessServiceException;

public interface AdminDAO extends AbstractDAO{

	public User getUserByNameAndPwd(String sName, String sPass, List<String> lAssociations);

	/**
	 * 
	 * @param question
	 * @return @
	 */
	public Integer enregistreQuestion(Question question);

	/**
	 * récupères les types de sujets parametrés.
	 * 
	 * @param typeSujet
	 * @return @
	 */
	public TypeSujet enregistreNouveauSujet(TypeSujet typeSujet);

	/**
	 * 
	 * @param reponse
	 * @return @
	 */
	public Reponse enregistreReponse(Reponse reponse);

	/**
	 * 
	 * @return @
	 */
	public List<NiveauQuestion> getListNiveauxQuestion();

	/**
	 * récupères les types de sujets parametrés.
	 * 
	 * @return @
	 */
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations);

	/**
	 * 
	 * @return @
	 */
	public List<User> getListUser();

	/**
	 * retourne toutes les questions parametr�es (avec des réponses parametrées
	 * correspondant).
	 * 
	 * @return @
	 */
	public List<Question> getAllQuestionsResponses();

	/**
	 * 
	 * @param entity
	 *            @
	 */
	public void deleteEntity(Object entity);

	/**
	 * Renvoie une liste de langage avec leurs associations passées en
	 * param�tres
	 * 
	 * @param lAssociations
	 * @return @
	 */
	public List<Langage> getListLangage(List<String> lAssociations);

	/**
	 * 
	 * @param objet
	 * @return
	 * @throws BusinessServiceException
	 */
	public void create(Object objet);

	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void delete(Object object);

	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void update(Object object);


}
