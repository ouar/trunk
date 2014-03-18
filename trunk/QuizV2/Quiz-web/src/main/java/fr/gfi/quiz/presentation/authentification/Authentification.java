package fr.gfi.quiz.presentation.authentification;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import fr.gfi.quiz.metier.service.AuthentificationBS;

public class Authentification implements AuthenticationProvider{

	private static final Logger LOGGER = LoggerFactory.getLogger(Authentification.class);


	@Resource(name="authentificationBS")
	private AuthentificationBS authentificationBS;

@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LOGGER.debug("demande d'authentification du login [" + authentication.getName() + "]");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<AuthentificationBS.ROLES> lRoles = authentificationBS.authentification(username, password);
		if(lRoles != null && lRoles.size()>0){
			for(AuthentificationBS.ROLES role : lRoles){
				switch(role){
					case CANDIDAT:
						break;
					case ADMINISTRATEUR:
						break;
					case UTILISATEUR:
						break;
				}
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
