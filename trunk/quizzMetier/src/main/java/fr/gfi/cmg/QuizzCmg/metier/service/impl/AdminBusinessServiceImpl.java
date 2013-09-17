package fr.gfi.cmg.QuizzCmg.metier.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.cmg.QuizzCmg.metier.beans.InfoQuestion;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.persistance.service.AdminPersistenceService;

@Service("adminBusinessService")
public class AdminBusinessServiceImpl implements AdminBusinessService {

	@Resource(name = "adminPersistenceService")
	private AdminPersistenceService adminPersistenceService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User getUserByNameAndPwd(String libUser, String password)
			throws BusinessServiceException {

		final User user = adminPersistenceService.getUserByNameAndPwd(libUser,
				password);

		return user;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Integer ajouteQuestion(InfoQuestion infoQuestion)
			throws BusinessServiceException {

		if (infoQuestion == null) {
			throw new BusinessServiceException(
					" L' objet InfoQuestion est null");
		}

		if (infoQuestion.getSujet().getSujetChoisi() == null
				&& infoQuestion.getSujet().getsNouveauSujet() == null) {
			throw new BusinessServiceException(" le type de sujet est null");
		}

		Question question = infoQuestion.getQuestionHibernate();

		Integer id = null;

		/**
		 * TransactionManager.begin();
		 * 
		 */
		// si le type de sujet est à créer
		if (infoQuestion.getSujet().getSujetChoisi() == null) {
			TypeSujet typeSujet = new TypeSujet();
			typeSujet.setLibelle(infoQuestion.getSujet().getsNouveauSujet());
			typeSujet = adminPersistenceService
					.enregistreNouveauSujet(typeSujet);
			question.setTypeSujet(typeSujet);

		}

		id = adminPersistenceService.enregistreQuestion(question);

		Iterator<Reponse> itReponses = question.getReponses().iterator();
		while (itReponses.hasNext()) {
			Reponse reponse = itReponses.next();
			reponse.setQuestion(question);
			adminPersistenceService.enregistreReponse(reponse);
		}

		/**
		 * TransactionManager.commit();
		 * 
		 */

		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<NiveauQuestion> getListNiveauxQuestion()
			throws BusinessServiceException {

		final List<NiveauQuestion> listNiveauQuestions = adminPersistenceService
				.getListNiveauxQuestion();
		return listNiveauQuestions;

	}

	/**
	 * récupères les types de sujets parametrés.
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations) throws BusinessServiceException {

		final List<TypeSujet> lTypeSujets = adminPersistenceService
				.getListTypesSujet(lAssociations);

		return lTypeSujets;

	}

	/**
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> getListUser() throws BusinessServiceException {

		final List<User> lAdmins = adminPersistenceService.getListUser();

		return lAdmins;

	}

	/**
	 * 
	 * @param entity
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteEntity(Object entity) throws BusinessServiceException {

		/**
		 * TransactionManager.begin();
		 * 
		 */
		adminPersistenceService.deleteEntity(entity);
		/**
		 * TransactionManager.commit();
		 */

	}

	/**
	 * 
	 * @return
	 * @throws BusinessServiceException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Question> getAllQuestionsResponses()
			throws BusinessServiceException {

		final List<Question> lQuestions = adminPersistenceService
				.getAllQuestionsResponses();

		return lQuestions;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService#getListLangage()
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Langage> getListLangage(List<String> lAssociations)
			throws BusinessServiceException {

		List<Langage> listLangages = adminPersistenceService
				.getListLangage(lAssociations);
		return listLangages;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void ajouter(Object objet) throws BusinessServiceException {
		if(!(objet instanceof Question)){
			adminPersistenceService.ajouter(objet);
		}
		else {
			adminPersistenceService.ajouter(objet);
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void supprimer(Object object) throws BusinessServiceException {
		adminPersistenceService.supprimer(object);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void modifier(Object object) throws BusinessServiceException {
		adminPersistenceService.modifier(object);
	}

}
