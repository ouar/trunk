package fr.gfi.quiz.presentation.referentiel.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;
import fr.gfi.quiz.entite.Ville;
import fr.gfi.quiz.metier.service.EditeurBusinessService;
import fr.gfi.quiz.metier.service.VilleBusinessService;
import fr.gfi.quiz.presentation.exception.VilleInconnueException;
import fr.gfi.quiz.presentation.referentiel.form.EditeurForm;

/**
 * Web controller for managing Editeur Entity.
 * 
 * @author CCMT Team
 * 
 */
@Controller
@RequestMapping("/dref/editeur")
public class EditeurController {

	@Resource(name = "editeurBusinessService")
	private EditeurBusinessService editeurBusinessService;
	
	@Value("${ville.inconnue.msg}")
	protected String villeIncnnueError;
	
	@Resource(name = "villeBusinessService")
	protected VilleBusinessService villeBusinessService;
	
	@Resource(name = "mapper")
	protected Mapper mapper;
		

	/**
	 * Selection d'un éditeur dans la liste
	 * 
	 * @Formation : Request Mapping : By path
	 */
	@RequestMapping("/show")
	public ModelAndView show(@RequestParam int idEditeur) {

		ModelAndView mv = new ModelAndView("editeur.detail.scene");

		Editeur editeur = editeurBusinessService.findEditeurById(new Long(
				idEditeur));

		mv.addObject("selectedEditeur", editeur);

		return mv;
	}

	/**
	 * Affichage de la page de recherche des editeurs
	 * 
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {

		model.addAttribute("criteresRechercheEditeur",
				new CritereRechercheEditeur());
		return "editeur.list.scene";
	}

	/**
	 * Recherche des editeurs @
	 * 
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(
			@Valid @ModelAttribute("criteresRechercheEditeur") CritereRechercheEditeur criteresRechercheEditeur,
			BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("editeur.list.scene");
		if (bindingResult.hasErrors()) {
			return mv;
		}

		List<Editeur> listEditeurs = editeurBusinessService
				.findAllEditeurByPagination(criteresRechercheEditeur);
		mv.addObject("listEditeurs", listEditeurs);

		return mv;
	}

	/**
	 * Suppression de l'editeur ayant comme id : idEditeur
	 * 
	 * @Formation : Request Mapping : By path
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam int idEditeur) {
		Editeur editeurASupprimer = editeurBusinessService
				.findEditeurById(new Long(idEditeur));

		editeurBusinessService.deleteEditeur(editeurASupprimer);
		return "redirect:/web/dref/editeur/search";
	}

	/**
	 * Affichage de la page de modification de l'editeur ayant comme id :
	 * idEditeur
	 * 
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam int idEditeur) {

		ModelAndView mv = new ModelAndView("editeur.edit.scene");

		Editeur editeur = editeurBusinessService.findEditeurById(new Long(
				idEditeur));
		EditeurForm editeurForm = mapper.map(editeur, EditeurForm.class);

		mv.addObject("editeurForm", editeurForm);
		mv.addObject("type", "update");

		return mv;
	}

	/**
	 * soumission de la modification de l'editeur
	 * 
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("editeurForm") EditeurForm editeurForm,
			BindingResult bindingResult) throws VilleInconnueException {

		if (bindingResult.hasErrors()) {
			return "editeur.edit.scene";
		}

		Editeur editeur = mapper.map(editeurForm, Editeur.class);
		Ville ville = villeBusinessService.findVilleByCodePostal(editeurForm
				.getCodePostal());
		if (ville == null)
			throw new VilleInconnueException(villeIncnnueError,
					editeurForm.getCodePostal());

		editeur.setVille(ville);

		editeurBusinessService.updateEditeur(editeur);
		return "redirect:/web/dref/editeur/search";
	}

	/**
	 * Affichage de la page de creation de l'editeur
	 * 
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView mv = new ModelAndView("editeur.edit.scene");

		EditeurForm editeurForm = new EditeurForm();

		mv.addObject("editeurForm", editeurForm);
		mv.addObject("type", "create");

		return mv;
	}

	/**
	 * soumission de la creation de l'editeur
	 * 
	 * @throws VilleInconnueException
	 * @Formation : Request Mapping : By HTTP method
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(
			@Valid @ModelAttribute("editeurForm") EditeurForm editeurForm,
			BindingResult bindingResult) throws VilleInconnueException {

		if (bindingResult.hasErrors()) {
			return "editeur.edit.scene";
		}

		Editeur editeur = mapper.map(editeurForm, Editeur.class);
		Ville ville = villeBusinessService.findVilleByCodePostal(editeurForm
				.getCodePostal());
		if (ville == null) {
			throw new VilleInconnueException(villeIncnnueError,
					editeurForm.getCodePostal());
		}
		editeur.setVille(ville);
		editeurBusinessService.createEditeur(editeur);
		return "redirect:/web/dref/editeur/search";
	}
	
	
	/**
	 * Gestionnaire d'exception niveau contrôleur
	 */
	@ExceptionHandler(value=VilleInconnueException.class)
	public ModelAndView handleVilleInconnueException(VilleInconnueException exception){
		ModelAndView mav = new ModelAndView("business.exception.scene");
		mav.addObject("exceptionMessage", exception.getMessage());
		return mav;
	}	

}
