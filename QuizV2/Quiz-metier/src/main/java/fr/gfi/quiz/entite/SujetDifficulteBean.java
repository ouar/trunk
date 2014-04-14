package fr.gfi.quiz.entite;

import fr.gfi.quiz.entite.hibernate.Sujet;

public class SujetDifficulteBean {

	private Sujet sujet;

	public SujetDifficulteBean() {
		super();
	}

	public SujetDifficulteBean(Sujet sujet) {
		this();
		this.sujet = sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public Sujet getSujet() {
		return sujet;
	}

}
