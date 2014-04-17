package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatsQuiz extends StatsAbstract implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1303935601711071379L;
	private Personne examinateur;
	private Personne candidat;
	private Date date;

	private int dureePrevue;
	private int dureeUtilisee;
	private List<StatsTheme> lStatsLangages;

	public StatsQuiz() {
		lStatsLangages = new ArrayList<StatsTheme>();
	}

	public Personne getExaminateur() {
		return examinateur;
	}

	public void setExaminateur(Personne examinateur) {
		this.examinateur = examinateur;
	}

	public Personne getCandidat() {
		return candidat;
	}

	public void setCandidat(Personne candidat) {
		this.candidat = candidat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDureePrevue() {
		return dureePrevue;
	}

	public void setDureePrevue(int dureePrevue) {
		this.dureePrevue = dureePrevue;
	}

	public int getDureeUtilisee() {
		return dureeUtilisee;
	}

	public void setDureeUtilisee(int dureeUtilisee) {
		this.dureeUtilisee = dureeUtilisee;
	}

	public List<StatsTheme> getlStatsLangages() {
		return lStatsLangages;
	}

	public void setlStatsLangages(List<StatsTheme> lStatsLangages) {
		this.lStatsLangages = lStatsLangages;
	}

	public void process() {
		for(StatsTheme langage : lStatsLangages){
			langage.process();
			nbQuestionsKO+=langage.nbQuestionsKO;
			nbQuestionsOK+=langage.nbQuestionsOK;
			nbQuestionsNonRepondues+=langage.nbQuestionsNonRepondues;
			nbQuestionsPartielles+=langage.nbQuestionsPartielles;

			nbPointsDispos+=langage.getNbPointsDispos();
			nbPointsObtenus+=langage.getNbPointsObtenus();
			nbPointsPerdusMauvaiseReponse+=langage.nbPointsPerdusMauvaiseReponse;
			nbPointsPerdusQuestionNonRepondue+=langage.nbPointsPerdusQuestionNonRepondue;
			nbPointsPerdusQuestionPartielle+=langage.nbPointsPerdusQuestionPartielle;

			nbBonnesReponses+=langage.getNbBonnesReponses();
			nbMauvaisesReponses+=langage.getNbMauvaisesReponses();
			nbReponsesNonTrouvees+=langage.getNbReponsesNonTrouvees();
		}
	}
}
