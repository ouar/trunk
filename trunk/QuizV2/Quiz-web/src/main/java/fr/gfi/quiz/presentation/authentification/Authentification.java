package fr.gfi.quiz.presentation.authentification;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.gfi.quiz.metier.service.AuthentificationBS;

public class Authentification implements UserDetailsService{

	@Resource(name="authentificationBS")
	private AuthentificationBS authentificationBS;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<AuthentificationBS.ROLES> lRoles = authentificationBS.authentification(username, "");
		return null;
	}

}
