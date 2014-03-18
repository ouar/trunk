package fr.gfi.quiz.dao;

import java.util.List;

import fr.gfi.quiz.entite.Ville;


public interface VilleDAO {

	Ville findVilleByCodePostal(Long codePostal);
	
	List<Ville> findVilles(String nomVille, int maxSize);
	
}
