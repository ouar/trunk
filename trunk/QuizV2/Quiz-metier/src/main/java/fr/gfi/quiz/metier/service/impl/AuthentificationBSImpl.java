package fr.gfi.quiz.metier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.AdminDAO;
import fr.gfi.quiz.entite.hibernate.User;
import fr.gfi.quiz.metier.service.AuthentificationBS;

@Service("authentificationBS")
public class AuthentificationBSImpl implements AuthentificationBS{

	@Resource(name="adminDao")
	private AdminDAO adminDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<ROLES> authentification(String sLogin, String sPassword) {
		User user = adminDao.getUserByLogin(sLogin);
		if(user != null){
			if(StringUtils.isNotBlank(user.getLibPassword())){
				if(user.getLibPassword().equals(sLogin)){
					/**
					 * On ajoute les roles
					 */
				}
			}
		}else{
			return null;
		}
		return null;
	}

}
