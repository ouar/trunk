package fr.gfi.quiz.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.gfi.quiz.dao.AdminDAO;
import fr.gfi.quiz.dao.utils.MyRequest;
import fr.gfi.quiz.entite.hibernate.Difficulte;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Reponse;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.entite.hibernate.User;

@Repository("adminDAO")
public class AdminDAOImpl extends AbstractDAOImpl implements AdminDAO{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public User getUserByNameAndPwd(String sName, String sPass, List<String> lAssociations) {
		Session hSession = null;
		User user = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(User.class);
		criteres.add(Restrictions.eq("login", sName));
		criteres.add(Restrictions.eq("password", sPass));

		user = (User) MyRequest.uniqueResult(criteres, lAssociations);

		return user;
	}

	@Override
	public Integer enregistreQuestion(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeSujet enregistreNouveauSujet(TypeSujet typeSujet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reponse enregistreReponse(Reponse reponse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Difficulte> getListNiveauxQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeSujet> getListTypesSujet(List<String> lAssociations) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getListUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getAllQuestionsResponses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Langage> getListLangage(List<String> lAssociations) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(Object objet) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(objet);
	}


	public void delete(Object object) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.delete(object);
	}


	public void update(Object object) {
		Session hSession = null;
		hSession = this.sessionFactory.getCurrentSession();
		hSession.update(object);
	}

}
