package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StatQuiz implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1303935601711071379L;
	private Personne examinateur;
	private Personne candidat;
	private Date date;

	private int dureePrevue;
	private int dureeUtilisee;
	private int noteSur20;
	private List<StatLangage> lLangages;

	public StatQuiz() {
	}

	public StatQuiz(Personne examinateur, Personne candidat, Date date,
			int dureePrevue, int dureeUtilisee, int noteSur20,
			List<StatLangage> lLangages) {
		super();
		this.examinateur = examinateur;
		this.candidat = candidat;
		this.date = date;
		this.dureePrevue = dureePrevue;
		this.dureeUtilisee = dureeUtilisee;
		this.noteSur20 = noteSur20;
		this.lLangages = lLangages;
	}

	public Personne getExaminateur() {
		return examinateur;
	}

	public void setExaminateur(Personne examinateur) {
		this.examinateur = examinateur;
	}

	public Personne getCandidat() {
		return candidat;
	}

	public void setCandidat(Personne candidat) {
		this.candidat = candidat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDureePrevue() {
		return dureePrevue;
	}

	public void setDureePrevue(int dureePrevue) {
		this.dureePrevue = dureePrevue;
	}

	public int getDureeUtilisee() {
		return dureeUtilisee;
	}

	public void setDureeUtilisee(int dureeUtilisee) {
		this.dureeUtilisee = dureeUtilisee;
	}

	public int getNoteSur20() {
		return noteSur20;
	}

	public void setNoteSur20(int noteSur20) {
		this.noteSur20 = noteSur20;
	}

	public List<StatLangage> getlLangages() {
		return lLangages;
	}

	public void setlLangages(List<StatLangage> lLangages) {
		this.lLangages = lLangages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((candidat == null) ? 0 : candidat.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + dureePrevue;
		result = prime * result + dureeUtilisee;
		result = prime * result
				+ ((examinateur == null) ? 0 : examinateur.hashCode());
		result = prime * result
				+ ((lLangages == null) ? 0 : lLangages.hashCode());
		result = prime * result + noteSur20;
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
		StatQuiz other = (StatQuiz) obj;
		if (candidat == null) {
			if (other.candidat != null)
				return false;
		} else if (!candidat.equals(other.candidat))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dureePrevue != other.dureePrevue)
			return false;
		if (dureeUtilisee != other.dureeUtilisee)
			return false;
		if (examinateur == null) {
			if (other.examinateur != null)
				return false;
		} else if (!examinateur.equals(other.examinateur))
			return false;
		if (lLangages == null) {
			if (other.lLangages != null)
				return false;
		} else if (!lLangages.equals(other.lLangages))
			return false;
		if (noteSur20 != other.noteSur20)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatQuiz [examinateur=" + examinateur + ", candidat="
				+ candidat + ", date=" + date + ", dureePrevue=" + dureePrevue
				+ ", dureeUtilisee=" + dureeUtilisee + ", noteSur20="
				+ noteSur20 + ", lLangages=" + lLangages + "]";
	}
}
