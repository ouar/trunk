package fr.gfi.quiz.entite;

import java.io.Serializable;

import fr.gfi.quiz.entite.hibernate.Sujet;



public class BeanNiveauTypeSujet implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 8482605223063445534L;

	private Sujet sujet;


	public BeanNiveauTypeSujet() {
		super();
		sujet=new Sujet();

	}

	public BeanNiveauTypeSujet(Sujet Sujet) {
		super();
		this.sujet = Sujet;
	}

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet Sujet) {
		this.sujet = Sujet;
	}

}
