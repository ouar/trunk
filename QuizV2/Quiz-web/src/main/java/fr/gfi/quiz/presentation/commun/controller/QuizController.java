package fr.gfi.quiz.presentation.commun.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.quiz.dao.utils.HibConst;
import fr.gfi.quiz.entite.InfoGenerationQuizz;
import fr.gfi.quiz.entite.hibernate.Theme;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.User;
import fr.gfi.quiz.json.entite.ChoixQuiz;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.StatsQuiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;
import fr.gfi.quiz.metier.exception.BusinessServiceException;
import fr.gfi.quiz.metier.exception.QuestionsNonTrouveesException;
import fr.gfi.quiz.presentation.commun.dto.GestionFormBean;
import fr.gfi.quiz.presentation.commun.dto.ThemeForm;


@Controller
@RequestMapping(value = "/quiz")
public class QuizController extends AbstractController{

	private final static Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

	private final static String SEPARATOR = "|";

	@Autowired
	ServletContext context;

	@RequestMapping(value="/new")
	public ModelAndView nouveauQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("new.quiz.scene");

		List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ThemeEnum.DifficulteSujets.getValue());
		lAssociations.add(HibConst.ThemeEnum.Questions.getValue());


		List<Theme> lThemesBD = quizBS.getListThemes(lAssociations);
		List<ThemeForm> lTheme = new ArrayList<ThemeForm>();

		if(lThemesBD != null && lThemesBD.size() > 0){
			for(Theme themeBD : lThemesBD){
				ThemeForm themeTemp = new ThemeForm(themeBD);
				if(!lTheme.contains(themeTemp)){
					lTheme.add(themeTemp);
				}
			}
		}

		mv.addObject("lThemes", lTheme);
		return mv;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView creerQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {

		ModelAndView model = new ModelAndView("quiz.flash");

		List<ChoixQuiz> lChoix = JsonBuilder.getChoixQuizBeanFromJson(gestionFormBean.getJsonSujetDifficulte());
		InfoGenerationQuizz infoGenerationQuizz = new InfoGenerationQuizz(lChoix, connecte, gestionFormBean.getNomCandidat(), gestionFormBean.getPrenomCandidat());
		//infoGenerationQuizz.setListNiveauTypeSujet(gestionFormBean.getListNiveauTypeSujetPanier());
		User user =new User();
		//UserBean activeUser = (UserBean)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setId(1);
		infoGenerationQuizz.setUser(user);
		Quizz quiz;
		try {
			quiz = quizBS.genererQuizz(infoGenerationQuizz);

			String urlServeur = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

			String urlFlashCode = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl="
					+ urlServeur + "/web/"
					+ SEPARATOR + quiz.getId();

			model.addObject("flashUrl", urlFlashCode);

		} catch (BusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QuestionsNonTrouveesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}


	@RolesAllowed("ROLE_USER")
	@RequestMapping(value="/list")
	public ModelAndView listerQuiz(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("quiz.liste.scene");
		try{

			List<Quizz> lQuiz = quizBS.getListQuizzByDate();
			mv.addObject("lQuiz", lQuiz);

		} catch (BusinessServiceException e) {
			System.err.println(e);
		}
		return mv;
	}


	@RolesAllowed("ROLE_USER")
	@RequestMapping(value="/get/{idquiz}")
	public String afficherQuiz(@PathVariable("idquiz") String idQuizz) {
		//TODO
		return null;
	}

	@RequestMapping(value="/getjson/{idquiz}")
	public ModelAndView afficherQuizEnJson(@PathVariable("idquiz") Integer idQuizz) {

		ModelAndView mv = new ModelAndView("quiz.json.tablette");
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

	@ResponseBody
	@RequestMapping(value="/getImage/{idImage}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable("idImage") Integer idQuestion) throws IOException {
		String sPath = quizBS.getImagePath(idQuestion);
		InputStream in = null;
		if(!StringUtils.isEmpty(sPath)){
			FileSystemResource resource = new FileSystemResource(new File("C:/dev/", sPath));
			in = resource.getInputStream();
		}
		if(in == null){
			in = context.getResourceAsStream("/resources/img/noimage.jpg");
		}
		return IOUtils.toByteArray(in);
	}


	@RequestMapping(value="/save/{idquiz}",method = RequestMethod.POST, headers ={"Accept=application/json"},consumes="application/json")
	public String enregistrerQuiz(@PathVariable("idquiz") Integer idQuizz,@RequestBody String json) {
		Quiz quizRepondu = JsonBuilder.getQuizBeanFromJson(json);
		quizBS.enregistrerReponsesQuizz(quizRepondu);
		return "accueil.scene";
	}

	@RequestMapping(value="/get/stats/{idquiz}")
	public ModelAndView getStatsQuiz(@PathVariable("idquiz") Integer idQuizz) {
		ModelAndView mv = new ModelAndView("quiz.stats");
		StatsQuiz statsQuiz = quizBS.getStatQuiz(idQuizz);
		mv.addObject("stats", statsQuiz);
		return mv;
	}

}
