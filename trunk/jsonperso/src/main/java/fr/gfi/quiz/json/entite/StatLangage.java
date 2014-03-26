package fr.gfi.quiz.json.entite;

import java.io.Serializable;
import java.util.List;

public class StatLangage implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1949212748982190820L;
	private IdLibelle langage;
	private List<StatSujet> lSujets;

	private int noteSur20;

	public StatLangage() {
	}

	public StatLangage(IdLibelle langage, List<StatSujet> lSujets, int noteSur20) {
		super();
		this.langage = langage;
		this.lSujets = lSujets;
		this.noteSur20 = noteSur20;
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
	}

	public List<StatSujet> getlSujets() {
		return lSujets;
	}

	public void setlSujets(List<StatSujet> lSujets) {
		this.lSujets = lSujets;
	}

	public int getNoteSur20() {
		return noteSur20;
	}

	public void setNoteSur20(int noteSur20) {
		this.noteSur20 = noteSur20;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lSujets == null) ? 0 : lSujets.hashCode());
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
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
		StatLangage other = (StatLangage) obj;
		if (lSujets == null) {
			if (other.lSujets != null)
				return false;
		} else if (!lSujets.equals(other.lSujets))
			return false;
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		if (noteSur20 != other.noteSur20)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StatLangage [langage=" + langage + ", lSujets=" + lSujets
				+ ", noteSur20=" + noteSur20 + "]";
	}
}
