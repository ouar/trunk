package fr.gfi.cmg.QuizzCmg.presentation.administration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("QuizzAdministrationAction")
public class QuizzAdministrationAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	/** init du panier de commande **/
	@ModelAttribute("administrationFormBean")
	public AdministrationFormBean initAdministrationFormBean() {
		return new AdministrationFormBean();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request) {

		String vueEncoursUtlisation = request.getParameter("vueEncoursUtlisation");

		HttpSession session = request.getSession();

		this.getListeUtiles(session);

		if ("langage".equals(vueEncoursUtlisation)) {
			return "Administration/ParametrageLangage";

		} else if ("typeSujet".equals(vueEncoursUtlisation)) {
			return "Administration/ParametrageTypeSujet";
		} else {
			return "Administration/ParametrageQuestion";
		}

	}

}
