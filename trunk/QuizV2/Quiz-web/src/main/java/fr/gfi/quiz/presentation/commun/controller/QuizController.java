package fr.gfi.quiz.presentation.commun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.quiz.dao.utils.HibConst;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;
import fr.gfi.quiz.metier.exception.BusinessServiceException;
import fr.gfi.quiz.presentation.commun.dto.GestionFormBean;
import fr.gfi.quiz.presentation.commun.dto.LangageForm;


@Controller
@RequestMapping(value = "/quiz")
public class QuizController extends AbstractController{

	private final static Logger LOGGER = LoggerFactory.getLogger(QuizController.class);
	
	
	@RequestMapping(value="/new")
	public ModelAndView nouveauQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("json");
		
		List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.LangageEnum.Questions.getValue() + "." + HibConst.QuestionEnum.Niveau.getValue());

		List<Langage> lLangagesBD = quizBS.getListLangage(lAssociations);
		List<LangageForm> lLangage = new ArrayList<LangageForm>();
		
		if(lLangagesBD != null && lLangagesBD.size() > 0){
			for(Langage langageBD : lLangagesBD){
				lLangage.add(new LangageForm(langageBD));
			}
		}
		
		mv.addObject("lLangages", lLangage);
		return mv;
	}
	
	@RequestMapping(value="/create")
	public String creerQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		//TODO		
		return null;
	}
	
	
	@RolesAllowed("ROLE_USER")
	@RequestMapping(value="/getList")
	public String listerQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		//TODO
		return null;
	}

	
	@RolesAllowed("ROLE_USER")
	@RequestMapping(value="/get/{idquiz}")
	public String afficherQuiz(@PathVariable("idquiz") String idQuizz) {
		//TODO
		return null;
	}
	
	@RequestMapping(value="/get/json/{idquiz}")
	public ModelAndView afficherQuizEnJson(@PathVariable("idquiz") Integer idQuizz) {
		
		ModelAndView mv = new ModelAndView("json");
		Quizz quizz;
		try {
			quizz = quizBS.getDetailsQuizz(idQuizz);
			if(quizz!=null){
				Quiz quiz = quizBS.convertQuizBDtoQuizJson(quizz);
				String sJsonQuiz = JsonBuilder.getJsonStringFromBean(quiz);
				mv.addObject("json", sJsonQuiz);
			}
		
		} catch (BusinessServiceException e) {
			LOGGER.error("erreur lors de la récupération du Quiz à transformer en json",e);
		}
		return mv;		
	}
	
	@RequestMapping(value="/save/{idquiz}")
	public String enregistrerQuiz(@PathVariable("idquiz") Integer idQuizz) {
		//TODO
		return null;
	}

	
}
