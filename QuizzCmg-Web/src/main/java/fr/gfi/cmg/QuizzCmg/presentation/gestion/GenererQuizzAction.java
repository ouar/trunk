package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.QuestionsNonTrouveesException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

import fr.gfi.cmg.QuizzCmg.util.AbstractConstantes;
import fr.gfi.cmg.QuizzCmg.util.UserBean;



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

		
			
		

			List<JSONObject> lSujetsDifficulteJson = generateListObjectFromJson(gestionFormBean.getJsonSujetDifficulte());

			// construction du tableau de Sujet avec niveau de difficult� voulu
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

			String urlFlashCode = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=" + urlServeur + "/QuizzCmg-Web/GenererJsonQuizz?tfIdQuizzAConsulter=" + questionnaire.getQuizz().getId();

			gestionFormBean.setUrlFlashCode(urlFlashCode);

			retour= "Gestion/ResumerQuizz";
			

			
		

		} catch (BusinessServiceException e) {

		

		} catch (QuestionsNonTrouveesException e) {

			/*
			 * Dans le cas o� les crit�res ne correspondent, l'�cran doit rester
			 * dans l'�tat dans lequel il a �t� saisi.
			 */
			List<String> listIdTypesSujetSaisis = new ArrayList<String>();
			for (TypeSujet typeSujet : lTypeSujetsSaisis) {
				listIdTypesSujetSaisis.add(typeSujet.getId().toString());
			}
			gestionFormBean.setListIdTypesSujetSaisis(listIdTypesSujetSaisis);

			retour="Gestion/GenerationQuizz";
		
		
		}
		catch (JSONException e) {
//			throw new ActionException(e.getMessage(), e);
		}
		return retour;

	}





	
	public String validate(GestionFormBean gestionFormBean,HttpServletRequest request) {
		this.isConnect(request.getSession());		

		try {
			generateListObjectFromJson(gestionFormBean.getJsonSujetDifficulte());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	private List<JSONObject> generateListObjectFromJson(String json) throws JSONException {
		ArrayList<JSONObject> lSujetDifficulte = new ArrayList<JSONObject>();
		JSONArray ljsonSujetDifficulte = new JSONArray(json);
		for (int i = 0; i < ljsonSujetDifficulte.length(); i++) {
			lSujetDifficulte.add(ljsonSujetDifficulte.getJSONObject(i));
		}
		return lSujetDifficulte;
	}

	

	

}
