package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.beans.QuestionBean;

@Controller("DetailTypeSujetAction")
public class DetailTypeSujetAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView execute(@ModelAttribute("administrationFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request, 
			@RequestParam(value = "idTypeSujet", required = false) Integer idTypeSujet,@RequestParam(value = "libelleTypeSujet", required = false) String libelleTypeSujet,@RequestParam(value = "erreur", required = false) String erreur) {

		ModelAndView model = null;
		


		model = new ModelAndView("Administration/ParametrageQuestion");

		this.getListeUtiles(request.getSession(), false);
		List<QuestionBean> listQuestionsFiltres = this.getQuestionsByIdTypeSujet(request.getSession(), idTypeSujet);
		administrationFormBean.setListQuestionsFiltres(listQuestionsFiltres);
		administrationFormBean.setLibelleTypeSujetFiltree(libelleTypeSujet);	
		administrationFormBean.setIdTypeSujet(idTypeSujet);
		//administrationFormBean.reset();
		model.addObject(administrationFormBean);
		if(erreur!=null && !erreur.equals("")){
			model.addObject("erreur", erreur);
		}

		return model;
	}


}
