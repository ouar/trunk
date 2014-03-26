package fr.gfi.android.quiz.model;

import java.io.Serializable;

import fr.gfi.quiz.json.entite.Quiz;

public class QuizzToScreen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 222805380891584522L;
	public static final int ETAT_INDETERMINE 		= 0;
	public static final int ETAT_EN_COURS 			= 1;
	public static final int ETAT_EN_CORRECTION 		= 2;
	public static final int ETAT_TERMINE	 		= 3;

	int etatQuizz = ETAT_INDETERMINE;
	int idQuestionAffichee;
	Quiz quiz;
	
	public QuizzToScreen() {
		super();
	}
	
	public QuizzToScreen(int etatQuizz, int idQuestionAffichee, Quiz quiz) {
		this();
		this.etatQuizz = etatQuizz;
		this.idQuestionAffichee = idQuestionAffichee;
		this.quiz = quiz;
	}

	public int getEtatQuizz() {
		return etatQuizz;
	}
	public void setEtatQuizz(int etatQuizz) {
		this.etatQuizz = etatQuizz;
	}
	public int getIdQuestionAffichee() {
		return idQuestionAffichee;
	}
	public void setIdQuestionAffichee(int idQuestionAffichee) {
		this.idQuestionAffichee = idQuestionAffichee;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + etatQuizz;
		result = prime * result + idQuestionAffichee;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
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
		QuizzToScreen other = (QuizzToScreen) obj;
		if (etatQuizz != other.etatQuizz)
			return false;
		if (idQuestionAffichee != other.idQuestionAffichee)
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}
	
	
	
}
