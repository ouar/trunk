package fr.gfi.cmg.QuizzCmg.presentation.exceptions;

public class ActionException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4694624811005995731L;

	/**
	 * @param message
	 */
	public ActionException(String message) {
		super(message);
	}

	/**
	 * @param exceptionOrigine
	 */
	public ActionException(Throwable exceptionOrigine) {
		super(exceptionOrigine);
	}

	/**
	 * @param message
	 * @param exceptionOrigine
	 */
	public ActionException(String message, Throwable exceptionOrigine) {
		super(message, exceptionOrigine);
	}
}
