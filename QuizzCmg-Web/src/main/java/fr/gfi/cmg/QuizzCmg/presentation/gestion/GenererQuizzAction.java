package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.beans.InfoGenerationQuizz;
import fr.gfi.cmg.QuizzCmg.metier.beans.Questionnaire;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.QuestionsNonTrouveesException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("GenererQuizzAction")
public class GenererQuizzAction extends AbstractMonAction {

	@Resource(name = "quizzBusinessService")
	QuizzBusinessService bsqz;
	
	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;



	
	@RequestMapping(method = RequestMethod.POST)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {

			validate(gestionFormBean, request);
		
		List<TypeSujet> lTypeSujetsSaisis = null;
		
		String retour="";

		try {

		

			lTypeSujetsSaisis = recupListTypeSujetsSaisis(gestionFormBean,request);

			final NiveauQuestion niveauQuestion = recupNiveauQuestionSaisi(gestionFormBean,request);

			InfoGenerationQuizz infoGenerationQuizz = new InfoGenerationQuizz(niveauQuestion, lTypeSujetsSaisis, connecte, gestionFormBean.getNomCandidat(), gestionFormBean.getPrenomCandidat());
			infoGenerationQuizz.setListNiveauTypeSujet(gestionFormBean.getListNiveauTypeSujetPanier());
			Questionnaire questionnaire = this.bsqz.genererQuizz(infoGenerationQuizz);

			final Quizz quizz = this.bsqz.getDetailsQuizz(questionnaire.getQuizz().getId());

			gestionFormBean.setQuizz(quizz);

			gestionFormBean.getPanierListQuizz().add(quizz);

			String urlServeur = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

			String urlFlashCode = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=" + urlServeur + "/QuizzCmg-Web/GenererJsonQuizz?tfIdQuizzAConsulter=" + questionnaire.getQuizz().getId();

			gestionFormBean.setUrlFlashCode(urlFlashCode);

			retour= "Gestion/ResumerQuizz";
		

		} catch (BusinessServiceException e) {

		

		} catch (QuestionsNonTrouveesException e) {

			/*
			 * Dans le cas où les critères ne correspondent, l'écran doit rester
			 * dans l'état dans lequel il a été saisi.
			 */
			List<String> listIdTypesSujetSaisis = new ArrayList<String>();
			for (TypeSujet typeSujet : lTypeSujetsSaisis) {
				listIdTypesSujetSaisis.add(typeSujet.getId().toString());
			}
			gestionFormBean.setListIdTypesSujetSaisis(listIdTypesSujetSaisis);

			retour="Gestion/GenerationQuizz";
		
		
		}
		return retour;

	}

	@SuppressWarnings("unchecked")
	private List<TypeSujet> recupListTypeSujetsSaisis(GestionFormBean gestionFormBean,HttpServletRequest request) throws BusinessServiceException  {
		List<TypeSujet> lTypeSujetsSaisis = new ArrayList<TypeSujet>();
		List<TypeSujet> lTypesSujetsParametres = (List<TypeSujet>) request.getAttribute("ListeTypesSujets");
		if (lTypesSujetsParametres == null) {
			lTypesSujetsParametres = this.getListTypeSujet(request.getSession(),false);
			request.setAttribute("ListeTypesSujets", lTypesSujetsParametres);
		}

		for (String sTypeSujet : gestionFormBean.getListTypeSujet()) {

			Integer idSujet = new Integer(sTypeSujet);
			// Integer idSujet = (Integer) gestionFormBean.getListTypeSujet().get(i);
			for (TypeSujet sTypeSujetParametre : lTypesSujetsParametres) {

				if (sTypeSujetParametre.getId().equals(idSujet)) {

					lTypeSujetsSaisis.add(sTypeSujetParametre);

				}
			}

		}

		return lTypeSujetsSaisis;
	}

	@SuppressWarnings("unchecked")
	private NiveauQuestion recupNiveauQuestionSaisi(GestionFormBean gestionFormBean,HttpServletRequest request) throws BusinessServiceException  {
		NiveauQuestion niveauQuestionSaisi = null;
		List<NiveauQuestion> lNiveauQuestions = (List<NiveauQuestion>) request.getAttribute("ListeNiveauxDifficultes");

		if (lNiveauQuestions == null) {
			lNiveauQuestions = this.bsAdmin.getListNiveauxQuestion();		
			request.setAttribute("ListeNiveauxDifficultes", lNiveauQuestions);
		}

		for (NiveauQuestion niveauQuestion : lNiveauQuestions) {
			if (niveauQuestion.getId().equals(gestionFormBean.getIdNiveauQuestionnaire())) {
				niveauQuestionSaisi = niveauQuestion;
				break;

			}
		}

		return niveauQuestionSaisi;

	}




	
	public String validate(GestionFormBean gestionFormBean,HttpServletRequest request) {
		this.isConnect(request.getSession());		

		List<String> listTypeSujet = new ArrayList<String>();
		List<BeanNiveauTypeSujet> listNiveauTypeSujetPanier = new ArrayList<BeanNiveauTypeSujet>();

		List<BeanNiveauTypeSujet> listNiveauTypeSujetAselectionner = new ArrayList<BeanNiveauTypeSujet>();

		String panier = request.getParameter("panier");
		String aselectionner = request.getParameter("aselectionner");

		deserializerObjetJson(listTypeSujet, listNiveauTypeSujetPanier, panier);
		deserializerObjetJson(null, listNiveauTypeSujetAselectionner, aselectionner);

		gestionFormBean.setListTypeSujet(listTypeSujet);
		gestionFormBean.setListNiveauTypeSujetPanier(listNiveauTypeSujetPanier);
		gestionFormBean.setListNiveauTypeSujetAselectionner(listNiveauTypeSujetAselectionner);
		
		boolean isErreur=false;

		if (gestionFormBean.getListTypeSujet() == null || gestionFormBean.getListTypeSujet().isEmpty()) {
			//this.addError(new FWKError("lib.typeSujet.manquant"));
			isErreur=true;
		}

		if (StringUtils.isBlank((""+gestionFormBean.getIdNiveauQuestionnaire()))) {
			//this.addError(new FWKError("lib.niveauQuestion.manquant"));
			isErreur=true;
		}

		if (StringUtils.isBlank(gestionFormBean.getNomCandidat())) {
			//this.addError(new FWKError("lib.nomCandidat.manquant"));
			//this.setScene("GenerationQuizz");
			isErreur=true;
		}

		if (StringUtils.isBlank((gestionFormBean.getPrenomCandidat()))) {
			//this.addError(new FWKError("lib.prenomCandidat.manquant"));
			isErreur=true;
		}
		if(isErreur){
			
			 return "Gestion/GenerationQuizz";
			
		}
		else {
			return "Gestion/GenerationQuizz";
			
		}

	}

	/**
	 * @param listTypeSujet
	 * @param listNiveauTypeSujetPanier
	 * @param panier
	 * @throws NumberFormatException
	 */
	private void deserializerObjetJson(List<String> listTypeSujet, List<BeanNiveauTypeSujet> listNiveauTypeSujetPanier, String panier) throws NumberFormatException {
		panier = panier.replaceAll("\"", "'");
		panier = "{'source':" + panier + "}";

		try {
			JSONObject myjson = new JSONObject(panier);
			JSONArray jsonMainArr = myjson.getJSONArray("source");

			for (int i = 0; i < jsonMainArr.length(); i++) { // **line 2**
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);

				String idLangage = childJSONObject.getString("idLangage");
				String libelleLangage = childJSONObject.getString("libelleLangage");
				String idTypeSujet = childJSONObject.getString("idTypeSujet");
				String libelleTypeSujet = childJSONObject.getString("libelleTypeSujet");
				String idNiveau = childJSONObject.getString("idNiveau");
				String libelleNiveau = childJSONObject.getString("libelleNiveau");

				BeanNiveauTypeSujet bean = new BeanNiveauTypeSujet();
				bean.getTypeSujet().setId(Integer.parseInt(idTypeSujet));
				bean.getTypeSujet().setLibelle(libelleTypeSujet);
				Langage langage=new Langage();
				langage.setId(Integer.parseInt(idLangage));
				langage.setLibelle(libelleLangage);
				bean.getTypeSujet().setLangage(langage);
				bean.getNiveauQuestion().setId(Integer.parseInt(idNiveau));
				bean.getNiveauQuestion().setLibNiveau(libelleNiveau);

				if (listNiveauTypeSujetPanier != null) {
					listNiveauTypeSujetPanier.add(bean);
				}

				if (listTypeSujet != null) {
					listTypeSujet.add(idTypeSujet);
				}

			}

		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	

}
