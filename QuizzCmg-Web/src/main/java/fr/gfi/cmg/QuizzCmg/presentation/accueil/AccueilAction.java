package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.exceptions.ActionException;
import fr.gfi.cmg.QuizzCmg.util.LogUtil;

/**
 * 
 * @author seouar
 * 
 */
@Controller("AccueilAction")
public class AccueilAction extends AbstractMonAction {

	
	static Logger log = Logger.getLogger(AccueilAction.class.getName());
	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	/** init du panier de commande **/
	@ModelAttribute("accueilFormBean")
	public AccueilFormBean initAcceuilFormBean() {
		return new AccueilFormBean();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView genererListUtil(@ModelAttribute("accueilFormBean") AccueilFormBean accueilFormBean, HttpServletRequest request) throws BusinessServiceException, ActionException {
		log.debug("sssssssssssssssssssss");
		ModelAndView modelAndView = new ModelAndView("Accueil/Accueil");

		modelAndView.addObject("accueilFormBean", accueilFormBean);
		HttpSession session = request.getSession();

		this.getListeUtiles(session, false);

		return modelAndView;
	}

}
