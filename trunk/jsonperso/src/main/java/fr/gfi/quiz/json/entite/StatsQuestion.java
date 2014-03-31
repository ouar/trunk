package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class StatsQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771789958271533901L;
	//nombre de point dépendant de la difficulté de la question
	private int nbPointDisponible=0;
	//nombre de point obtenu pour la question
	private int nbPointObtenu=0;
	//nombre de réponses bonnes
	//pour les réponses fausses, elles ne doivent pas être choisies par le candidat
	private int nbReponseOK=0;
	//nombre de réponse fausses
	//réponses correctes non saisies, réponses fausses choisies
	private int nbReponseKO=0;
	
	public StatsQuestion() {
		
	}

	public StatsQuestion(int nbPointDisponible, int nbPointObtenu,
			int nbReponseOK, int nbReponseKO) {
		super();
		this.nbPointDisponible = nbPointDisponible;
		this.nbPointObtenu = nbPointObtenu;
		this.nbReponseOK = nbReponseOK;
		this.nbReponseKO = nbReponseKO;
	}

	public int getNbPointDisponible() {
		return nbPointDisponible;
	}

	public void setNbPointDisponible(int nbPointDisponible) {
		this.nbPointDisponible = nbPointDisponible;
	}

	public int getNbPointObtenu() {
		return nbPointObtenu;
	}

	public void setNbPointObtenu(int nbPointObtenu) {
		this.nbPointObtenu = nbPointObtenu;
	}

	public int getNbReponseOK() {
		return nbReponseOK;
	}

	public void setNbReponseOK(int nbReponseOK) {
		this.nbReponseOK = nbReponseOK;
	}

	public int getNbReponseKO() {
		return nbReponseKO;
	}

	public void setNbReponseKO(int nbReponseKO) {
		this.nbReponseKO = nbReponseKO;
	}

	
	
}
