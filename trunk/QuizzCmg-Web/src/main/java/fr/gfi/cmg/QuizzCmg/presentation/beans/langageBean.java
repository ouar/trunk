package fr.gfi.cmg.QuizzCmg.presentation.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Langage;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.TypeSujet;

public class langageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810393276053272721L;
	private int id;
	private String libelle;
	private List<SujetBean> lSujet;
	private JSONArray lSujetJson;

	public langageBean() {
		super();
		lSujet = new ArrayList<SujetBean>();
	}

	public langageBean(int id, String libelle, List<SujetBean> lSujet) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.lSujet = lSujet;
	}

	public langageBean(Langage langageFromBDD) {
		this();
		this.id = langageFromBDD.getId();
		this.libelle = langageFromBDD.getLibelle();
		if (langageFromBDD.getTypeSujets().size() > 0) {
			for (TypeSujet typeSujet : langageFromBDD.getTypeSujets()) {
				lSujet.add(new SujetBean(typeSujet));
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<SujetBean> getlSujet() {
		return lSujet;
	}

	public void setlSujet(List<SujetBean> lSujet) {
		this.lSujet = lSujet;
	}

	@Override
	public String toString() {
		return "langageBean [id=" + id + ", libelle=" + libelle + ", lSujet=" + lSujet + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((lSujet == null) ? 0 : lSujet.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			if (obj instanceof Langage) {
				return ((Langage) obj).getId() == this.getId();
			} else
				return false;
		}

		langageBean other = (langageBean) obj;
		if (id != other.id)
			return false;
		if (lSujet == null) {
			if (other.lSujet != null)
				return false;
		} else if (!lSujet.equals(other.lSujet))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}

	public JSONArray getlSujetJson() {
		return lSujetJson;
	}

	public void setlSujetJson(JSONArray lSujetJson) {
		this.lSujetJson = lSujetJson;
	}
	
	
}
