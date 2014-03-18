package fr.gfi.quiz.dao;

import fr.gfi.quiz.entite.hibernate.User;

public interface AdminDAO {

	User getUserByLogin(String sLogin);
}
