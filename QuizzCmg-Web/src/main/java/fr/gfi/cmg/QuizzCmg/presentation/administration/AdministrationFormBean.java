package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.io.Serializable;

public class AdministrationFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906651985022713791L;

	private String libelleTypeSujet;
	
	private int idTypeSujet;

	private int idLangage;
	
	private int idQuestion;
	
	private int idNiveauQuestion;
	
	private String action;
	
	private String vueEncoursUtlisation;
	
	
	private String libelleLangage;
	
	private String libelleQuestion;
	
	private int dureeReflexion;
	
	private String[] reponse;
//	private String reponse2;
//	private String reponse3;
//	private String reponse4;
	
	private String image;
	


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

	public String getLibelleLangage() {
		return libelleLangage;
	}

	public void setLibelleLangage(String libelleLangage) {
		this.libelleLangage = libelleLangage;
	}

	public int getIdTypeSujet() {
		return idTypeSujet;
	}

	public void setIdTypeSujet(int idTypeSujet) {
		this.idTypeSujet = idTypeSujet;
	}

	public String getLibelleQuestion() {
		return libelleQuestion;
	}

	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}

	public int getDureeReflexion() {
		return dureeReflexion;
	}

	public void setDureeReflexion(int dureeReflexion) {
		this.dureeReflexion = dureeReflexion;
	}



	public String[] getReponse() {
		return reponse;
	}

	public void setReponse(String[] reponse) {
		this.reponse = reponse;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIdNiveauQuestion() {
		return idNiveauQuestion;
	}

	public void setIdNiveauQuestion(int idNiveauQuestion) {
		this.idNiveauQuestion = idNiveauQuestion;
	}
	
	

}
