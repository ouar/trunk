package fr.gfi.cmg.QuizzCmg.presentation.administration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;


@Controller("AjouterAction")
public class AjouterAction {

	

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean,HttpServletRequest request) {
		
		
		return null;
		
	}
}
