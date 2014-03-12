package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import fr.gfi.cmg.QuizzCmg.metier.beans.WrapperResultatsQuizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;


/**
 * @author seouar
 * 
 */
public class GestionFormBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8450454237339344293L;
	/**
	 * 
	 */
//	@CheckSpecialChars(allow = { SpecialChar.ACCOLADE_OUVRANTE, SpecialChar.ACCOLADE_FERMANTE, SpecialChar.POINT_VIRGULE, SpecialChar.VIRGULE,
//			SpecialChar.DEUX_POINTS, SpecialChar.GUILLEMET, SpecialChar.CROCHET_OUVRANT, SpecialChar.CROCHET_FERMANT,
//			SpecialChar.SLASH,SpecialChar.DIESE})
	private String jsonSujetDifficulte;
	private List<String> listTypeSujet;
	private Integer idNiveauQuestionnaire;
	private String nomCandidat;
	private String prenomCandidat;
	private Quizz quizz;
	private String reponsesCandidat;
	private List<WrapperResultatsQuizz> listWrapperResultatsQuizz;
	private Integer idQuizzAConsulter;
	private List<Quizz> listQuizz;
	private String password;

	private List<String> listIdTypesSujetSaisis = new ArrayList<String>();

	private List<Quizz> panierListQuizz = new ArrayList<Quizz>();
	private int idLangage;
	private String libelleLangage;
	public String json;

	List<BeanNiveauTypeSujet> listNiveauTypeSujetPanier = new ArrayList<BeanNiveauTypeSujet>();

	List<BeanNiveauTypeSujet> listNiveauTypeSujetAselectionner = new ArrayList<BeanNiveauTypeSujet>();

	private String urlFlashCode;

	/**
	 * @return the listTypeSujet
	 */
	public List<String> getListTypeSujet() {
		return listTypeSujet;
	}

	/**
	 * @param listTypeSujet
	 *            the listTypeSujet to set
	 */
	public void setListTypeSujet(List<String> listTypeSujet) {

		this.listTypeSujet = listTypeSujet;
	}

	/**
	 * @return the idNiveauQuestionnaire
	 */
	public Integer getIdNiveauQuestionnaire() {
		return idNiveauQuestionnaire;
	}

	/**
	 * @param idNiveauQuestionnaire
	 *            the idNiveauQuestionnaire to set
	 */
	public void setIdNiveauQuestionnaire(Integer idNiveauQuestionnaire) {
		this.idNiveauQuestionnaire = idNiveauQuestionnaire;
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

	/**
	 * @return the quizz
	 */
	public Quizz getQuizz() {
		return quizz;
	}

	/**
	 * @param quizz
	 *            the quizz to set
	 */
	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
	}

	/**
	 * @return the reponsesCandidat
	 */
	public String getReponsesCandidat() {
		return reponsesCandidat;
	}

	/**
	 * @param reponsesCandidat
	 *            the reponsesCandidat to set
	 */
	public void setReponsesCandidat(String reponsesCandidat) {
		this.reponsesCandidat = reponsesCandidat;
	}

	/**
	 * @return the listWrapperResultatsQuizz
	 */
	public List<WrapperResultatsQuizz> getListWrapperResultatsQuizz() {
		return listWrapperResultatsQuizz;
	}

	/**
	 * @param listWrapperResultatsQuizz
	 *            the listWrapperResultatsQuizz to set
	 */
	public void setListWrapperResultatsQuizz(List<WrapperResultatsQuizz> listWrapperResultatsQuizz) {
		this.listWrapperResultatsQuizz = listWrapperResultatsQuizz;
	}

	/**
	 * @return the idQuizzAConsulter
	 */
	public Integer getIdQuizzAConsulter() {
		return idQuizzAConsulter;
	}

	/**
	 * @param idQuizzAConsulter
	 *            the idQuizzAConsulter to set
	 */
	public void setIdQuizzAConsulter(Integer idQuizzAConsulter) {
		this.idQuizzAConsulter = idQuizzAConsulter;
	}

	/**
	 * @return the listQuizz
	 */
	public List<Quizz> getListQuizz() {
		return listQuizz;
	}

	/**
	 * @param listQuizz
	 *            the listQuizz to set
	 */
	public void setListQuizz(List<Quizz> listQuizz) {
		this.listQuizz = listQuizz;
	}

	/**
	 * @return the listIdTypesSujetSaisis
	 */
	public List<String> getListIdTypesSujetSaisis() {
		return listIdTypesSujetSaisis;
	}

	/**
	 * @param listIdTypesSujetSaisis
	 *            the listIdTypesSujetSaisis to set
	 */
	public void setListIdTypesSujetSaisis(List<String> listIdTypesSujetSaisis) {
		this.listIdTypesSujetSaisis = listIdTypesSujetSaisis;
	}

	public boolean isReadyToUse() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		this.listNiveauTypeSujetPanier = new ArrayList<BeanNiveauTypeSujet>();

		this.listNiveauTypeSujetAselectionner = new ArrayList<BeanNiveauTypeSujet>();

	}

	public int getIdLangage() {
		return idLangage;
	}

	public void setIdLangage(int idLangage) {
		this.idLangage = idLangage;
	}

	public List<Quizz> getPanierListQuizz() {
		return panierListQuizz;
	}

	public void setPanierListQuizz(List<Quizz> panierListQuizz) {
		this.panierListQuizz = panierListQuizz;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getLibelleLangage() {
		return libelleLangage;
	}

	public void setLibelleLangage(String libelleLangage) {
		this.libelleLangage = libelleLangage;
	}

	public List<BeanNiveauTypeSujet> getListNiveauTypeSujetPanier() {
		return listNiveauTypeSujetPanier;
	}

	public void setListNiveauTypeSujetPanier(List<BeanNiveauTypeSujet> listNiveauTypeSujetPanier) {
		this.listNiveauTypeSujetPanier = listNiveauTypeSujetPanier;
	}

	public List<BeanNiveauTypeSujet> getListNiveauTypeSujetAselectionner() {
		return listNiveauTypeSujetAselectionner;
	}

	public void setListNiveauTypeSujetAselectionner(List<BeanNiveauTypeSujet> listNiveauTypeSujetAselectionner) {
		this.listNiveauTypeSujetAselectionner = listNiveauTypeSujetAselectionner;
	}

	public String getUrlFlashCode() {
		return urlFlashCode;
	}

	public void setUrlFlashCode(String urlFlashCode) {
		this.urlFlashCode = urlFlashCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJsonSujetDifficulte() {
		return jsonSujetDifficulte;
	}

	public void setJsonSujetDifficulte(String jsonSujetDifficulte) {
		this.jsonSujetDifficulte = jsonSujetDifficulte;
	}

}
