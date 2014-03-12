package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8527927448616221917L;

	private int id;
	private Personne candidat;
	private Personne examinateur;
	private List<TypeQuestion> lTypesQuestions;
	private List<Question> lQuestions;

	public Quiz() {
	}
	
	
	
	public Quiz(int id, Personne candidat, Personne examinateur,
			List<TypeQuestion> typesQuestions,
			List<Question> questions) {
		super();
		this.id = id;
		this.candidat = candidat;
		this.examinateur = examinateur;
		lTypesQuestions = typesQuestions;
		lQuestions = questions;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Personne getCandidat() {
		return candidat;
	}

	public void setCandidat(Personne candidat) {
		this.candidat = candidat;
	}

	public Personne getExaminateur() {
		return examinateur;
	}

	public void setExaminateur(Personne examinateur) {
		this.examinateur = examinateur;
	}

	public List<TypeQuestion> getLTypesQuestions() {
		return lTypesQuestions;
	}

	public void setLTypesQuestions(List<TypeQuestion> typesQuestions) {
		lTypesQuestions = typesQuestions;
	}

	public List<Question> getLQuestions() {
		return lQuestions;
	}

	public void setLQuestions(List<Question> questions) {
		lQuestions = questions;
	}



	@Override
	public String toString() {
		return "Quiz [id=" + id + ", candidat=" + candidat + ", examinateur="
				+ examinateur + ", lTypesQuestions=" + lTypesQuestions
				+ ", lQuestions=" + lQuestions + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((candidat == null) ? 0 : candidat.hashCode());
		result = prime * result
				+ ((examinateur == null) ? 0 : examinateur.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((lQuestions == null) ? 0 : lQuestions.hashCode());
		result = prime * result
				+ ((lTypesQuestions == null) ? 0 : lTypesQuestions.hashCode());
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
		Quiz other = (Quiz) obj;
		if (candidat == null) {
			if (other.candidat != null)
				return false;
		} else if (!candidat.equals(other.candidat))
			return false;
		if (examinateur == null) {
			if (other.examinateur != null)
				return false;
		} else if (!examinateur.equals(other.examinateur))
			return false;
		if (id != other.id)
			return false;
		if (lQuestions == null) {
			if (other.lQuestions != null)
				return false;
		} else if (!lQuestions.equals(other.lQuestions))
			return false;
		if (lTypesQuestions == null) {
			if (other.lTypesQuestions != null)
				return false;
		} else if (!lTypesQuestions.equals(other.lTypesQuestions))
			return false;
		return true;
	}

}
