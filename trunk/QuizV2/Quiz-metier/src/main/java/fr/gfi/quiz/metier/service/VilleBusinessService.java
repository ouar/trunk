package fr.gfi.quiz.metier.service;

import java.util.List;

import fr.gfi.quiz.entite.Ville;


public interface VilleBusinessService {

	Ville findVilleByCodePostal(Long codePostal);
	
	List<Ville> findVilles(String nomVille, int maxSize);
	
}
