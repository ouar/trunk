package fr.gfi.cmg.QuizzCmg.presentation.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;

public class QuestionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SujetBean sujetBean;
	private DifficulteBean difficulteBean;
	private String libQuestion;
	private Date datCreation;
	private Integer intDureeReflexion;
	private Boolean bolUniqueReponse;
	private String urlImage;
	private JSONArray reponses = new JSONArray();
	private Boolean isValid;

	public QuestionBean() {

	}

	public QuestionBean(Question question) {

		this.id = question.getId();
		this.libQuestion = question.getLibQuestion();
		this.datCreation = question.getDatCreation();
		this.isValid = question.getIsValid();

		this.intDureeReflexion = question.getIntDureeReflexion();
		this.bolUniqueReponse = question.getBolUniqueReponse();
		this.urlImage = question.getUrlImage();

		this.difficulteBean = new DifficulteBean();
		this.difficulteBean.setId(question.getNiveauQuestion().getId());
		this.difficulteBean.setLibelle(question.getNiveauQuestion().getLibNiveau());

		this.sujetBean = new SujetBean();
		this.sujetBean.setId(question.getTypeSujet().getId());
		this.sujetBean.setLibelle(question.getTypeSujet().getLibelle());
		

		Set<Reponse> reponses = question.getReponses();

		if (reponses != null && reponses.size() > 0) {

			for (Reponse reponse : reponses) {

				ReponseBean reponseBean = new ReponseBean();

				reponseBean.setId(reponse.getId());
				reponseBean.setLibReponse(reponse.getLibReponse());
				reponseBean.setBolTypeReponse(reponse.getBolTypeReponse());

				JSONObject json = new JSONObject(reponseBean);

				this.reponses.put(json);

			}
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SujetBean getSujetBean() {
		return sujetBean;
	}

	public void setSujetBean(SujetBean sujetBean) {
		this.sujetBean = sujetBean;
	}

	public DifficulteBean getDifficulteBean() {
		return difficulteBean;
	}

	public void setDifficulteBean(DifficulteBean difficulteBean) {
		this.difficulteBean = difficulteBean;
	}

	public String getLibQuestion() {
		return libQuestion;
	}

	public void setLibQuestion(String libQuestion) {
		this.libQuestion = libQuestion;
	}

	public Date getDatCreation() {
		return datCreation;
	}

	public void setDatCreation(Date datCreation) {
		this.datCreation = datCreation;
	}

	public Integer getIntDureeReflexion() {
		return intDureeReflexion;
	}

	public void setIntDureeReflexion(Integer intDureeReflexion) {
		this.intDureeReflexion = intDureeReflexion;
	}

	public Boolean getBolUniqueReponse() {
		return bolUniqueReponse;
	}

	public void setBolUniqueReponse(Boolean bolUniqueReponse) {
		this.bolUniqueReponse = bolUniqueReponse;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public JSONArray getReponses() {
		return reponses;
	}

	public void setReponses(JSONArray reponses) {
		this.reponses = reponses;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

}
