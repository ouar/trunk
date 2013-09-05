package fr.gfi.cmg.QuizzCmg.metier.service;

import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Admin;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;

public interface AdminBusinessService {

	public Admin getUserByNameAndPwd(String user, String password)
			throws BusinessServiceException;

	

	/**
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<NiveauQuestion> getListNiveauxQuestion()
			throws BusinessServiceException;

	/**
	 * récupères les types de sujets parametrés.
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations) throws BusinessServiceException;

	/**
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<Admin> getListAdmin() throws BusinessServiceException;

	/**
	 * récupère la liste de toutes les questions parametrées contenant aussi des
	 * réponses correspondantes.
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<Question> getAllQuestionsResponses()
			throws BusinessServiceException;

	/**
	 * 
	 * @param entity
	 * @throws BusinessServiceException
	 */
	public void deleteEntity(Object entity) throws BusinessServiceException;

	/**
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	public List<Langage> getListLangage(List<String> lAssociations)
			throws BusinessServiceException;
	
	/**
	 * 
	 * @param objet
	 * @return
	 * @throws BusinessServiceException
	 */
	public void ajouter(Object objet)
			throws BusinessServiceException ;
	
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void supprimer(Object object)
			throws BusinessServiceException;
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws BusinessServiceException
	 */
	public void modifier(Object object)
			throws BusinessServiceException;
}
