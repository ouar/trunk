package fr.gfi.cmg.QuizzCmg.metier.beans;

public class InfoReponseQuestion {

	private String libReponse;
	private boolean isBonneReponse;

	public InfoReponseQuestion() {

	}

	/**
	 * @param libReponse
	 * @param isBonneReponse
	 */
	public InfoReponseQuestion(String libReponse, boolean isBonneReponse) {
		this.libReponse = libReponse;
		this.isBonneReponse = isBonneReponse;
	}

	/**
	 * @return the libReponse
	 */
	public String getLibReponse() {
		return libReponse;
	}

	/**
	 * @param libReponse
	 *            the libReponse to set
	 */
	public void setLibReponse(String libReponse) {
		this.libReponse = libReponse;
	}

	/**
	 * @return the isBonneReponse
	 */
	public boolean isBonneReponse() {
		return isBonneReponse;
	}

	/**
	 * @param isBonneReponse
	 *            the isBonneReponse to set
	 */
	public void setBonneReponse(boolean isBonneReponse) {
		this.isBonneReponse = isBonneReponse;
	}

}
