package fr.gfi.cmg.QuizzCmg.metier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.UserRoles;
import fr.gfi.cmg.QuizzCmg.persistance.service.AdminPersistenceService;
import fr.gfi.cmg.QuizzCmg.persistance.service.AuthentificationPersistancService;
import fr.gfi.cmg.QuizzCmg.util.UserBean;

@Service("userDetailsService")
public class AuthentificationBusinessServiceImpl implements UserDetailsService {

	@Resource(name = "authentificationPersistancService")
	private AuthentificationPersistancService authentificationPersistancService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		final fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User user = authentificationPersistancService
				.getUserByName(arg0);
		UserBean principal = null;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		List<UserRoles> lisUserRoles = new ArrayList<UserRoles>(
				user.getUserRoleses());

		for (UserRoles userRoles : lisUserRoles) {
			authorities.add(new SimpleGrantedAuthority(userRoles.getRole()
					.getLibRole()));

		}
		if (user != null) {
			principal = new UserBean(user.getLibNom(), user.getLibPassword(),
					authorities);
		}
		principal.setId(user.getId());
		return principal;
	}

}
