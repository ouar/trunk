package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class TypeQuestion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6500627937157025171L;
	private IdLibelle langage;
	private IdLibelle typeSujet;
	private IdLibelle difficulte;
	
	public TypeQuestion() {
	}

	public TypeQuestion(IdLibelle langage, IdLibelle typeSujet,
			IdLibelle difficulte) {
		super();
		this.langage = langage;
		this.typeSujet = typeSujet;
		this.difficulte = difficulte;
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
	}

	public IdLibelle getTypeSujet() {
		return typeSujet;
	}

	public void setTypeSujet(IdLibelle typeSujet) {
		this.typeSujet = typeSujet;
	}

	public IdLibelle getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}

	@Override
	public String toString() {
		return "TypeQuestion [langage=" + langage + ", typeSujet=" + typeSujet
				+ ", difficulte=" + difficulte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
		result = prime * result
				+ ((typeSujet == null) ? 0 : typeSujet.hashCode());
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
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		if (typeSujet == null) {
			if (other.typeSujet != null)
				return false;
		} else if (!typeSujet.equals(other.typeSujet))
			return false;
		return true;
	}
	
	
	
	
}
