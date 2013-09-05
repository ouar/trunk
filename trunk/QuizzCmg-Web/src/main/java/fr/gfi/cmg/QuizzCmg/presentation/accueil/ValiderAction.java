package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Admin;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;

/**
 * 
 * @author seouar
 * 
 */
@Controller("ValiderAction")
public class ValiderAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.POST)
	public String validate(
			@ModelAttribute("accueilFormBean") @Valid AccueilFormBean accueilFormBean,
			BindingResult result, HttpServletRequest request) {

		String retour = "";
		if (StringUtils.isBlank(accueilFormBean.getPass())) {

			retour = "Accueil/Accueil";
		}

		Admin connecte = null;
		try {
			connecte = (this.bsAdmin).getUserByNameAndPwd(
					accueilFormBean.getUser(), accueilFormBean.getPass());
		} catch (BusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (connecte != null) {

			HttpSession session = request.getSession();
			session.setAttribute("CONNECTE", connecte);
			retour = "redirect:AfficherFormulaireGenerationQuizz" ;
		}
		return retour;
	}

}