package fr.gfi.quiz.entite;



import java.util.List;

import fr.gfi.quiz.entite.hibernate.User;
import fr.gfi.quiz.json.entite.ChoixQuiz;

public class InfoGenerationQuizz {

	private List<ChoixQuiz> lChoix;
	private User  user;
	private String nomCandidat;
	private String prenomCandidat;
	//private List<BeanNiveauTypeSujet> listNiveauTypeSujet;
	
	public InfoGenerationQuizz() {

	}

	public InfoGenerationQuizz(List<ChoixQuiz> lChoix2, User user, String nomCandidat, String prenomCandidat) {
		this.lChoix = lChoix2;
		this.user = user;
		this.nomCandidat = nomCandidat;
		this.prenomCandidat = prenomCandidat;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the nomCandidat
	 */
	public String getNomCandidat() {
		return nomCandidat;
	}

	/**
	 * @param nomCandidat
	 *            the nomCandidat to set
	 */
	public void setNomCandidat(String nomCandidat) {
		this.nomCandidat = nomCandidat;
	}

	/**
	 * @return the prenomCandidat
	 */
	public String getPrenomCandidat() {
		return prenomCandidat;
	}

	/**
	 * @param prenomCandidat
	 *            the prenomCandidat to set
	 */
	public void setPrenomCandidat(String prenomCandidat) {
		this.prenomCandidat = prenomCandidat;
	}

//	public List<BeanNiveauTypeSujet> getListNiveauTypeSujet() {
//		return listNiveauTypeSujet;
//	}
//
//	public void setListNiveauTypeSujet(List<BeanNiveauTypeSujet> listNiveauTypeSujet) {
//		this.listNiveauTypeSujet = listNiveauTypeSujet;
//	}


	public List<ChoixQuiz> getlChoix() {
		return lChoix;
	}

	public void setlChoix(List<ChoixQuiz> lChoix) {
		this.lChoix = lChoix;
	}
}
