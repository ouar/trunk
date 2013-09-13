package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.exceptions.ActionException;

@Controller("SupprimerAction")
public class SupprimerAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request, @RequestParam(value = "idTypeSujet", required = false) int idTypeSujet, @RequestParam(value = "libelleTypeSujet", required = false) String libelleTypeSujet) throws BusinessServiceException,ActionException {

		List<TypeSujet> listTypeSujet = new ArrayList<TypeSujet>();
		listTypeSujet = this.getListTypeSujet(request.getSession(), false);
		ModelAndView model =new ModelAndView("Administration/Administration");
		model.addObject(administrationFormBean);
		if (listTypeSujet != null && listTypeSujet.size() > 0) {
			for (TypeSujet typeSujet : listTypeSujet) {

				if (typeSujet.getId().equals(idTypeSujet)) {
				
					if ((typeSujet.getQuestions() == null || typeSujet.getQuestions().size() == 0) && (typeSujet.getQuizzSujets() == null || typeSujet.getQuizzSujets().size() == 0)) {

						bsAdmin.supprimer(typeSujet);

					} else {
						this.model = model;
						throw new ActionException("Impossible de supprimer ce sujet car il est raattachéà des questions ou à des réponses !");
					}

				}

			}
		}

		this.getListeUtiles(request.getSession(), true);
		administrationFormBean.reset();
		

		return model;
	}

}
