package fr.gfi.cmg.QuizzCmg.metier.beans;



import java.util.List;


import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;

public class InfoGenerationQuizz {

	private List<SujetDifficulteBean> lSujetDifficulte;
	private User  user;
	private String nomCandidat;
	private String prenomCandidat;
	private List<BeanNiveauTypeSujet> listNiveauTypeSujet;

	public InfoGenerationQuizz() {

	}

	public InfoGenerationQuizz(List<SujetDifficulteBean> lSujetDifficulte, User user, String nomCandidat, String prenomCandidat) {
		this.setlSujetDifficulte(lSujetDifficulte);
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

	public List<BeanNiveauTypeSujet> getListNiveauTypeSujet() {
		return listNiveauTypeSujet;
	}

	public void setListNiveauTypeSujet(List<BeanNiveauTypeSujet> listNiveauTypeSujet) {
		this.listNiveauTypeSujet = listNiveauTypeSujet;
	}

	public void setlSujetDifficulte(List<SujetDifficulteBean> lSujetDifficulte) {
		this.lSujetDifficulte = lSujetDifficulte;
	}

	public List<SujetDifficulteBean> getlSujetDifficulte() {
		return lSujetDifficulte;
	}

}
