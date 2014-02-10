package fr.gfi.cmg.QuizzCmg.persistance.service.impl.hibernate;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.persistance.service.AuthentificationPersistancService;


@Repository("authentificationPersistancService")
public class AuthentificationPersistancServiceImpl implements
		AuthentificationPersistancService {
	
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserByName(String libUser) {
		Session hSession = null;
		User user = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(User.class).createAlias("userRoleses", "userRoleses");
		criteres.add(Restrictions.eq("libNom", libUser));	

		if(criteres.list()!=null && criteres.list().size()>0){
			user = (User) criteres.list().get(0);
		}
		

		return user;
	}

}
