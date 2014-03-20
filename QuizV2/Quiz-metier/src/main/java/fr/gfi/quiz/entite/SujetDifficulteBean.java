package fr.gfi.quiz.entite;

import fr.gfi.quiz.entite.hibernate.NiveauQuestion;
import fr.gfi.quiz.entite.hibernate.TypeSujet;

public class SujetDifficulteBean {

	private TypeSujet sujet;
	private NiveauQuestion difficulte;

	public SujetDifficulteBean() {
		super();
	}

	public SujetDifficulteBean(TypeSujet sujet, NiveauQuestion difficulte) {
		this();
		this.sujet = sujet;
		this.difficulte = difficulte;
	}

	public void setSujet(TypeSujet sujet) {
		this.sujet = sujet;
	}

	public TypeSujet getSujet() {
		return sujet;
	}

	public void setDifficulte(NiveauQuestion difficulte) {
		this.difficulte = difficulte;
	}

	public NiveauQuestion getDifficulte() {
		return difficulte;
	}

}
