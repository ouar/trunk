package fr.gfi.quiz.presentation.commun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(HomeController.class);


	@RequestMapping("/pages/scenes/ouvrage/{fileName}")
	public String fetchHtmlFile(@PathVariable("fileName") String fileName) {
		return fileName;
	}


	@RequestMapping(value = "/accueil")
	public String showAccueil() throws Exception {
		LOGGER.debug("Appel de AccueilController.afficherAccueil()");

		return "accueil.scene";
	}

	@RequestMapping(value = "/login")
	public String showLogin() throws Exception {
		LOGGER.debug("Appel de AccueilController.afficherLogin()");

		return "login.scene";
	}

	@RequestMapping(value = "/healthCheck")
	public String showHealthCheck() throws Exception {
		LOGGER.debug("Appel de HomeController.showHealthCheck()");

		return "healthcheck/healthCheck";
	}

	@RequestMapping(value = "/dref")
	public String showDref() throws Exception {
		LOGGER.debug("Appel de AccueilController.showDref()");

		return "dref.scene";
	}

	@RequestMapping(value = "/commande")
	public String showCommande() throws Exception {
		LOGGER.debug("Appel de AccueilController.showCommande()");

		return "commande.scene";
	}

	@RequestMapping(value = "/mainOuvragesSPI")
	public String showOuvrageAsSinglePageInterface() throws Exception {
		LOGGER.debug("Appel de AccueilController.afficherOuvrageSceneSPI()");

		return "ouvrage.spi.scene";
	}

}
