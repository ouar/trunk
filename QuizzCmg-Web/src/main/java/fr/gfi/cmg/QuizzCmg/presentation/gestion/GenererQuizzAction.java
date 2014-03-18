package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.beans.InfoGenerationQuizz;
import fr.gfi.cmg.QuizzCmg.metier.beans.Questionnaire;
import fr.gfi.cmg.QuizzCmg.metier.beans.SujetDifficulteBean;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.QuestionsNonTrouveesException;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.beans.GestionFormBean;
import fr.gfi.cmg.QuizzCmg.util.UserBean;



@Controller("GenererQuizzAction")
public class GenererQuizzAction extends AbstractMonAction {

	
	@RequestMapping(method = RequestMethod.POST)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {

		validate(gestionFormBean, request);
		List<TypeSujet> lTypeSujetsSaisis = null;
		String retour="";
		try {
			List<JSONObject> lSujetsDifficulteJson = generateListObjectFromJson(gestionFormBean.getJsonSujetDifficulte());

			// construction du tableau de Sujet avec niveau de difficulté voulu
			List<SujetDifficulteBean> lSujetsDifficulte = new ArrayList<SujetDifficulteBean>();
			for (JSONObject sujetJson : lSujetsDifficulteJson) {
				NiveauQuestion difficulte = new NiveauQuestion();
				difficulte.setId(Integer.valueOf((String) sujetJson.get("difficulteid")));
				// difficulte.setLibNiveau((String)
				// sujetJson.get("difficulteid"));

				TypeSujet sujet = new TypeSujet();
				sujet.setId(Integer.valueOf((String) sujetJson.get("sujetid")));

				lSujetsDifficulte.add(new SujetDifficulteBean(sujet, difficulte));
			}

			InfoGenerationQuizz infoGenerationQuizz = new InfoGenerationQuizz(lSujetsDifficulte, connecte, gestionFormBean.getNomCandidat(), gestionFormBean.getPrenomCandidat());
			infoGenerationQuizz.setListNiveauTypeSujet(gestionFormBean.getListNiveauTypeSujetPanier());
			User user =new User();
			UserBean activeUser = (UserBean)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user.setId(activeUser.getId());
			user.setLibNom(activeUser.getUsername());
			infoGenerationQuizz.setUser(user);
			Questionnaire questionnaire = this.bsqz.genererQuizz(infoGenerationQuizz);

			final Quizz quizz = this.bsqz.getDetailsQuizz(questionnaire.getQuizz().getId());
			gestionFormBean.setQuizz(quizz);
			gestionFormBean.getPanierListQuizz().add(quizz);

			String urlServeur = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

			String urlFlashCode = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=" 
									+ urlServeur + "/QuizzCmg-Web" 
									+ SEPARATOR + questionnaire.getQuizz().getId();

			gestionFormBean.setUrlFlashCode(urlFlashCode);
			retour= "Gestion/ResumerQuizz";
		} catch (BusinessServiceException e) {
			System.err.println(e);
		} catch (QuestionsNonTrouveesException e) {
			System.err.println(e);
			
			/*
			 * Dans le cas ou les critères ne correspondent, l'écran doit rester
			 * dans l'état dans lequel il a été saisi.
			 */
			List<String> listIdTypesSujetSaisis = new ArrayList<String>();
			for (TypeSujet typeSujet : lTypeSujetsSaisis) {
				listIdTypesSujetSaisis.add(typeSujet.getId().toString());
			}
			gestionFormBean.setListIdTypesSujetSaisis(listIdTypesSujetSaisis);

			retour="Gestion/GenerationQuizz";
		
		
		}
		catch (JSONException e) {
			System.err.println(e);
		}
		return retour;

	}





	
	public String validate(GestionFormBean gestionFormBean,HttpServletRequest request) {
		this.isConnect(request.getSession());		

		try {
			generateListObjectFromJson(gestionFormBean.getJsonSujetDifficulte());
		} catch (JSONException e) {
			System.err.println(e);
		}
		
		boolean isErreur=false;

		if (gestionFormBean.getListTypeSujet() == null || gestionFormBean.getListTypeSujet().isEmpty()) {
			isErreur=true;
		}

		if (StringUtils.isBlank((""+gestionFormBean.getIdNiveauQuestionnaire()))) {
			isErreur=true;
		}

		if (StringUtils.isBlank(gestionFormBean.getNomCandidat())) {
			isErreur=true;
		}

		if (StringUtils.isBlank((gestionFormBean.getPrenomCandidat()))) {
			isErreur=true;
		}
		
		if(isErreur){
			 return "Gestion/GenerationQuizz";
		}else {
			return "Gestion/GenerationQuizz";
		}
	}
	
	private List<JSONObject> generateListObjectFromJson(String json) throws JSONException {
		ArrayList<JSONObject> lSujetDifficulte = new ArrayList<JSONObject>();
		JSONArray ljsonSujetDifficulte = new JSONArray(json);
		for (int i = 0; i < ljsonSujetDifficulte.length(); i++) {
			lSujetDifficulte.add(ljsonSujetDifficulte.getJSONObject(i));
		}
		return lSujetDifficulte;
	}

}
