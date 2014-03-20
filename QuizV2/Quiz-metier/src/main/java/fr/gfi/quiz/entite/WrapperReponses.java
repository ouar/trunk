package fr.gfi.quiz.entite;

public class WrapperReponses {
	private String libReponseParametre;
	private Integer idReponseParametre;
	private boolean hasReponseCandidat;
	private boolean reponseCorrecte;

	public WrapperReponses(String libReponseParametre, Integer idReponseParametre) {
		this.libReponseParametre = libReponseParametre;
		this.idReponseParametre = idReponseParametre;
	}

	public WrapperReponses(String libReponseParametre, Integer idReponseParametre, boolean hasReponseCandidat, boolean isReponseCorrecte) {
		this.libReponseParametre = libReponseParametre;
		this.idReponseParametre = idReponseParametre;
		this.hasReponseCandidat = hasReponseCandidat;
		this.reponseCorrecte = isReponseCorrecte;
	}

	/**
	 * @return the libReponseParametre
	 */
	public String getLibReponseParametre() {
		return libReponseParametre;
	}

	/**
	 * @param libReponseParametre
	 *            the libReponseParametre to set
	 */
	public void setLibReponseParametre(String libReponseParametre) {
		this.libReponseParametre = libReponseParametre;
	}

	/**
	 * @return the idReponseParametre
	 */
	public Integer getIdReponseParametre() {
		return idReponseParametre;
	}

	/**
	 * @param idReponseParametre
	 *            the idReponseParametre to set
	 */
	public void setIdReponseParametre(Integer idReponseParametre) {
		this.idReponseParametre = idReponseParametre;
	}

	/**
	 * @return the hasReponseCandidat
	 */
	public boolean isHasReponseCandidat() {
		return hasReponseCandidat;
	}

	/**
	 * @param hasReponseCandidat
	 *            the hasReponseCandidat to set
	 */
	public void setHasReponseCandidat(boolean hasReponseCandidat) {
		this.hasReponseCandidat = hasReponseCandidat;
	}

	/**
	 * @return the reponseCorrecte
	 */
	public boolean isReponseCorrecte() {
		return reponseCorrecte;
	}

	/**
	 * @param reponseCorrecte
	 *            the reponseCorrecte to set
	 */
	public void setReponseCorrecte(boolean reponseCorrecte) {
		this.reponseCorrecte = reponseCorrecte;
	}

}
