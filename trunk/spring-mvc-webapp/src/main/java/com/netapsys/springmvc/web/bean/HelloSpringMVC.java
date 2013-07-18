package com.netapsys.springmvc.web.bean;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur que l'on invoquera pour l'url /helloSpringMVC.html.
 * 
 * @author Michael Courcy
 * 
 */
@Controller
public class HelloSpringMVC {

	/**
	 * Handler de la méthode Get pour l'URL /helloSpringMVC.html.
	 * 
	 * @param name
	 *            le nom que l'on doit afficher dans la vue.
	 * @param model
	 *            une map de toutes les données qui seront utilisables dans la
	 *            vue
	 * @return le nom de la vue qu'il faudra utiliser.
	 */
	@RequestMapping(value = "/connection.html", method = RequestMethod.GET)
	public ModelAndView sayHelloWithSpringMVC(@RequestParam(value = "name", required = false) String name, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("login");

		return modelAndView;
	}

	@RequestMapping(value = "/acceuil.html", method = RequestMethod.POST)
	public String redirection(@RequestParam(value = "name", required = false) String name, ModelMap model) {

		return "redirect:ListStock.html";

	}

	@RequestMapping(value = "/contact.html", method = RequestMethod.GET)
	public ModelAndView contact(@RequestParam(value = "name", required = false) String name, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("contact");
		
		return modelAndView;
	}

	@RequestMapping(value = "/hello.html", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(value = "name", required = false) String name, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("hello");
		modelAndView.addObject("message", "");
		return modelAndView;
	}

	@RequestMapping(value = "/time.html", method = RequestMethod.GET)
	public @ResponseBody
	String getTime(@RequestParam String name) {
		String result = "Time for " + name + " is " + new Date().toString();
		return result;
	}

}
