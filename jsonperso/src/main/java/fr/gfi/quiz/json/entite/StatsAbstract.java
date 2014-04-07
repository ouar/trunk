package fr.gfi.quiz.json.entite;

public class StatsAbstract {

	protected int nbPointsDispos=0;
	protected int nbPointsObtenus=0;
	protected int nbPointsPerdusMauvaiseReponse=0;
	protected int nbPointsPerdusQuestionNonRepondue=0;
	protected int nbPointsPerdusQuestionPartielle=0;

	protected int nbQuestionsOK=0;
	protected int nbQuestionsPartielles=0;
	protected int nbQuestionsKO=0;
	protected int nbQuestionsNonRepondues=0;

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

	public int getNbPointsPerdusMauvaiseReponse() {
		return nbPointsPerdusMauvaiseReponse;
	}

	public void setNbPointsPerdusMauvaiseReponse(int nbPointsPerdusMauvaiseReponse) {
		this.nbPointsPerdusMauvaiseReponse = nbPointsPerdusMauvaiseReponse;
	}

	public int getNbPointsPerdusQuestionNonRepondue() {
		return nbPointsPerdusQuestionNonRepondue;
	}

	public void setNbPointsPerdusQuestionNonRepondue(
			int nbPointsPerdusQuestionNonRepondue) {
		this.nbPointsPerdusQuestionNonRepondue = nbPointsPerdusQuestionNonRepondue;
	}

	public int getNbQuestionsOK() {
		return nbQuestionsOK;
	}

	public void setNbQuestionsOK(int nbQuestionsOK) {
		this.nbQuestionsOK = nbQuestionsOK;
	}

	public int getNbQuestionsPartielles() {
		return nbQuestionsPartielles;
	}

	public void setNbQuestionsPartielles(int nbQuestionsPartielles) {
		this.nbQuestionsPartielles = nbQuestionsPartielles;
	}

	public int getNbQuestionsKO() {
		return nbQuestionsKO;
	}

	public void setNbQuestionsKO(int nbQuestionsKO) {
		this.nbQuestionsKO = nbQuestionsKO;
	}

	public int getNbQuestionsNonRepondues() {
		return nbQuestionsNonRepondues;
	}

	public void setNbQuestionsNonRepondues(int nbQuestionsNonRepondues) {
		this.nbQuestionsNonRepondues = nbQuestionsNonRepondues;
	}

	public int getNbPointsPerdusQuestionPartielle() {
		return nbPointsPerdusQuestionPartielle;
	}

	public void setNbPointsPerdusQuestionPartielle(
			int nbPointsPerdusQuestionPartielle) {
		this.nbPointsPerdusQuestionPartielle = nbPointsPerdusQuestionPartielle;
	}



}
