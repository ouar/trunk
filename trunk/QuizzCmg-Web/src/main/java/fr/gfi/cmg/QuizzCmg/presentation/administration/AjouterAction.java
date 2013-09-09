package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("AjouterAction")
public class AjouterAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request, @RequestParam("vueEncoursUtlisation") String vueEncoursUtlisation, @RequestParam(value = "typeSujetAjoute", required = false) String typeSujetAjoute, @RequestParam(value = "image", required = false) String image) {

		ModelAndView model = null;

		if ("langage".equals(vueEncoursUtlisation)) {

			model = new ModelAndView("Administration/ParametrageLangage");

			Langage langage = new Langage();

			langage.setLibelle(administrationFormBean.getLibelleLangage());

			try {
				bsAdmin.ajouter(langage);

			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("typeSujet".equals(vueEncoursUtlisation)) {

			List<TypeSujet> list = deserializerObjetJson(typeSujetAjoute);

			if (list != null && list.size() > 0) {

				for (TypeSujet typeSujet : list) {

					if (typeSujet.getId() == null) {
						try {
							bsAdmin.ajouter(typeSujet);
						} catch (BusinessServiceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}

			model = new ModelAndView("Administration/ParametrageTypeSujet");

		} else {

			
			Langage langage = new Langage();
			langage.setId(administrationFormBean.getIdLangage());
			TypeSujet typeSujet = new TypeSujet();			
			typeSujet.setId(administrationFormBean.getIdTypeSujet());
			typeSujet.setLangage(langage);
			NiveauQuestion niveauQuestion = new NiveauQuestion();
			niveauQuestion.setId(administrationFormBean.getIdNiveauQuestion());
			
			Question question = new Question();
			
			question.setIntDureeReflexion((administrationFormBean.getDureeReflexion()));
			question.setLibQuestion(administrationFormBean.getLibelleQuestion());
			question.setUrlImage(administrationFormBean.getImage());
			question.setTypeSujet(typeSujet);
			question.setNiveauQuestion(niveauQuestion);
			
			Set<Reponse> listReponse=new HashSet<Reponse>();
			
			
			

			String[] reponses = administrationFormBean.getReponse();

			if (reponses != null && reponses.length > 0) {

				for (int i = 0; i < reponses.length; i++) {

					Reponse reponse = new Reponse();
					reponse.setLibReponse(reponses[i]);
					listReponse.add(reponse);

				}
			}

			question.setReponses(listReponse);
			// administrationFormBean.getReponse1();

			// private Integer id;
			// private Question question;
			// private String libReponse;

			// private Integer id;
			// private TypeSujet typeSujet;
			// private NiveauQuestion niveauQuestion;
			// private String libQuestion;
			// private Date datCreation;
			// private Integer intDureeReflexion;
			// private Boolean bolUniqueReponse;
			// private String urlImage;
			// private Set<Reponse> reponses = new HashSet<Reponse>(0);
			
			try {
				bsAdmin.ajouter(question);
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			model = new ModelAndView("Administration/ParametrageQuestion");

		}

		this.getListeUtiles(request.getSession(), true);

		model.addObject(administrationFormBean);

		return model;
	}
}
