package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class ChoixQuiz implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 344934583549356337L;
	private IdLibelle langage;
	private IdLibelle sujet;
	private IdLibelle difficulte;
	
	public ChoixQuiz() {
	}

	public ChoixQuiz(IdLibelle langage, IdLibelle sujet, IdLibelle difficulte) {
		super();
		this.langage = langage;
		this.sujet = sujet;
		this.difficulte = difficulte;
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
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

	@Override
	public String toString() {
		return "ChoixQuiz [langage=" + langage + ", sujet=" + sujet
				+ ", difficulte=" + difficulte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
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
		ChoixQuiz other = (ChoixQuiz) obj;
		if (difficulte == null) {
			if (other.difficulte != null)
				return false;
		} else if (!difficulte.equals(other.difficulte))
			return false;
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}
	
	
}
