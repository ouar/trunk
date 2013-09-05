package fr.gfi.cmg.QuizzCmg.metier.beans;

import java.util.List;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;

public class Questionnaire {

	private Quizz quizz;
	private List<Question> lQuestions;

	/**
	 * @return the quizz
	 */
	public Quizz getQuizz() {
		return quizz;
	}

	/**
	 * @param quizz
	 *            the quizz to set
	 */
	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

	/**
	 * @return the lQuestions
	 */
	public List<Question> getlQuestions() {
		return lQuestions;
	}

	/**
	 * @param lQuestions
	 *            the lQuestions to set
	 */
	public void setlQuestions(List<Question> lQuestions) {
		this.lQuestions = lQuestions;
	}
}
