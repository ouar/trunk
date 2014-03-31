package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.List;


public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1381212015605371688L;
	private int id;
	private IdLibelle langage;
	private IdLibelle typeSujet;
	private IdLibelle difficulte;
	private boolean uniqueReponseCorrecte;
	private int dureeReflexionEnSec;
	private boolean repondue;
	private boolean correcte;
	private String libelle;
	private String urlInmage;
	private List<Reponse> lReponses;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, IdLibelle langage, IdLibelle typeSujet,
			IdLibelle difficulte, boolean uniqueReponseCorrecte,
			int dureeReflexionEnSec, String libelle, String urlInmage,
			List<Reponse> lReponses) {
		super();
		this.id = id;
		this.langage = langage;
		this.typeSujet = typeSujet;
		this.difficulte = difficulte;
		this.uniqueReponseCorrecte = uniqueReponseCorrecte;
		this.dureeReflexionEnSec = dureeReflexionEnSec;
		this.libelle = libelle;
		this.urlInmage = urlInmage;
		this.lReponses = lReponses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
	}

	public IdLibelle getTypeSujet() {
		return typeSujet;
	}

	public void setTypeSujet(IdLibelle typeSujet) {
		this.typeSujet = typeSujet;
	}

	public IdLibelle getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(IdLibelle difficulte) {
		this.difficulte = difficulte;
	}

	public boolean isUniqueReponseCorrecte() {
		return uniqueReponseCorrecte;
	}

	public void setUniqueReponseCorrecte(boolean uniqueReponseCorrecte) {
		this.uniqueReponseCorrecte = uniqueReponseCorrecte;
	}

	public int getDureeReflexionEnSec() {
		return dureeReflexionEnSec;
	}

	public void setDureeReflexionEnSec(int dureeReflexionEnSec) {
		this.dureeReflexionEnSec = dureeReflexionEnSec;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getUrlInmage() {
		return urlInmage;
	}

	public void setUrlInmage(String urlInmage) {
		this.urlInmage = urlInmage;
	}

	public List<Reponse> getlReponses() {
		return lReponses;
	}

	public void setlReponses(List<Reponse> lReponses) {
		this.lReponses = lReponses;
	}

	public boolean isRepondue() {
		return repondue;
	}

	public void setRepondue(boolean repondue) {
		this.repondue = repondue;
	}

	public boolean isCorrecte() {
		return correcte;
	}

	public void setCorrecte(boolean correcte) {
		this.correcte = correcte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (correcte ? 1231 : 1237);
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + dureeReflexionEnSec;
		result = prime * result + id;
		result = prime * result
				+ ((lReponses == null) ? 0 : lReponses.hashCode());
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + (repondue ? 1231 : 1237);
		result = prime * result
				+ ((typeSujet == null) ? 0 : typeSujet.hashCode());
		result = prime * result + (uniqueReponseCorrecte ? 1231 : 1237);
		result = prime * result
				+ ((urlInmage == null) ? 0 : urlInmage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (correcte != other.correcte)
			return false;
		if (difficulte == null) {
			if (other.difficulte != null)
				return false;
		} else if (!difficulte.equals(other.difficulte))
			return false;
		if (dureeReflexionEnSec != other.dureeReflexionEnSec)
			return false;
		if (id != other.id)
			return false;
		if (lReponses == null) {
			if (other.lReponses != null)
				return false;
		} else if (!lReponses.equals(other.lReponses))
			return false;
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (repondue != other.repondue)
			return false;
		if (typeSujet == null) {
			if (other.typeSujet != null)
				return false;
		} else if (!typeSujet.equals(other.typeSujet))
			return false;
		if (uniqueReponseCorrecte != other.uniqueReponseCorrecte)
			return false;
		if (urlInmage == null) {
			if (other.urlInmage != null)
				return false;
		} else if (!urlInmage.equals(other.urlInmage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nQuestion [id=" + id + ", langage=" + langage + ", typeSujet="
				+ typeSujet + ", difficulte=" + difficulte
				+ ", uniqueReponseCorrecte=" + uniqueReponseCorrecte
				+ ", dureeReflexionEnSec=" + dureeReflexionEnSec
				+ ", repondue=" + repondue + ", correcte=" + correcte
				+ ", libelle=" + libelle + ", urlInmage=" + urlInmage
				+ ",\n lReponses=" + lReponses + "]";
	}

	
	
	
}
