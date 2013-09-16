package fr.gfi.cmg.QuizzCmg.presentation.administration;

import java.io.Serializable;
import java.util.List;

import fr.gfi.cmg.QuizzCmg.presentation.beans.QuestionBean;

public class AdministrationFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906651985022713791L;

	private String[] libelleTypeSujet;
	
	private Integer idTypeSujet;

	private Integer idLangage;
	
	private Integer idQuestion;
	
	private Integer idNiveauQuestion=null;
	
	private String action;
	
	private String vueEncoursUtlisation;
	
	
	private String libelleLangage;
	
	private String libelleQuestion;
	
	private Integer dureeReflexion;
	
	private String[] reponse;

	
	private String libelleTypeSujetFiltree;
	
	private Object image;
	
	private List<QuestionBean> listQuestionsFiltres;
	
	
	private boolean typeReponse;
	
	

	public AdministrationFormBean() {

	}


	public String[] getLibelleTypeSujet() {
		return libelleTypeSujet;
	}


	public void setLibelleTypeSujet(String[] libelleTypeSujet) {
		this.libelleTypeSujet = libelleTypeSujet;
	}


	public Integer getIdTypeSujet() {
		return idTypeSujet;
	}


	public void setIdTypeSujet(Integer idTypeSujet) {
		this.idTypeSujet = idTypeSujet;
	}


	public Integer getIdLangage() {
		return idLangage;
	}


	public void setIdLangage(Integer idLangage) {
		this.idLangage = idLangage;
	}


	public Integer getIdQuestion() {
		return idQuestion;
	}


	public void setIdQuestion(Integer idQuestion) {
		this.idQuestion = idQuestion;
	}


	public Integer getIdNiveauQuestion() {
		return idNiveauQuestion;
	}


	public void setIdNiveauQuestion(Integer idNiveauQuestion) {
		this.idNiveauQuestion = idNiveauQuestion;
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


	public String getLibelleQuestion() {
		return libelleQuestion;
	}


	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}


	public Integer getDureeReflexion() {
		return dureeReflexion;
	}


	public void setDureeReflexion(Integer dureeReflexion) {
		this.dureeReflexion = dureeReflexion;
	}


	public String[] getReponse() {
		return reponse;
	}


	public void setReponse(String[] reponse) {
		this.reponse = reponse;
	}


	public String getLibelleTypeSujetFiltree() {
		return libelleTypeSujetFiltree;
	}


	public void setLibelleTypeSujetFiltree(String libelleTypeSujetFiltree) {
		this.libelleTypeSujetFiltree = libelleTypeSujetFiltree;
	}


	public Object getImage() {
		return image;
	}


	public void setImage(Object image) {
		this.image = image;
	}


	public List<QuestionBean> getListQuestionsFiltres() {
		return listQuestionsFiltres;
	}


	public void setListQuestionsFiltres(List<QuestionBean> listQuestionsFiltres) {
		this.listQuestionsFiltres = listQuestionsFiltres;
	}


	public void reset() {
		
		libelleTypeSujet=null;
		reponse=null;
		libelleQuestion=null;
		dureeReflexion=null;
		idNiveauQuestion=null;
	}


	public boolean isTypeReponse() {
		return typeReponse;
	}


	public void setTypeReponse(boolean typeReponse) {
		this.typeReponse = typeReponse;
	}


	

}
