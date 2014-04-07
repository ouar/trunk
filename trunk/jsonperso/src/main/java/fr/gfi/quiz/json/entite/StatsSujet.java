package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatsSujet extends StatsAbstract implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8248299183306733123L;
	private IdLibelle sujet;
	private IdLibelle difficulte;
	private List<StatsQuestion> lStatsQuestions;

	public StatsSujet() {
		lStatsQuestions = new ArrayList<StatsQuestion>();
	}

	public StatsSujet(IdLibelle sujet, IdLibelle difficulte, int noteSur20,
			int nbQuestionsOK, int nbQuestionsKO, int nbQuestionsPartielles, int nbQuestionsTotales) {
		super();
		this.sujet = sujet;
		this.difficulte = difficulte;
	}

	public IdLibelle getSujet() {
		return sujet;
	}
	public void setSujet(IdLibelle sujet) {
		this.sujet = sujet;
	}
	public IdLibelle getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}



	public List<StatsQuestion> getlStatsQuestions() {
		return lStatsQuestions;
	}

	public void setlStatsQuestions(List<StatsQuestion> lStatsQuestions) {
		this.lStatsQuestions = lStatsQuestions;
	}




	public void addStatsQuestion(StatsQuestion statsQuestion){
		lStatsQuestions.add(statsQuestion);
		nbQuestionsKO+=statsQuestion.nbQuestionsKO;
		nbQuestionsOK+=statsQuestion.nbQuestionsOK;
		nbQuestionsNonRepondues+=statsQuestion.nbQuestionsNonRepondues;
		nbQuestionsPartielles+=statsQuestion.nbQuestionsPartielles;

		nbPointsDispos+=statsQuestion.getNbPointsDispos();
		nbPointsObtenus+=statsQuestion.getNbPointsObtenus();
		nbPointsPerdusMauvaiseReponse+=statsQuestion.nbPointsPerdusMauvaiseReponse;
		nbPointsPerdusQuestionNonRepondue+=statsQuestion.nbPointsPerdusQuestionNonRepondue;
		nbPointsPerdusQuestionPartielle+=statsQuestion.nbPointsPerdusQuestionPartielle;

		nbBonnesReponses+=statsQuestion.getNbBonnesReponses();
		nbMauvaisesReponses+=statsQuestion.getNbMauvaisesReponses();
		nbReponsesNonTrouvees+=statsQuestion.getNbReponsesNonTrouvees();
	}

}
