package fr.gfi.quiz.dao;

import java.util.List;

import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;

public interface EditeurDAO {

	void createEditeur(Editeur editeur);

	void updateEditeur(Editeur editeur);

	void deleteEditeur(Editeur Editeur);

	List<Editeur> findEditeurByPagination(CritereRechercheEditeur critere);

	Editeur findEditeurById(long id);

	List<Editeur> findAllEditeurPresent();
}
