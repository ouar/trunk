package fr.gfi.quiz.metier.service;

import java.util.List;

public interface AuthentificationBS {

	List<String> authentification(String sLogin, String sPassword);

}
