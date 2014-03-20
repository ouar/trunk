package fr.gfi.quiz.presentation.commun.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
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
	
	public SujetForm(TypeSujet sujetFromBDD){
		this();
		sujet.setId(sujetFromBDD.getId());
		sujet.setLibelle(sujetFromBDD.getLibelle());
		if (sujetFromBDD.getQuestions().size() > 0) {
			for (Question question : sujetFromBDD.getQuestions()) {
				if (!lDifficultes.contains(question.getNiveauQuestion())) {
					lDifficultes.add(new DifficulteForm(question.getNiveauQuestion()));
				}
			}
		}

	}
	
	public SujetForm(IdLibelle sujet, List<DifficulteForm> lDifficultes) {
		super();
		this.sujet = sujet;
		this.lDifficultes = lDifficultes;
	}

	@Override
	public String toString() {
		return "SujetForm [sujet=" + sujet + ", lDifficultes=" + lDifficultes
				+ "]";
	}

}
