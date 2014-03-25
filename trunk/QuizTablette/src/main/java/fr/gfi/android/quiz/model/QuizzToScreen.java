package fr.gfi.android.quiz.model;

import fr.gfi.quiz.json.entite.Quiz;

public class QuizzToScreen {

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
	
	
	
}
