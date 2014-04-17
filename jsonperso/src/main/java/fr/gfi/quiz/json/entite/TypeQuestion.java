package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class TypeQuestion implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6500627937157025171L;
	private IdLibelle theme;
	private IdLibelle sujet;
	private IdLibelle difficulte;

	public TypeQuestion() {
	}

	public TypeQuestion(IdLibelle theme, IdLibelle typeSujet,
			IdLibelle difficulte) {
		super();
		this.theme = theme;
		this.sujet = typeSujet;
		this.difficulte = difficulte;
	}

	public IdLibelle getTheme() {
		return theme;
	}

	public void setTheme(IdLibelle theme) {
		this.theme = theme;
	}

	public IdLibelle getSujet() {
		return sujet;
	}

	public void setSujet(IdLibelle typeSujet) {
		this.sujet = typeSujet;
	}

	public IdLibelle getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}

	@Override
	public String toString() {
		return "TypeQuestion [theme=" + theme + ", typeSujet=" + sujet
				+ ", difficulte=" + difficulte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result
				+ ((sujet == null) ? 0 : sujet.hashCode());
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
		TypeQuestion other = (TypeQuestion) obj;
		if (difficulte == null) {
			if (other.difficulte != null)
				return false;
		} else if (!difficulte.equals(other.difficulte))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}




}
