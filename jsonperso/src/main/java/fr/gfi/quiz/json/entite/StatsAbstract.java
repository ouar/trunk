package fr.gfi.quiz.json.entite;

public class StatsAbstract {
	protected int nbPointsDispos=0;
	protected int nbPointsObtenus=0;
	
	protected int nbQuestionNonRepondue=0;
	protected int nbBonnesReponses=0;
	protected int nbMauvaisesReponses=0;
	protected int nbReponsesNonTrouvees=0;

	public StatsAbstract() {
		super();
	}

	public int getNbPointsPerdus() {
		return nbPointsDispos-nbPointsObtenus;
	}

	public int getNoteSur20() {
		return Math.round((nbPointsObtenus*20f)/nbPointsDispos);
	}
	
	public int getNbPointsDispos() {
		return nbPointsDispos;
	}

	public void setNbPointsDispos(int nbPointsDispos) {
		this.nbPointsDispos = nbPointsDispos;
	}

	public int getNbPointsObtenus() {
		return nbPointsObtenus;
	}

	public void setNbPointsObtenus(int nbPointsObtenus) {
		this.nbPointsObtenus = nbPointsObtenus;
	}

	public int getNbBonnesReponses() {
		return nbBonnesReponses;
	}

	public void setNbBonnesReponses(int nbBonnesReponses) {
		this.nbBonnesReponses = nbBonnesReponses;
	}

	public int getNbMauvaisesReponses() {
		return nbMauvaisesReponses;
	}

	public void setNbMauvaisesReponses(int nbMauvaisesReponses) {
		this.nbMauvaisesReponses = nbMauvaisesReponses;
	}

	public int getNbReponsesNonTrouvees() {
		return nbReponsesNonTrouvees;
	}

	public void setNbReponsesNonTrouvees(int nbReponsesNonTrouvees) {
		this.nbReponsesNonTrouvees = nbReponsesNonTrouvees;
	}

	public int getNbQuestionNonRepondue() {
		return nbQuestionNonRepondue;
	}

	public void setNbQuestionNonRepondue(int nbQuestionNonRepondue) {
		this.nbQuestionNonRepondue = nbQuestionNonRepondue;
	}

}
