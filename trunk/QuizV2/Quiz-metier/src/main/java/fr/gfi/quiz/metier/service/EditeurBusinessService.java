package fr.gfi.quiz.metier.service;

import java.util.List;

import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;


/**
 * 
 * Un service m�tier est d�crit par un interface.
 * 
 * Le nom de l'interface se termine par 'BusinessService'.
 *
 */
public interface EditeurBusinessService {

	void createEditeur(Editeur editeur);

	void updateEditeur(Editeur editeur);

	void deleteEditeur(Editeur editeur);
	
	Editeur findEditeurById(Long id);
	
	//@PreAuthorize("hasRole('Administrateur')")
	List<Editeur> findAllEditeur();
	
	//@PreAuthorize("hasRole('Administrateur')")
	List<Editeur> findAllEditeurByPagination(CritereRechercheEditeur critere);

}
