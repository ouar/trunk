package fr.gfi.cmg.QuizzCmg.presentation.beans;

import java.io.Serializable;

public class ReponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private QuestionBean questionBean;
	private String libReponse;
	private Boolean bolTypeReponse;

	public ReponseBean() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuestionBean getQuestionBean() {
		return questionBean;
	}

	public void setQuestionBean(QuestionBean questionBean) {
		this.questionBean = questionBean;
	}

	public String getLibReponse() {
		return libReponse;
	}

	public void setLibReponse(String libReponse) {
		this.libReponse = libReponse;
	}

	public Boolean getBolTypeReponse() {
		return bolTypeReponse;
	}

	public void setBolTypeReponse(Boolean bolTypeReponse) {
		this.bolTypeReponse = bolTypeReponse;
	}

}
