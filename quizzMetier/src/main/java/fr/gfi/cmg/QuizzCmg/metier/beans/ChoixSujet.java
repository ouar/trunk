package fr.gfi.cmg.QuizzCmg.metier.beans;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;

/**
 * représente un type de sujet choisi par l'utilisateur. Si l'utilisateur
 * choisit un sujet déjà parametré en base, c'est le champ "sujetChoisi" qui
 * sera renseigné sinon ça sera le champ "sNouveauSujet" .
 * 
 * 
 */

public class ChoixSujet {

	private TypeSujet sujetChoisi;
	private String sNouveauSujet;

	/**
	 * @return the sujetChoisi
	 */
	public TypeSujet getSujetChoisi() {
		return sujetChoisi;
	}

	/**
	 * @param sujetChoisi
	 *            the sujetChoisi to set
	 */
	public void setSujetChoisi(TypeSujet sujetChoisi) {
		this.sujetChoisi = sujetChoisi;
	}

	/**
	 * @return the sNouveauSujet
	 */
	public String getsNouveauSujet() {
		return sNouveauSujet;
	}

	/**
	 * @param sNouveauSujet
	 *            the sNouveauSujet to set
	 */
	public void setsNouveauSujet(String sNouveauSujet) {
		this.sNouveauSujet = sNouveauSujet;
	}

}
