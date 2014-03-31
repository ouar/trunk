package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.List;

public class StatsSujet implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8248299183306733123L;
	private IdLibelle sujet;
	private IdLibelle difficulte;
	private int nbPointsDispos;
	private int nbPointsObtenus;
	private int nbBonnesReponses;
	private int nbMauvaisesReponses;
	private List<StatsQuestion> lStatsQuestions;

	public StatsSujet() {

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

	public int getNbPointsDispo() {
		return nbPointsDispos;
	}

	public void setNbPointsDispo(int nbPointsDispo) {
		this.nbPointsDispos = nbPointsDispo;
	}

	public int getNbPointsObtenus() {
		return nbPointsObtenus;
	}

	public void setNbPointsObtenus(int nbPointsObtenus) {
		this.nbPointsObtenus = nbPointsObtenus;
	}

	public List<StatsQuestion> getlStatsQuestions() {
		return lStatsQuestions;
	}

	public void setlStatsQuestions(List<StatsQuestion> lStatsQuestions) {
		this.lStatsQuestions = lStatsQuestions;
	}
	
	public void addStatsQuestion(StatsQuestion statsQuestion){
		lStatsQuestions.add(statsQuestion);
		nbPointsDispos+=statsQuestion.getNbPointDisponible();
		nbPointsObtenus+=statsQuestion.getNbPointObtenu();
		nbBonnesReponses+=statsQuestion.getNbReponseOK();
		nbMauvaisesReponses+=statsQuestion.getNbReponseKO();
	}


}