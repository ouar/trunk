package fr.gfi.quiz.presentation.commun.dto;

import java.io.Serializable;

import fr.gfi.quiz.entite.hibernate.NiveauQuestion;
import fr.gfi.quiz.json.entite.IdLibelle;

public class DifficulteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5731830960371485057L;
	IdLibelle difficulte;

	public DifficulteForm() {
		super();
		difficulte = new IdLibelle();
	}
	
	public DifficulteForm(IdLibelle difficulte) {
		this();
		this.difficulte = difficulte;
	}

	public DifficulteForm(int id, String libelle) {
		this();
		difficulte.setId(id);
		difficulte.setLibelle(libelle);
	}
	
	public DifficulteForm(NiveauQuestion difficulteBDD){
		this(difficulteBDD.getId(),difficulteBDD.getLibNiveau());
	}
	
	public IdLibelle getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}

		
	
	@Override
	public String toString() {
		return "DifficulteForm [difficulte=" + difficulte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
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
		DifficulteForm other = (DifficulteForm) obj;
		if (difficulte == null) {
			if (other.difficulte != null)
				return false;
		} else if (!difficulte.equals(other.difficulte))
			return false;
		return true;
	}
	
	
	
}
