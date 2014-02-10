package fr.gfi.cmg.QuizzCmg.persistance.service;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;

public interface AuthentificationPersistancService {
	
	public User getUserByName(String libUser);

}
