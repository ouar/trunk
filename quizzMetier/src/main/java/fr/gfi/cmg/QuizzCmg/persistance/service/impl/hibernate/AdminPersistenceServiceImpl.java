/**
 * 
 */
package fr.gfi.cmg.QuizzCmg.persistance.service.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Admin;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.persistance.service.AdminPersistenceService;
import fr.gfi.cmg.QuizzCmg.persistance.util.HibConst;
import fr.gfi.cmg.QuizzCmg.persistance.util.MyRequest;

@Repository("adminPersistenceService")
public class AdminPersistenceServiceImpl implements AdminPersistenceService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Admin getUserByNameAndPwd(String sName, String sPass) {
		Session hSession = null;
		Admin admin = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Admin.class);
		criteres.add(Restrictions.eq("libNom", sName));
		criteres.add(Restrictions.eq("libPassword", sPass));

		admin = (Admin) criteres.uniqueResult();

		return admin;
	}

	/**
	 * enregistre une question dans la table param�tre.
	 * 
	 * @param question
	 * @return @
	 */

	public Integer enregistreQuestion(Question question) {

		Session hSession = null;
		Integer id = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(question);
		id = question.getId();

		return id;
	}

	/**
	 * r�cup�re la liste des niveaux de difficult�s (des questions) parametr�s.
	 * 
	 * @return @
	 */
	public List<NiveauQuestion> getListNiveauxQuestion() {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<NiveauQuestion> listNiveauDifficultes = hSession.createCriteria(NiveauQuestion.class).list();
		return listNiveauDifficultes;

	}

	/**
	 * r�cup�res les types de sujets parametr�s.
	 * 
	 * @return @
	 */
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		Criteria criteria = hSession.createCriteria(TypeSujet.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		final List<TypeSujet> lTypesSujets = MyRequest.list(criteria, lAssociations);
		return lTypesSujets;

	}

	/**
	 * enregistre un type de sujet dans la table param�tre.
	 * 
	 * @param typeSujet
	 * @return @
	 */
	public TypeSujet enregistreNouveauSujet(TypeSujet typeSujet) {

		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(typeSujet);

		return typeSujet;
	}

	/**
	 * enregistrer une r�ponse dd'une question dans une table param�tre.
	 * 
	 * @param reponse
	 * @return @
	 */
	public Reponse enregistreReponse(Reponse reponse) {

		Session hSession = null;
		// if (reponse == null) {
		// throw new PersistenceServiceException("L'objet reponse est null");
		// }

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(reponse);

		return reponse;
	}

	/**
	 * 
	 * @return @
	 */
	public List<Admin> getListAdmin() {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<Admin> lAdmins = hSession.createCriteria(Admin.class).list();
		return lAdmins;

	}

	/**
	 * Supprimer un entity
	 * 
	 * @param entity
	 *            @
	 */
	public void deleteEntity(Object entity) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.delete(entity);

	}

	@SuppressWarnings("unchecked")
	public List<Question> getAllQuestionsResponses() {
		Session hSession = null;

		List<Question> lQuestions = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Question.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ParametresEnum.reponse.getValue());

		lAssociations.add(HibConst.QuestionEnum.Niveau.getValue());

		lAssociations.add(HibConst.QuestionEnum.sujet.getValue());

		lAssociations.add(HibConst.QuestionEnum.Langage.getValue());

		// On traite le r�sultat de la requ�te pour avoir une question avec
		// des r�ponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		lQuestions = (List<Question>) MyRequest.list(criteres, lAssociations);
		return lQuestions;

	}

	public List<Langage> getListLangage(List<String> lAssociations) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		Criteria criteria = hSession.createCriteria(Langage.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		final List<Langage> listLangages = MyRequest.list(criteria, lAssociations);
		return listLangages;

	}

	@Override
	public void ajouter(Object objet) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(objet);

	}

	@Override
	public void supprimer(Object object) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.delete(object);
	}

	@Override
	public void modifier(Object object) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.update(object);
	}
}
