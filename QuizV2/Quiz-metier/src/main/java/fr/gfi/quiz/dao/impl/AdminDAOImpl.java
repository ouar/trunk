package fr.gfi.quiz.dao.impl;

import fr.gfi.quiz.dao.AdminDAO;
import fr.gfi.quiz.entite.hibernate.User;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public class AdminDAOImpl implements AdminDAO{

	@Override
	public User getUserByLogin(String sLogin) {
		// TODO Auto-generated method stub
		return null;
	}

}
