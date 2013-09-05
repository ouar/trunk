package fr.gfi.cmg.QuizzCmg.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;

public class SujetBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8406260854034373879L;
	private int id;
	private String libelle;
	private List<DifficulteBean> lDifficultes;

	public SujetBean() {
		super();
		lDifficultes = new ArrayList<DifficulteBean>();
	}

	public SujetBean(int id, String libelle, List<DifficulteBean> lDifficultes) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.lDifficultes = lDifficultes;
	}

	public SujetBean(TypeSujet typeSujetFromBDD) {
		this();
		this.id = typeSujetFromBDD.getId();
		this.libelle = typeSujetFromBDD.getLibelle();
		if (typeSujetFromBDD.getQuestions().size() > 0) {
			for (Question question : typeSujetFromBDD.getQuestions()) {
				if (!lDifficultes.contains(question.getNiveauQuestion())) {
					lDifficultes.add(new DifficulteBean(question.getNiveauQuestion()));
				}
			}
		}
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

	public List<DifficulteBean> getlDifficultes() {
		return lDifficultes;
	}

	public void setlDifficultes(List<DifficulteBean> lDifficultes) {
		this.lDifficultes = lDifficultes;
	}

	@Override
	public String toString() {
		return "SujetBean [id=" + id + ", libelle=" + libelle + ", lDifficultes=" + lDifficultes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lDifficultes == null) ? 0 : lDifficultes.hashCode());
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
			if (obj instanceof TypeSujet) {
				return ((Langage) obj).getId() == this.getId();
			} else
				return false;
		}
		SujetBean other = (SujetBean) obj;
		if (id != other.id)
			return false;
		if (lDifficultes == null) {
			if (other.lDifficultes != null)
				return false;
		} else if (!lDifficultes.equals(other.lDifficultes))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

}
