package fr.gfi.cmg.QuizzCmg.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Admin;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;
import fr.gfi.cmg.QuizzCmg.persistance.util.HibConst;
import fr.gfi.cmg.QuizzCmg.presentation.beans.SujetBean;
import fr.gfi.cmg.QuizzCmg.presentation.beans.langageBean;
import fr.gfi.cmg.QuizzCmg.util.AbstractConstantes;

public abstract class AbstractMonAction {

	@Resource(name = "quizzBusinessService")
	QuizzBusinessService bsqz;

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	private boolean erreur = false;
	protected Admin connecte = null;

	public boolean isErreur() {
		return this.erreur;
	}

	public void setErreur(boolean isErreur) {
		this.erreur = isErreur;
	}

	public void validate(HttpSession session) {
		this.setErreur(false);
		connecte = (Admin) session.getAttribute("CONNECTE");
		if (connecte == null) {
			this.setErreur(true);

		}

	}

	public void ajouter(Object objet) {
		try {
			bsAdmin.ajouter(objet);
		} catch (BusinessServiceException e) {

		}
	}

	public void supprimer(Object objet) {
		try {
			bsAdmin.supprimer(objet);
		} catch (BusinessServiceException e) {

		}
	}

	public void modifier(Object objet) {
		try {
			bsAdmin.modifier(objet);
		} catch (BusinessServiceException e) {

		}
	}

	public void getListeUtiles(HttpSession session, boolean reinitialisation) {

		/** Chargement des administrateurs **/
		if (session.getAttribute(AbstractConstantes.LISTE_ADMINS) == null || reinitialisation) {

			List<Admin> listeAdmins = null;
			try {
				listeAdmins = bsAdmin.getListAdmin();
			} catch (BusinessServiceException e) {

				e.printStackTrace();
			}

			session.setAttribute(AbstractConstantes.LISTE_ADMINS, listeAdmins);

		}
		/** Chargement des langage / sujet et leur niveau de difficult�s **/
		if (session.getAttribute(AbstractConstantes.LISTE_LANGAGES) == null || reinitialisation) {

			List<String> lAssociations = new ArrayList<String>();
			lAssociations.add(HibConst.LangageEnum.Questions.getValue() + "." + HibConst.QuestionEnum.Niveau.getValue());
			List<Langage> lListLangages = null;

			try {
				lListLangages = bsAdmin.getListLangage(lAssociations);

			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<langageBean> lLangageBeans = new ArrayList<langageBean>();

			for (Langage langage : lListLangages) {
				JSONArray array = new JSONArray();
				langageBean langageBean = new langageBean(langage);

				for (SujetBean sujet : langageBean.getlSujet()) {
					JSONObject jsonObj = new JSONObject(sujet);
					array.put(jsonObj);
				}
				langageBean.setlSujetJson(array);
				lLangageBeans.add(langageBean);
			}

			session.setAttribute(AbstractConstantes.LISTE_LANGAGES, lLangageBeans);

		}

		getListQuestions(session, reinitialisation);

		getListNiveauDifficultes(session, reinitialisation);

		getListTypeSujet(session, reinitialisation);
	}

	/**
	 * @param session
	 */
	protected void getListQuestions(HttpSession session, boolean reinitialisation) {
		/** Chargement des questions **/
		if (session.getAttribute(AbstractConstantes.LISTE_QUESTIONS) == null || reinitialisation) {

			List<Question> lListQuestions = null;

			try {
				lListQuestions = bsAdmin.getAllQuestionsResponses();

			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute(AbstractConstantes.LISTE_QUESTIONS, lListQuestions);

		}
	}

	/**
	 * @param session
	 */
	protected void getListNiveauDifficultes(HttpSession session, boolean reinitialisation) {
		if (session.getAttribute(AbstractConstantes.LISTE_NIVEAU_DIFFICULTES) == null || reinitialisation) {
			List<NiveauQuestion> lNiveauQuestions = null;

			try {
				lNiveauQuestions = this.bsAdmin.getListNiveauxQuestion();
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute(AbstractConstantes.LISTE_NIVEAU_DIFFICULTES, lNiveauQuestions);

		}
	}

	/**
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	protected List<TypeSujet> getListTypeSujet(HttpSession session, boolean reinitialisation) {
		List<TypeSujet> lListTypeSujets = null;
		/** Chargement des langage / sujet et leur niveau de difficult�s **/
		if (session.getAttribute(AbstractConstantes.LISTE_TYPE_SUJETS) == null || reinitialisation) {

			List<String> lAssociations = new ArrayList<String>();
			lAssociations.add(HibConst.typeSujetEnum.Langage.getValue());

			try {
				lListTypeSujets = bsAdmin.getListTypesSujet(lAssociations);

			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute(AbstractConstantes.LISTE_TYPE_SUJETS, lListTypeSujets);

		}

		else {
			lListTypeSujets = (List<TypeSujet>) session.getAttribute(AbstractConstantes.LISTE_TYPE_SUJETS);
		}
		return lListTypeSujets;
	}

	/**
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	protected List<Question> getQuestionsByIdTypeSujet(HttpSession session, int idTypeSujet) {
		List<Question> lListQuestions = (List<Question>) session.getAttribute(AbstractConstantes.LISTE_QUESTIONS);
		List<Question> lListQuestionsFiltres = new ArrayList<Question>();
		if (lListQuestions != null && lListQuestions.size() > 0) {
			for (Question question : lListQuestions) {

				if (question.getTypeSujet().getId().equals(idTypeSujet)) {
					lListQuestionsFiltres.add(question);
				}

			}
		}
		return lListQuestionsFiltres;
	}
}