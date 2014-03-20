package fr.gfi.quiz.metier.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.AdminDAO;
import fr.gfi.quiz.dao.utils.HibConst.UserEnum;
import fr.gfi.quiz.entite.hibernate.User;
import fr.gfi.quiz.entite.hibernate.UserRoles;
import fr.gfi.quiz.metier.service.AuthentificationBS;

@Service("authBS")
public class AuthentificationBSImpl implements AuthentificationBS{

	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<String> authentification(String sLogin, String sPassword) {
		List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(UserEnum.roles.getValue());
		
		User user = adminDAO.getUserByNameAndPwd(sLogin,sPassword,lAssociations);
		if(user != null && user.getUserRoleses() != null && user.getUserRoleses().size()>0){
			List<String> lDroits = new ArrayList<String>();
			for(UserRoles userRole : user.getUserRoleses()){
				lDroits.add(userRole.getRole().getLibRole());
			}
			return lDroits;
		}
		return null;
	}

}
