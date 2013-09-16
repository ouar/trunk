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

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.exceptions.ActionException;

@Controller("SupprimerAction")
public class SupprimerAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.GET)
	public Object execute(@ModelAttribute("administrationFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request, @RequestParam(value = "idTypeSujet", required = false) Integer idTypeSujet, @RequestParam(value = "vueEncoursUtlisation", required = false) String vueEncoursUtlisation, @RequestParam(value = "idQuestion", required = false) Integer idQuestion) throws Exception {
		ModelAndView model = null;

		if ("typeSujet".equals(vueEncoursUtlisation)) {
			List<TypeSujet> listTypeSujet = new ArrayList<TypeSujet>();
			listTypeSujet = this.getListTypeSujet(request.getSession(), false);
			model = new ModelAndView("Administration/Administration");
			administrationFormBean.reset();
			model.addObject(administrationFormBean);
			if (listTypeSujet != null && listTypeSujet.size() > 0) {
				for (TypeSujet typeSujet : listTypeSujet) {

					if (typeSujet.getId().equals(idTypeSujet)) {

						if ((typeSujet.getQuestions() == null || typeSujet.getQuestions().size() == 0) && (typeSujet.getQuizzSujets() == null || typeSujet.getQuizzSujets().size() == 0)) {

							bsAdmin.supprimer(typeSujet);

						} else {
							this.model = model;
							throw new ActionException("Impossible de supprimer ce sujet car il est rattaché à des questions ou à des réponses !");
						}

					}

				}
			}

		} else if ("question".equals(vueEncoursUtlisation)) {
			model = new ModelAndView();
			model.setViewName("redirect:DetailTypeSujet?idTypeSujet="+idTypeSujet);
			
			administrationFormBean.reset();
			model.addObject(administrationFormBean);
			List<Question> lListQuestions = new ArrayList<Question>();
			lListQuestions = this.getListQuestions(request.getSession(), false);

			if (lListQuestions != null && lListQuestions.size() > 0) {

				for (Question question : lListQuestions) {

					if (question.getId().equals(idQuestion)) {

						if ((question.getQuizzQuestions() == null || question.getQuizzQuestions().size() == 0) && (question.getReponseCandidats() == null || question.getReponseCandidats().size() == 0)) {

							bsAdmin.supprimer(question);

						} else {						
							this.model = model;
							throw new ActionException("Impossible de supprimer cette question car elle est rattachée à des réponses de candidat ou à un quizz !");
						}

					}

				}

			}

		}

		this.getListeUtiles(request.getSession(), true);
		

		return model;
	}

}
