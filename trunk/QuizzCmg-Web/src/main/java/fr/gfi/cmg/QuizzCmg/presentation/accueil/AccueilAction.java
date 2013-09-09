package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

/**
 * 
 * @author seouar
 * 
 */
@Controller("AccueilAction")
public class AccueilAction extends AbstractMonAction {

	// private Logger logger= Logger.getLogger(OuvrageAction.class);

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	/** init du panier de commande **/
	@ModelAttribute("accueilFormBean")
	public AccueilFormBean initAcceuilFormBean() {
		return new AccueilFormBean();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView genererListUtil(@ModelAttribute("accueilFormBean") AccueilFormBean accueilFormBean, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("Accueil/Accueil");

		modelAndView.addObject("accueilFormBean", accueilFormBean);
		HttpSession session = request.getSession();

		this.getListeUtiles(session,false);

		return modelAndView;
	}

}
