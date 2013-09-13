package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.beans.QuestionBean;

@Controller("AjouterAction")
public class AjouterAction extends AbstractMonAction {

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView execute(@ModelAttribute("gestionFormBean") AdministrationFormBean administrationFormBean, HttpServletRequest request, @RequestParam(value = "vueEncoursUtlisation", required = false) String vueEncoursUtlisation, @RequestParam(value = "typeSujetAjoute", required = false) String typeSujetAjoute, @RequestParam(value = "image", required = false) String image) {

		ModelAndView model = null;

		if ("langage".equals(vueEncoursUtlisation)) {

			model = new ModelAndView("Administration/Administration");

			Langage langage = new Langage();

			langage.setLibelle(administrationFormBean.getLibelleLangage());

			try {
				bsAdmin.ajouter(langage);

			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("typeSujet".equals(vueEncoursUtlisation)) {
			// String idlangage = request.getParameter("langage");

			Langage langage = new Langage();
			langage.setId(administrationFormBean.getIdLangage());
			TypeSujet typesujet = new TypeSujet();
			String[] libelleTypeSujet = administrationFormBean.getLibelleTypeSujet();
			if (libelleTypeSujet != null && libelleTypeSujet.length > 0) {
				for (int i = 0; i < libelleTypeSujet.length; i++) {

					if (libelleTypeSujet[i] != null && !libelleTypeSujet[i].equals("")) {
						typesujet.setLibelle(libelleTypeSujet[i]);
					}

				}
			}
			typesujet.setLangage(langage);
			try {
				bsAdmin.ajouter(typesujet);
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			model = new ModelAndView("Administration/Administration");

		} else {

			Langage langage = new Langage();
			// langage.setId(administrationFormBean.getIdLangage());
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

			Set<Reponse> listReponse = new HashSet<Reponse>();

			Map paramMap = request.getParameterMap();
			Iterator entries = paramMap.entrySet().iterator();
			while (entries.hasNext()) {
				Entry entree = (Entry) entries.next();
				String cle = (String) entree.getKey();

				if (cle.indexOf("_typeReponse") != -1) {
					String[] valeur = (String[]) entree.getValue();
					String[] tab = valeur[0].split("_");

					if (tab != null && tab.length >= 2) {

						String[] libellereponse = (String[]) paramMap.get(tab[0]);
						String typeReponse = tab[1];
						Reponse reponse = new Reponse();
						reponse.setLibReponse(libellereponse[0]);
						reponse.setQuestion(question);
						if ("VRAI".equals(typeReponse)) {
							reponse.setBolTypeReponse(true);
						} else if ("FAUSSE".equals(typeReponse)) {
							reponse.setBolTypeReponse(false);
						}
						listReponse.add(reponse);

					}

				}
			}

			question.setReponses(listReponse);

			try {
				bsAdmin.ajouter(question);
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<QuestionBean> listQuestionsFiltres = this.getQuestionsByIdTypeSujet(request.getSession(), typeSujet.getId());
			administrationFormBean.setListQuestionsFiltres(listQuestionsFiltres);
			model = new ModelAndView("Administration/ParametrageQuestion");

		}

		this.getListeUtiles(request.getSession(), true);
		administrationFormBean.reset();
		model.addObject(administrationFormBean);

		return model;
	}


}
