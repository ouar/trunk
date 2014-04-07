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
	private List<StatsLangage> lStatsLangages;

	public StatsQuiz() {
		lStatsLangages = new ArrayList<StatsLangage>();
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

	public List<StatsLangage> getlStatsLangages() {
		return lStatsLangages;
	}

	public void setlStatsLangages(List<StatsLangage> lStatsLangages) {
		this.lStatsLangages = lStatsLangages;
	}

	public void process() {
		for(StatsLangage langage : lStatsLangages){
			langage.process();
			this.nbQuestionNonRepondue+=langage.getNbQuestionNonRepondue();
			this.nbBonnesReponses+=langage.getNbBonnesReponses();
			this.nbMauvaisesReponses+=langage.getNbMauvaisesReponses();
			this.nbPointsDispos+=langage.getNbPointsDispos();
			this.nbPointsObtenus+=langage.getNbPointsObtenus();
			this.nbReponsesNonTrouvees+=langage.getNbReponsesNonTrouvees();
		}
	}
}
