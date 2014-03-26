package fr.gfi.quiz.entite;

import fr.gfi.quiz.entite.hibernate.TypeSujet;

public class SujetDifficulteBean {

	private TypeSujet sujet;

	public SujetDifficulteBean() {
		super();
	}

	public SujetDifficulteBean(TypeSujet sujet) {
		this();
		this.sujet = sujet;
	}

	public void setSujet(TypeSujet sujet) {
		this.sujet = sujet;
	}

	public TypeSujet getSujet() {
		return sujet;
	}

}
