package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;


@Controller("GenererJsonQuizzAction")
public class GenererJsonQuizzAction extends AbstractMonAction {

	@Resource(name = "quizzBusinessService")
	QuizzBusinessService bsqz;


	HttpServletRequest vRequest;


	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {		
				
			this.vRequest=request;
			Quizz quizz;
			try {
				gestionFormBean.setIdQuizzAConsulter(Integer.parseInt(request.getParameter("tfIdQuizzAConsulter")));
				quizz = this.bsqz.getDetailsQuizz(gestionFormBean.getIdQuizzAConsulter());
				if(quizz!=null){
					gestionFormBean.setJsonObject(getJsonQuizzCmgObject(quizz));
				}
			
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		
			return "Gestion/JsonResponse";		

	

	}



	private void constructionJsonObjectPartieQuestions(JSONArray listJsonQuizzQuesion, QuizzQuestion quizzQuestion, JSONObject jsonQuizzQuestion) {

		try {

			jsonQuizzQuestion.accumulate("idQuestion", quizzQuestion.getQuestion().getId());
			jsonQuizzQuestion.accumulate("idLangage", quizzQuestion.getQuestion().getTypeSujet().getLangage().getId());
			jsonQuizzQuestion.accumulate("libLangage", quizzQuestion.getQuestion().getTypeSujet().getLangage().getLibelle());
			jsonQuizzQuestion.accumulate("idTypeSujet", quizzQuestion.getQuestion().getTypeSujet().getId());
			jsonQuizzQuestion.accumulate("libTypeSujet", quizzQuestion.getQuestion().getTypeSujet().getLibelle());
			jsonQuizzQuestion.accumulate("idDifficulte", quizzQuestion.getQuestion().getNiveauQuestion().getId());
			jsonQuizzQuestion.accumulate("libDifficulte", quizzQuestion.getQuestion().getNiveauQuestion().getLibNiveau());
			jsonQuizzQuestion.accumulate("isPlusieursReponsesCorrectes", quizzQuestion.getQuestion().getBolUniqueReponse());
			jsonQuizzQuestion.accumulate("dureeReflexionEnSec", quizzQuestion.getQuestion().getIntDureeReflexion());
			jsonQuizzQuestion.accumulate("libEnonce", quizzQuestion.getQuestion().getLibQuestion());
			String urlImage;
			if(quizzQuestion.getQuestion().getUrlImage()!=null && !"".equals(quizzQuestion.getQuestion().getUrlImage()) && vRequest!=null){
				urlImage = vRequest.getScheme() + "://" + vRequest.getServerName() + ":" + vRequest.getServerPort() + vRequest.getContextPath()+"/imageUpload/"+quizzQuestion.getQuestion().getUrlImage();
				jsonQuizzQuestion.accumulate("picture", urlImage);
			}
			
			
		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	private void constructionJsonObjectPartieTypeQuestions(List<QuizzSujet> listQuizzSujet, JSONArray listQuizzGlobale, JSONArray listJsonSujet) {
		for (QuizzSujet quizzSujet : listQuizzSujet) {

			JSONObject jsonTypeSujet = new JSONObject();
			try {

				jsonTypeSujet.accumulate("idLangage", quizzSujet.getTypeSujet().getLangage().getId());
				jsonTypeSujet.accumulate("libLangage", quizzSujet.getTypeSujet().getLangage().getLibelle());
				jsonTypeSujet.accumulate("idTypeSujet", quizzSujet.getTypeSujet().getId());
				jsonTypeSujet.accumulate("libTypeSujet", quizzSujet.getTypeSujet().getLibelle());
			} catch (JSONException e) {

				e.printStackTrace();
			}

			listJsonSujet.put(jsonTypeSujet);

		}
		JSONObject jsObjaaData = new JSONObject();
		try {
			jsObjaaData = jsObjaaData.put("TypesQuestions", listJsonSujet);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		listQuizzGlobale.put(jsObjaaData);
	}

	private void constructionJsonObjectPartieGenerale(Quizz quizz, JSONArray listQuizzGlobale) {
		JSONObject jsonQuizz = new JSONObject();
		try {

			jsonQuizz.accumulate("idQuestionnaire", quizz.getId());
			jsonQuizz.accumulate("idCandidat", quizz.getUser().getId());
			jsonQuizz.accumulate("nomCandidat", quizz.getLibNomCandidat());
			jsonQuizz.accumulate("idExaminateur", quizz.getUser().getId());
			jsonQuizz.accumulate("nomExaminateur", quizz.getUser().getId());
		} catch (JSONException e) {

			e.printStackTrace();
		}
		listQuizzGlobale.put(jsonQuizz);
	}

	public JSONArray getJsonQuizzCmgObject(Quizz quizz) {

		List<QuizzSujet> listQuizzSujet = new ArrayList<QuizzSujet>(quizz.getQuizzSujets());

		List<QuizzQuestion> listQuizzQuestion = new ArrayList<QuizzQuestion>(quizz.getQuizzQuestions());

		JSONArray listQuizzGlobale = new JSONArray();

		JSONArray listJsonSujet = new JSONArray();

		JSONArray listJsonQuizzQuesion = new JSONArray();

		/*
		 * 
		 */
		constructionJsonObjectPartieGenerale(quizz, listQuizzGlobale);
		/*
		 * 
		 */
		constructionJsonObjectPartieTypeQuestions(listQuizzSujet, listQuizzGlobale, listJsonSujet);

		for (QuizzQuestion quizzQuestion : listQuizzQuestion) {
			JSONObject jsonQuizzQuestion = new JSONObject();
			constructionJsonObjectPartieQuestions(listJsonQuizzQuesion, quizzQuestion, jsonQuizzQuestion);

			JSONArray listJsonReponse = new JSONArray();

			List<Reponse> listQuizzQuestionReponse = new ArrayList<Reponse>(quizzQuestion.getQuestion().getReponses());
			JSONObject jsonReponse = new JSONObject();
			for (Reponse reponse : listQuizzQuestionReponse) {

				try {

					jsonReponse.accumulate("" + reponse.getId(), reponse.getLibReponse());
				} catch (JSONException e) {

					e.printStackTrace();
				}

			}
			listJsonReponse.put(jsonReponse);
			
			try {
				jsonQuizzQuestion.accumulate("reponses", jsonReponse);
				
			} catch (JSONException e) {
			
				e.printStackTrace();
			}
			listJsonQuizzQuesion.put(jsonQuizzQuestion);
			

		}

		JSONObject Questions = new JSONObject();
		try {
			Questions = Questions.put("Questions", listJsonQuizzQuesion);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		listQuizzGlobale.put(Questions);

		return listQuizzGlobale;

	}





}
