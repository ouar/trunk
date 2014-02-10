package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.exceptions.ActionException;

/**
 * 
 * @author seouar
 * 
 */
@Controller("ValiderAction")
public class ValiderAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.POST)
	public String validate(@ModelAttribute("accueilFormBean") @Valid AccueilFormBean accueilFormBean, BindingResult result, HttpServletRequest request) throws ActionException  {

		String retour = "";
		if (StringUtils.isBlank(accueilFormBean.getPass())) {

			retour = "Accueil/Accueil";
		}

		User connecte = null;
		try {
			connecte = (this.bsAdmin).getUserByNameAndPwd(accueilFormBean.getUser(), accueilFormBean.getPass());
		} catch (BusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (connecte != null) {

			HttpSession session = request.getSession();
			session.setAttribute("CONNECTE", connecte);
			retour = "redirect:AfficherFormulaireGenerationQuizz";
		}
		else {
			this.model = new ModelAndView();
			this.model.setViewName("Accueil/Accueil");
			this.model.addObject("accueilFormBean", accueilFormBean);
			this.getListeUtiles(request.getSession(), false);
			throw new ActionException("Vous n'êtes pas habilité");
			
		}
		return retour;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpServletRequest request) {
		String retour = null;
		UserDetails userDetails =
			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
		if(userDetails!=null){
			System.out.println("zzzzzzzzzzzzzz");
			
			retour = "redirect:AfficherFormulaireGenerationQuizz";
		}
		
		
		
		return retour;
 
	}

}