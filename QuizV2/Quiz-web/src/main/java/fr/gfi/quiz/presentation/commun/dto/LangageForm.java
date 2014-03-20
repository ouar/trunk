package fr.gfi.quiz.presentation.commun.dto;

import java.util.ArrayList;
import java.util.List;

import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.TypeSujet;
import fr.gfi.quiz.json.entite.IdLibelle;

public class LangageForm {

	private IdLibelle langage;
	private List<SujetForm> lSujets;

	public LangageForm() {
		langage = new IdLibelle();
		lSujets = new ArrayList<SujetForm>();
	}

	public LangageForm(IdLibelle langage) {
		this();
		this.langage = langage;
	}

	public LangageForm(int id, String libelle) {
		this();
		langage.setId(id);
		langage.setLibelle(libelle);
	}

	public LangageForm(Langage langageFromBDD){
		this();
		langage.setId(langageFromBDD.getId());
		langage.setLibelle(langageFromBDD.getLibelle());
		if (langageFromBDD.getTypeSujets().size() > 0) {
			for (TypeSujet typeSujet : langageFromBDD.getTypeSujets()) {
				SujetForm sujetTemp = new SujetForm(typeSujet);
				if(!lSujets.contains(sujetTemp)){
					lSujets.add(sujetTemp);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "LangageForm [langage=" + langage + ", lSujets=" + lSujets + "]";
	}

	public IdLibelle getLangage() {
		return langage;
	}

	public void setLangage(IdLibelle langage) {
		this.langage = langage;
	}

	public List<SujetForm> getlSujets() {
		return lSujets;
	}

	public void setlSujets(List<SujetForm> lSujets) {
		this.lSujets = lSujets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((langage == null) ? 0 : langage.hashCode());
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
		LangageForm other = (LangageForm) obj;
		if (langage == null) {
			if (other.langage != null)
				return false;
		} else if (!langage.equals(other.langage))
			return false;
		return true;
	}


}
