package fr.gfi.cmg.QuizzCmg.persistance.util;

import java.io.Serializable;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.NiveauQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;

public class BeanNiveauTypeSujet implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8482605223063445534L;

	private TypeSujet typeSujet;
	
	private NiveauQuestion niveauQuestion;

	
	
	public BeanNiveauTypeSujet() {
		super();
		typeSujet=new TypeSujet();
		niveauQuestion=new NiveauQuestion();
		
	}

	public BeanNiveauTypeSujet(TypeSujet typeSujet, NiveauQuestion niveauQuestion) {
		super();
		this.typeSujet = typeSujet;
		this.niveauQuestion = niveauQuestion;
	}

	public TypeSujet getTypeSujet() {
		return typeSujet;
	}

	public void setTypeSujet(TypeSujet typeSujet) {
		this.typeSujet = typeSujet;
	}

	public NiveauQuestion getNiveauQuestion() {
		return niveauQuestion;
	}

	public void setNiveauQuestion(NiveauQuestion niveauQuestion) {
		this.niveauQuestion = niveauQuestion;
	}
	
	
}
