package fr.gfi.cmg.QuizzCmg.metier.beans;

import java.util.List;


import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;

import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;

public class InfoGenerationQuizz {

	private NiveauQuestion niveauQuestion;
	private List<TypeSujet> lTypeSujet;
	private User user;
	private String nomCandidat;
	private String prenomCandidat;
	private List<BeanNiveauTypeSujet> listNiveauTypeSujet;

	public InfoGenerationQuizz() {

	}

	public InfoGenerationQuizz(NiveauQuestion niveauQuestion, List<TypeSujet> lTypeSujet, User user, String nomCandidat, String prenomCandidat) {
		this.niveauQuestion = niveauQuestion;
		this.lTypeSujet = lTypeSujet;
		this.user = user;
		this.nomCandidat = nomCandidat;
		this.prenomCandidat = prenomCandidat;
	}

	/**
	 * @return the lNiveauQuestion
	 */
	public NiveauQuestion getNiveauQuestion() {
		return niveauQuestion;
	}

	/**
	 * @param lNiveauQuestion
	 *            the lNiveauQuestion to set
	 */
	public void setNiveauQuestion(NiveauQuestion niveauQuestion) {
		this.niveauQuestion = niveauQuestion;
	}

	/**
	 * @return the lTypeSujet
	 */
	public List<TypeSujet> getlTypeSujet() {
		return lTypeSujet;
	}

	/**
	 * @param lTypeSujet
	 *            the lTypeSujet to set
	 */
	public void setlTypeSujet(List<TypeSujet> lTypeSujet) {
		this.lTypeSujet = lTypeSujet;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the nomCandidat
	 */
	public String getNomCandidat() {
		return nomCandidat;
	}

	/**
	 * @param nomCandidat
	 *            the nomCandidat to set
	 */
	public void setNomCandidat(String nomCandidat) {
		this.nomCandidat = nomCandidat;
	}

	/**
	 * @return the prenomCandidat
	 */
	public String getPrenomCandidat() {
		return prenomCandidat;
	}

	/**
	 * @param prenomCandidat
	 *            the prenomCandidat to set
	 */
	public void setPrenomCandidat(String prenomCandidat) {
		this.prenomCandidat = prenomCandidat;
	}

	public List<BeanNiveauTypeSujet> getListNiveauTypeSujet() {
		return listNiveauTypeSujet;
	}

	public void setListNiveauTypeSujet(List<BeanNiveauTypeSujet> listNiveauTypeSujet) {
		this.listNiveauTypeSujet = listNiveauTypeSujet;
	}
	
	
	

}
