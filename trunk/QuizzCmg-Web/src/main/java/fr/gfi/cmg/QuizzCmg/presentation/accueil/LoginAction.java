package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.presentation.beans.GestionFormBean;

@Controller("LoginAction")
public class LoginAction {

	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean, HttpServletRequest request) {
		HttpSession session = request.getSession();
		final User connecte = (User) session.getAttribute("CONNECTE");
		if (connecte == null) {
			return "Accueil/Login";
		} else {
			return "Gestion/GenerationQuizz";
		}

	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(HttpServletRequest request) {
		HttpSession session = request.getSession();
		final User connecte = (User) session.getAttribute("CONNECTE");
		if (connecte != null) {
			session.setAttribute("CONNECTE", null);
		}
		return "redirect:Accueil";

	}
}
