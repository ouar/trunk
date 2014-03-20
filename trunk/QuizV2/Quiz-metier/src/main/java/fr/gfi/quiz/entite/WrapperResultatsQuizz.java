package fr.gfi.quiz.entite;

import java.util.List;

public class WrapperResultatsQuizz {

	private String libQuestion;
	private String libNiveau;
	private List<WrapperReponses> lWrapperReponses;

	public WrapperResultatsQuizz(String libQuestion, String libNiveau, List<WrapperReponses> lWrapperReponses) {
		this.libQuestion = libQuestion;
		this.libNiveau = libNiveau;
		this.lWrapperReponses = lWrapperReponses;
	}

	/**
	 * @return the libQuestion
	 */
	public String getLibQuestion() {
		return libQuestion;
	}

	/**
	 * @param libQuestion
	 *            the libQuestion to set
	 */
	public void setLibQuestion(String libQuestion) {
		this.libQuestion = libQuestion;
	}

	/**
	 * @return the libNiveau
	 */
	public String getLibNiveau() {
		return libNiveau;
	}

	/**
	 * @param libNiveau
	 *            the libNiveau to set
	 */
	public void setLibNiveau(String libNiveau) {
		this.libNiveau = libNiveau;
	}

	/**
	 * @return the lWrapperReponses
	 */
	public List<WrapperReponses> getlWrapperReponses() {
		return lWrapperReponses;
	}

	/**
	 * @param lWrapperReponses
	 *            the lWrapperReponses to set
	 */
	public void setlWrapperReponses(List<WrapperReponses> lWrapperReponses) {
		this.lWrapperReponses = lWrapperReponses;
	}

}
