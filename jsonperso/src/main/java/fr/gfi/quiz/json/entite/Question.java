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
	private boolean plusieursReponsesCorrectes;
	private int dureeReflexionEnSec;
	private String libelle;
	private String urlInmage;
	private List<Reponse> lReponses;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, IdLibelle langage, IdLibelle typeSujet,
			IdLibelle difficulte, boolean plusieursReponsesCorrectes,
			int dureeReflexionEnSec, String libelle, String urlInmage,
			List<Reponse> lReponses) {
		super();
		this.id = id;
		this.langage = langage;
		this.typeSujet = typeSujet;
		this.difficulte = difficulte;
		this.plusieursReponsesCorrectes = plusieursReponsesCorrectes;
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

	public boolean isPlusieursReponsesCorrectes() {
		return plusieursReponsesCorrectes;
	}

	public void setPlusieursReponsesCorrectes(boolean plusieursReponsesCorrectes) {
		this.plusieursReponsesCorrectes = plusieursReponsesCorrectes;
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

	@Override
	public String toString() {
		return "Question [id=" + id + ", langage=" + langage + ", typeSujet="
				+ typeSujet + ", difficulte=" + difficulte
				+ ", plusieursReponsesCorrectes=" + plusieursReponsesCorrectes
				+ ", dureeReflexionEnSec=" + dureeReflexionEnSec + ", libelle="
				+ libelle + ", urlInmage=" + urlInmage + ", lReponses="
				+ lReponses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((difficulte == null) ? 0 : difficulte.hashCode());
		result = prime * result + dureeReflexionEnSec;
		result = prime * result + id;
		result = prime * result
				+ ((lReponses == null) ? 0 : lReponses.hashCode());
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + (plusieursReponsesCorrectes ? 1231 : 1237);
		result = prime * result
				+ ((typeSujet == null) ? 0 : typeSujet.hashCode());
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
		if (plusieursReponsesCorrectes != other.plusieursReponsesCorrectes)
			return false;
		if (typeSujet == null) {
			if (other.typeSujet != null)
				return false;
		} else if (!typeSujet.equals(other.typeSujet))
			return false;
		if (urlInmage == null) {
			if (other.urlInmage != null)
				return false;
		} else if (!urlInmage.equals(other.urlInmage))
			return false;
		return true;
	}
	
	
	
}
