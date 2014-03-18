package fr.gfi.quiz.metier.service;

import java.util.List;

public interface AuthentificationBS {



	List<ROLES> authentification(String sLogin, String sPassword);


	enum ROLES{
		CANDIDAT,ADMINISTRATEUR,UTILISATEUR;
	}
}
