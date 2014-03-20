package fr.gfi.quiz.entite;

import java.util.List;
import java.util.Map;

import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.Reponse;



public class InfoReponseCandidat {

	private Quizz quizz;
	private Map<Question, List<Reponse>> mapQuestionsReponses;

	public InfoReponseCandidat() {

	}

	public InfoReponseCandidat(Quizz quizz, Map<Question, List<Reponse>> mapQuestionsReponses) {
		this.quizz = quizz;
		this.mapQuestionsReponses = mapQuestionsReponses;
	}

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
	 * @return the mapQuestionsReponses
	 */
	public Map<Question, List<Reponse>> getMapQuestionsReponses() {
		return mapQuestionsReponses;
	}

	/**
	 * @param mapQuestionsReponses
	 *            the mapQuestionsReponses to set
	 */
	public void setMapQuestionsReponses(Map<Question, List<Reponse>> mapQuestionsReponses) {
		this.mapQuestionsReponses = mapQuestionsReponses;
	}

}
