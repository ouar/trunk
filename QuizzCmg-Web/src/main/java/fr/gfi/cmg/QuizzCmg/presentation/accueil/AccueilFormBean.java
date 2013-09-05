package fr.gfi.cmg.QuizzCmg.presentation.accueil;

import java.io.Serializable;

/**
 * 
 * @author seouar
 *
 */

public class AccueilFormBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1722906284606515269L;
	/**
	 * 
	 */

	private String pass = null;
	private String user = null;

	public AccueilFormBean() {
	}

	public String getPass() {
		return this.pass;
	}

	public String getUser() {
		return this.user;
	}


	public void reset() {
		this.user = null;
		this.pass = null;
	}

	public void setPass(String sPass) {
		this.pass = sPass;
	}

	public void setUser(String sUser) {
		this.user = sUser;
	}

}
