package fr.gfi.quiz.entite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.gfi.quiz.entite.hibernate.Difficulte;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Reponse;



public class InfoQuestion {

	private ChoixSujet sujet;
	private String libQuestion;
	private Difficulte nivDifficulte;
	private Integer dureeReflexionEnSec;
	private boolean has_unique_Response;
	private List<InfoReponseQuestion> listInfoReponseQuestion;

	public InfoQuestion() {

	}

	/**
	 * @param sujet
	 * @param libQuestion
	 * @param nivDifficulte
	 * @param dureeReflexionEnSec
	 * @param listInfoReponseQuestion
	 */
	public InfoQuestion(ChoixSujet sujet, String libQuestion, Difficulte nivDifficulte, Integer dureeReflexionEnSec,
			List<InfoReponseQuestion> listInfoReponseQuestion) {
		this.sujet = sujet;
		this.libQuestion = libQuestion;
		this.nivDifficulte = nivDifficulte;
		this.dureeReflexionEnSec = dureeReflexionEnSec;
		this.listInfoReponseQuestion = listInfoReponseQuestion;
	}

	/**
	 * @return the libQuestion
	 */
	public String getLibQuestion() {
		return libQuestion;
	}

	/**
	 * @param libQuestion
	 *            the libQuestion to set
	 */
	public void setLibQuestion(String libQuestion) {
		this.libQuestion = libQuestion;
	}

	/**
	 * @return the dureeReflexion
	 */
	public Integer getDureeReflexion() {
		return dureeReflexionEnSec;
	}

	/**
	 * @param dureeReflexion
	 *            the dureeReflexion to set
	 */
	public void setDureeReflexion(Integer dureeReflexion) {
		this.dureeReflexionEnSec = dureeReflexion;
	}

	/**
	 * @return the has_unique_Response
	 */
	public boolean isHas_unique_Response() {
		return has_unique_Response;
	}

	/**
	 * @param has_unique_Response
	 *            the has_unique_Response to set
	 */
	public void setHas_unique_Response(boolean has_unique_Response) {
		this.has_unique_Response = has_unique_Response;
	}

	/**
	 * @return the listInfoReponseQuestion
	 */
	public List<InfoReponseQuestion> getListInfoReponseQuestion() {
		return listInfoReponseQuestion;
	}

	/**
	 * @param listInfoReponseQuestion
	 *            the listInfoReponseQuestion to set
	 */
	public void setListInfoReponseQuestion(List<InfoReponseQuestion> listInfoReponseQuestion) {
		this.listInfoReponseQuestion = listInfoReponseQuestion;
	}

	/**
	 * @return the nivDifficulte
	 */
	public Difficulte getNivDifficulte() {
		return nivDifficulte;
	}

	/**
	 * @param nivDifficulte
	 *            the nivDifficulte to set
	 */
	public void setNivDifficulte(Difficulte nivDifficulte) {
		this.nivDifficulte = nivDifficulte;
	}

	public void setSujet(ChoixSujet sujet) {
		this.sujet = sujet;
	}

	public ChoixSujet getSujet() {
		return sujet;
	}

	public Question getQuestionHibernate() {
		Question question = new Question();

		// si l'utilisateur a choisi un sujet d�j� parametr� en base.
		if (getSujet().getSujetChoisi() != null) {
			question.setSujet(getSujet().getSujetChoisi());
		}

		question.setLibQuestion(getLibQuestion());
		question.setDatCreation(new Date());
		question.setIntDureeReflexion(getDureeReflexion());

		/*
		 * associer les r�ponses � une question.
		 */
		List<InfoReponseQuestion> listInfoReponseQuestion = getListInfoReponseQuestion();
		Set<Reponse> lReponses = construireListReponses(listInfoReponseQuestion);
		question.setReponses(lReponses);

		question.setBolUniqueReponse(isQuestionUniqueReponse(lReponses));

		return question;
	}

	/**
	 * Constitue un set des entit�s r�ponses en fct des r�ponses saisies par
	 * l'utilisateur.
	 *
	 * @param listInfoReponseQuestion
	 * @return
	 */
	private Set<Reponse> construireListReponses(List<InfoReponseQuestion> listInfoReponseQuestion) {
		Reponse reponse = null;
		List<Reponse> lReponses = new ArrayList<Reponse>();
		Set<Reponse> result = null;

		for (InfoReponseQuestion infoReponseQuestion : listInfoReponseQuestion) {

			reponse = new Reponse();
			reponse.setLibReponse(infoReponseQuestion.getLibReponse());
			reponse.setBolTypeReponse(infoReponseQuestion.isBonneReponse());
			lReponses.add(reponse);
		}

		result = new HashSet<Reponse>(lReponses);
		return result;

	}

	/**
	 * D�termine si une question a plusieurs r�ponses ou pas.
	 *
	 * @param lReponses
	 * @return
	 * @throws ReponseQuestionNonSaisieException
	 */
	private boolean isQuestionUniqueReponse(Set<Reponse> lReponses) {

		return lReponses.size() == 1;
	}
}
