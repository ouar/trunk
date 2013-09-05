package fr.gfi.cmg.QuizzCmg.presentation.beans;

import java.io.Serializable;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;

public class DifficulteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8650378191213591272L;
	private int id;
	private String libelle;

	public DifficulteBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DifficulteBean(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public DifficulteBean(NiveauQuestion niveauQuestion) {
		this(niveauQuestion.getId(), niveauQuestion.getLibNiveau());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "DifficulteBean [id=" + id + ", libelle=" + libelle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			if (obj instanceof NiveauQuestion) {
				return ((Langage) obj).getId() == this.getId();
			} else
				return false;
		}
		DifficulteBean other = (DifficulteBean) obj;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

}
