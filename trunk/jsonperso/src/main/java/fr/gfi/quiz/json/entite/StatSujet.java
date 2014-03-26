package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class StatSujet implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8248299183306733123L;
	private IdLibelle sujet;
	private IdLibelle difficulte;

	private int noteSur20;
	private int nbQuestionsOK;
	private int nbQuestionsKO;
	private int nbQuestionsPartielles;

	public StatSujet() {

	}

	public StatSujet(IdLibelle sujet, IdLibelle difficulte, int noteSur20,
			int nbQuestionsOK, int nbQuestionsKO, int nbQuestionsPartielles) {
		super();
		this.sujet = sujet;
		this.difficulte = difficulte;
		this.noteSur20 = noteSur20;
		this.nbQuestionsOK = nbQuestionsOK;
		this.nbQuestionsKO = nbQuestionsKO;
		this.nbQuestionsPartielles = nbQuestionsPartielles;
	}

	public IdLibelle getSujet() {
		return sujet;
	}
	public void setSujet(IdLibelle sujet) {
		this.sujet = sujet;
	}
	public IdLibelle getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}
	public int getNoteSur20() {
		return noteSur20;
	}
	public void setNoteSur20(int noteSur20) {
		this.noteSur20 = noteSur20;
	}
	public int getNbQuestionsOK() {
		return nbQuestionsOK;
	}
	public void setNbQuestionsOK(int nbQuestionsOK) {
		this.nbQuestionsOK = nbQuestionsOK;
	}
	public int getNbQuestionsKO() {
		return nbQuestionsKO;
	}
	public void setNbQuestionsKO(int nbQuestionsKO) {
		this.nbQuestionsKO = nbQuestionsKO;
	}
	public int getNbQuestionsPartielles() {
		return nbQuestionsPartielles;
	}
	public void setNbQuestionsPartielles(int nbQuestionsPartielles) {
		this.nbQuestionsPartielles = nbQuestionsPartielles;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + nbQuestionsKO;
		result = prime * result + nbQuestionsOK;
		result = prime * result + nbQuestionsPartielles;
		result = prime * result + noteSur20;
		result = prime * result + ((sujet == null) ? 0 : sujet.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatSujet other = (StatSujet) obj;
		if (difficulte == null) {
			if (other.difficulte != null)
				return false;
		} else if (!difficulte.equals(other.difficulte))
			return false;
		if (nbQuestionsKO != other.nbQuestionsKO)
			return false;
		if (nbQuestionsOK != other.nbQuestionsOK)
			return false;
		if (nbQuestionsPartielles != other.nbQuestionsPartielles)
			return false;
		if (noteSur20 != other.noteSur20)
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StatSujet [sujet=" + sujet + ", difficulte=" + difficulte
				+ ", noteSur20=" + noteSur20 + ", nbQuestionsOK="
				+ nbQuestionsOK + ", nbQuestionsKO=" + nbQuestionsKO
				+ ", nbQuestionsPartielles=" + nbQuestionsPartielles + "]";
	}


}
