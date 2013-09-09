package fr.gfi.cmg.QuizzCmg.presentation.administration;

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

@Controller("QuizzAdministrationAction")
public class QuizzAdministrationAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	/**  **/
	@ModelAttribute("administrationFormBean")
	public AdministrationFormBean initAdministrationFormBean() {
		return new AdministrationFormBean();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request) {

		ModelAndView model;
		String vueEncoursUtlisation = request.getParameter("vueEncoursUtlisation");

		HttpSession session = request.getSession();

		this.getListeUtiles(session, false);

		if ("langage".equals(vueEncoursUtlisation)) {
			model = new ModelAndView("Administration/ParametrageLangage");

		} else if ("typeSujet".equals(vueEncoursUtlisation)) {
			model = new ModelAndView("Administration/ParametrageTypeSujet");

		} else {
			model = new ModelAndView("Administration/ParametrageQuestion");

		}

		model.addObject(administrationFormBean);

		return model;

	}

}
