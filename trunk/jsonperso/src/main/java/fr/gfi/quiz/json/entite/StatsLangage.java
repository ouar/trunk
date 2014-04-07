package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.gfi.quiz.entite.PairInt;

public class StatsLangage extends StatsAbstract implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1949212748982190820L;
	private IdLibelle langage;
	private Map<PairInt,StatsSujet> mSujets;


	public StatsLangage() {
		mSujets = new HashMap<PairInt,StatsSujet>();
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
	}

	public Map<PairInt, StatsSujet> getmSujets() {
		return mSujets;
	}

	public void setmSujets(Map<PairInt, StatsSujet> mSujets) {
		this.mSujets = mSujets;
	}

	public void process(){
		if(mSujets.size()>0){
			for(PairInt mapKey : mSujets.keySet()){
				StatsSujet sujetTemp = mSujets.get(mapKey);
				nbQuestionsKO+=sujetTemp.nbQuestionsKO;
				nbQuestionsOK+=sujetTemp.nbQuestionsOK;
				nbQuestionsNonRepondues+=sujetTemp.nbQuestionsNonRepondues;
				nbQuestionsPartielles+=sujetTemp.nbQuestionsPartielles;

				nbPointsDispos+=sujetTemp.getNbPointsDispos();
				nbPointsObtenus+=sujetTemp.getNbPointsObtenus();
				nbPointsPerdusMauvaiseReponse+=sujetTemp.nbPointsPerdusMauvaiseReponse;
				nbPointsPerdusQuestionNonRepondue+=sujetTemp.nbPointsPerdusQuestionNonRepondue;
				nbPointsPerdusQuestionPartielle+=sujetTemp.nbPointsPerdusQuestionPartielle;

				nbBonnesReponses+=sujetTemp.getNbBonnesReponses();
				nbMauvaisesReponses+=sujetTemp.getNbMauvaisesReponses();
				nbReponsesNonTrouvees+=sujetTemp.getNbReponsesNonTrouvees();
			}
		}
	}


}
