package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;



@Controller("AfficherFormulaireGenerationQuizzAction")
public class AfficherFormulaireGenerationQuizzAction extends AbstractMonAction {

	
	
	/** init du panier de commande **/
	@ModelAttribute("gestionFormBean")
	public GestionFormBean initGestionFormBean() {
		return new GestionFormBean();
	}

	


	/**
	 * initialisation des attributs du formulaire de génération.
	 * 
	 * @param fb
	 */
	private void initialiserFormulaireGeneration(GestionFormBean fb) {
		fb.getListIdTypesSujetSaisis().clear();
		fb.setIdNiveauQuestionnaire(0);
		fb.setNomCandidat(null);
		fb.setPrenomCandidat(null);
		fb.reset();

	}
	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		validate(session);
		if (this.isErreur()) {
			return "redirect:LoginAction";		
		}

		else {
			initialiserFormulaireGeneration(gestionFormBean);

			return"Gestion/GenerationQuizz";
		}
	

		//ModelAndView modelAndView = new ModelAndView("Accueil/Accueil");
		
	}




}
