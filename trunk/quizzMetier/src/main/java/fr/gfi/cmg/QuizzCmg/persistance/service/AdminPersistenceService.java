/**
 * 
 */
package fr.gfi.cmg.QuizzCmg.persistance.service;

import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Admin;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;

public interface AdminPersistenceService {

	public Admin getUserByNameAndPwd(String sName, String sPass);

	/**
	 * 
	 * @param question
	 * @return @
	 */
	public Integer enregistreQuestion(Question question);

	/**
	 * r�cup�res les types de sujets parametr�s.
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
	 * r�cup�res les types de sujets parametr�s.
	 * 
	 * @return @
	 */
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations);

	/**
	 * 
	 * @return @
	 */
	public List<Admin> getListAdmin();

	/**
	 * retourne toutes les questions parametr�es (avec des r�ponses parametr�es
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
	 * Renvoie une liste de langage avec leurs associations pass�es en
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
	public void ajouter(Object objet);

	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void supprimer(Object object);

	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void modifier(Object object);

}
