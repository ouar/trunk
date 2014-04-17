package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.List;


public class Question implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1381212015605371688L;
	private int id;
	private IdLibelle theme;
	private IdLibelle sujet;
	private IdLibelle difficulte;
	private boolean uniqueReponseCorrecte;
	private int dureeReflexionEnSec;
	private boolean repondue;
	private boolean correcte;
	private String libelle;
	private boolean image;
	private List<Reponse> lReponses;

	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, IdLibelle theme, IdLibelle typeSujet,
			IdLibelle difficulte, boolean uniqueReponseCorrecte,
			int dureeReflexionEnSec, String libelle, boolean image,
			List<Reponse> lReponses) {
		super();
		this.id = id;
		this.theme = theme;
		this.sujet = typeSujet;
		this.difficulte = difficulte;
		this.uniqueReponseCorrecte = uniqueReponseCorrecte;
		this.dureeReflexionEnSec = dureeReflexionEnSec;
		this.libelle = libelle;
		this.image = image;
		this.lReponses = lReponses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IdLibelle getTheme() {
		return theme;
	}

	public void setTheme(IdLibelle theme) {
		this.theme = theme;
	}

	public IdLibelle getSujet() {
		return sujet;
	}

	public void setSujet(IdLibelle typeSujet) {
		this.sujet = typeSujet;
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

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
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
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + (repondue ? 1231 : 1237);
		result = prime * result
				+ ((sujet == null) ? 0 : sujet.hashCode());
		result = prime * result + (uniqueReponseCorrecte ? 1231 : 1237);
		result = prime * result + (image ? 1231 : 1237);
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
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (repondue != other.repondue)
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		if (uniqueReponseCorrecte != other.uniqueReponseCorrecte)
			return false;
		if (image != other.image)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nQuestion [id=" + id + ", theme=" + theme + ", typeSujet="
				+ sujet + ", difficulte=" + difficulte
				+ ", uniqueReponseCorrecte=" + uniqueReponseCorrecte
				+ ", dureeReflexionEnSec=" + dureeReflexionEnSec
				+ ", repondue=" + repondue + ", correcte=" + correcte
				+ ", libelle=" + libelle + ", image=" + image
				+ ",\n lReponses=" + lReponses + "]";
	}




}
