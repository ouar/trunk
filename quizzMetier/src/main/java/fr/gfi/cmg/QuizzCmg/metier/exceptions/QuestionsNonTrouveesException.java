/**
 * 
 */
package fr.gfi.cmg.QuizzCmg.metier.exceptions;

/**
 * @author ehabumuremyi-e
 * 
 */
public class QuestionsNonTrouveesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7844360025994899260L;

	/**
	 * @param message
	 */
	public QuestionsNonTrouveesException(String message) {
		super(message);
	}

	/**
	 * @param exceptionOrigine
	 */
	public QuestionsNonTrouveesException(Throwable exceptionOrigine) {
		super(exceptionOrigine);
	}

	/**
	 * @param message
	 * @param exceptionOrigine
	 */
	public QuestionsNonTrouveesException(String message, Throwable exceptionOrigine) {
		super(message, exceptionOrigine);
	}

}
