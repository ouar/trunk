package fr.gfi.quiz.json.entite;

import java.io.Serializable;


public class Reponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2111576868018978396L;
	private IdLibelle reponse;
	private Boolean correcte;
	private Boolean choisie;
	
	public Reponse() {
		// TODO Auto-generated constructor stub
	}

	public Reponse(IdLibelle reponse, boolean correcte, boolean choisie) {
		super();
		this.reponse = reponse;
		this.correcte = correcte;
		this.choisie = choisie;
	}

	public IdLibelle getReponse() {
		return reponse;
	}

	public void setReponse(IdLibelle reponse) {
		this.reponse = reponse;
	}

	public Boolean isCorrecte() {
		return correcte;
	}

	public void setCorrecte(Boolean correcte) {
		this.correcte = correcte;
	}

	public Boolean isChoisie() {
		return choisie;
	}

	public void setChoisie(Boolean choisie) {
		this.choisie = choisie;
	}
	
	@Override
	public String toString() {
		return "Reponse [reponse=" + reponse + ", correcte=" + correcte
				+ ", choisie=" + choisie + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((choisie == null) ? 0 : choisie.hashCode());
		result = prime * result
				+ ((correcte == null) ? 0 : correcte.hashCode());
		result = prime * result + ((reponse == null) ? 0 : reponse.hashCode());
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
		Reponse other = (Reponse) obj;
		if (choisie == null) {
			if (other.choisie != null)
				return false;
		} else if (!choisie.equals(other.choisie))
			return false;
		if (correcte == null) {
			if (other.correcte != null)
				return false;
		} else if (!correcte.equals(other.correcte))
			return false;
		if (reponse == null) {
			if (other.reponse != null)
				return false;
		} else if (!reponse.equals(other.reponse))
			return false;
		return true;
	}
	
}
