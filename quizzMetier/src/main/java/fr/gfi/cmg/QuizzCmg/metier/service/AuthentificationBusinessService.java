package fr.gfi.cmg.QuizzCmg.metier.service;

import org.springframework.security.core.userdetails.User;

import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;

public interface AuthentificationBusinessService {
	
	public User getUserByName(String libUser,String password) throws BusinessServiceException;

}
