package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.exceptions.ActionException;

/**
 * 
 * @author seouar
 * 
 */
@Controller("AccueilAction")
@RequestMapping(value = "/Accueil/**")
public class AccueilAction extends AbstractMonAction {

	
	static Logger log = Logger.getLogger(AccueilAction.class.getName());
	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	/** init du panier de commande **/
	@ModelAttribute("accueilFormBean")
	public AccueilFormBean initAcceuilFormBean() {
		return new AccueilFormBean();
	}

	@RequestMapping(value = "/Accueil",method = RequestMethod.GET)
	public ModelAndView genererListUtil(@ModelAttribute("accueilFormBean") AccueilFormBean accueilFormBean, HttpServletRequest request) throws BusinessServiceException, ActionException {
	
		ModelAndView modelAndView = new ModelAndView("Accueil/Login");

		modelAndView.addObject("accueilFormBean", accueilFormBean);
		HttpSession session = request.getSession();

		this.getListeUtiles(session, false);

		return modelAndView;
	}
	
	 @RequestMapping(value = "/Accueil/acceesRefuse", method = RequestMethod.GET)  
	 public ModelAndView accessDenied(Principal principal) {  
		 ModelAndView modelAndView = new ModelAndView("Accueil/acceesRefuse");
	  String username = principal.getName();  
	  modelAndView.addObject("message", "Désolé "+username+" Vous n'avez pas de privilèges pour accéder à cette page !!!");  
	 
	  return modelAndView;  
	 } 
	 
	 @RequestMapping(value="/Accueil/failLogin", method = RequestMethod.GET)  
	 public String loginerror(ModelMap model) {  
	   
	  model.addAttribute("error", "true");  
	  return "Accueil/Login";  
	   
	 }

}
