package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.io.Serializable;

public class AdministrationFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906651985022713791L;

	private String libelleTypeSujet;

	private int idLangage;
	
	private int idQuestion;
	
	private String action;
	
	private String vueEncoursUtlisation;

	public AdministrationFormBean() {

	}

	public String getLibelleTypeSujet() {
		return libelleTypeSujet;
	}

	public void setLibelleTypeSujet(String libelleTypeSujet) {
		this.libelleTypeSujet = libelleTypeSujet;
	}

	public int getIdLangage() {
		return idLangage;
	}

	public void setIdLangage(int idLangage) {
		this.idLangage = idLangage;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getVueEncoursUtlisation() {
		return vueEncoursUtlisation;
	}

	public void setVueEncoursUtlisation(String vueEncoursUtlisation) {
		this.vueEncoursUtlisation = vueEncoursUtlisation;
	}
	
	

}
