package fr.icdc.dei.client;

import fr.icdc.dei.shared.dto.PersonneDTO;

public interface AccueilVue {
	void lancerApplication();

	void enregistrerPresenter(AccueilPresenter presenter);

	String recupererChampNom();

	void afficherErreur(String message);

	void reinitialiser(String nomTableau);

	void afficherTableau(PersonneDTO[] listePersonnes);
}
