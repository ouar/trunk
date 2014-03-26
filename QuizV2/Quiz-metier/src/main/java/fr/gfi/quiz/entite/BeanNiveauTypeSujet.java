package fr.gfi.quiz.entite;

import java.io.Serializable;

import fr.gfi.quiz.entite.hibernate.TypeSujet;



public class BeanNiveauTypeSujet implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 8482605223063445534L;

	private TypeSujet typeSujet;


	public BeanNiveauTypeSujet() {
		super();
		typeSujet=new TypeSujet();

	}

	public BeanNiveauTypeSujet(TypeSujet typeSujet) {
		super();
		this.typeSujet = typeSujet;
	}

	public TypeSujet getTypeSujet() {
		return typeSujet;
	}

	public void setTypeSujet(TypeSujet typeSujet) {
		this.typeSujet = typeSujet;
	}

}
