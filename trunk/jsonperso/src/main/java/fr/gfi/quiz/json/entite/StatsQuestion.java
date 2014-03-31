package fr.gfi.quiz.json.entite;

import java.io.Serializable;

public class StatsQuestion extends StatsAbstract implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3771789958271533901L;

	public StatsQuestion() {

	}

	public StatsQuestion(int nbPointsDispos, int nbPointsObtenus,
			int nbBonnesReponses, int nbMauvaisesReponses, int nbReponsesNonTrouvees ) {
		super();
		this.nbPointsDispos = nbPointsDispos;
		this.nbPointsObtenus = nbPointsObtenus;
		this.nbBonnesReponses = nbBonnesReponses;
		this.nbMauvaisesReponses = nbMauvaisesReponses;
		this.nbReponsesNonTrouvees = nbReponsesNonTrouvees;
	}

}
