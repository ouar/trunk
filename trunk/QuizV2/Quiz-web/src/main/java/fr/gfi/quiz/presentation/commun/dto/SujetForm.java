package fr.gfi.quiz.presentation.commun.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Sujet;
import fr.gfi.quiz.json.entite.IdLibelle;

public class SujetForm implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -2801458144042957648L;
	IdLibelle sujet;
	List<DifficulteForm> lDifficultes;

	public SujetForm() {
		super();
		sujet = new IdLibelle();
		lDifficultes = new ArrayList<DifficulteForm>();
	}

	public SujetForm(int id, String libelle){
		this();
		sujet = new IdLibelle(id,libelle);
	}

	public SujetForm(Sujet sujetFromBDD){
		this();
		sujet.setId(sujetFromBDD.getId().getId());
		sujet.setLibelle(sujetFromBDD.getLibelle());
		if (sujetFromBDD.getQuestions().size() > 0) {
			for (Question question : sujetFromBDD.getQuestions()) {
				DifficulteForm difficulteTemp = new DifficulteForm(question.getSujet().getDifficulte());
				if (!lDifficultes.contains(difficulteTemp)) {
					lDifficultes.add(difficulteTemp);
				}
			}
		}

	}

	public SujetForm(IdLibelle sujet, List<DifficulteForm> lDifficultes) {
		super();
		this.sujet = sujet;
		this.lDifficultes = lDifficultes;
	}



	public IdLibelle getSujet() {
		return sujet;
	}

	public void setSujet(IdLibelle sujet) {
		this.sujet = sujet;
	}

	public List<DifficulteForm> getlDifficultes() {
		return lDifficultes;
	}

	public void setlDifficultes(List<DifficulteForm> lDifficultes) {
		this.lDifficultes = lDifficultes;
	}

	@Override
	public String toString() {
		return "SujetForm [sujet=" + sujet + ", lDifficultes=" + lDifficultes
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SujetForm other = (SujetForm) obj;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}



}
