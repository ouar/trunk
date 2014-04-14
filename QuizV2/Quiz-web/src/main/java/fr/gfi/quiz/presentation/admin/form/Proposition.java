package fr.gfi.quiz.presentation.admin.form;

import java.io.Serializable;

public class Proposition implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1295576917591020901L;
	String sReponse;
	boolean vrai;


	public Proposition() {
		super();
	}



	public Proposition(String sReponse, boolean vrai) {
		super();
		this.sReponse = sReponse;
		this.vrai = vrai;
	}



	public String getsReponse() {
		return sReponse;
	}
	public void setsReponse(String sReponse) {
		this.sReponse = sReponse;
	}
	public boolean isVrai() {
		return vrai;
	}
	public void setVrai(boolean vrai) {
		this.vrai = vrai;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sReponse == null) ? 0 : sReponse.hashCode());
		result = prime * result + (vrai ? 1231 : 1237);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (sReponse == null) {
			if (other.sReponse != null)
				return false;
		} else if (!sReponse.equals(other.sReponse))
			return false;
		if (vrai != other.vrai)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Proposition [sReponse=" + sReponse + ", vrai=" + vrai + "]";
	}



}
