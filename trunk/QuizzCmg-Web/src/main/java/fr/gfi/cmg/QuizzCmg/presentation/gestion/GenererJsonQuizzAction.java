package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.beans.GestionFormBean;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;


@Controller("GenererJsonQuizzAction")
public class GenererJsonQuizzAction extends AbstractMonAction {

	HttpServletRequest vRequest;

	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {		
				
			this.vRequest=request;
			Quizz quizz;
			try {
				gestionFormBean.setIdQuizzAConsulter(Integer.parseInt(request.getParameter("id")));
				quizz = this.bsqz.getDetailsQuizz(gestionFormBean.getIdQuizzAConsulter());
				if(quizz!=null){
					Quiz quiz = this.bsqz.convertQuizBDtoQuizJson(quizz);
					gestionFormBean.setJson(JsonBuilder.getJsonStringFromBean(quiz));
				}
			
			} catch (BusinessServiceException e) {
				System.err.println(e);
			}
			return "Gestion/JsonResponse";		
	}

}
