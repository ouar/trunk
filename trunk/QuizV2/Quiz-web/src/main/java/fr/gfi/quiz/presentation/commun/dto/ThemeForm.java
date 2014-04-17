package fr.gfi.quiz.presentation.commun.dto;

import java.util.ArrayList;
import java.util.List;

import fr.gfi.quiz.entite.hibernate.Sujet;
import fr.gfi.quiz.entite.hibernate.Theme;
import fr.gfi.quiz.json.entite.IdLibelle;

public class ThemeForm {

	private IdLibelle theme;
	private List<SujetForm> lSujets;

	public ThemeForm() {
		theme = new IdLibelle();
		lSujets = new ArrayList<SujetForm>();
	}

	public ThemeForm(IdLibelle theme) {
		this();
		this.theme = theme;
	}

	public ThemeForm(int id, String libelle) {
		this();
		theme.setId(id);
		theme.setLibelle(libelle);
	}

	public ThemeForm(Theme themeFromBDD){
		this();
		theme.setId(themeFromBDD.getId());
		theme.setLibelle(themeFromBDD.getLibelle());
		if (themeFromBDD.getSujets().size() > 0) {
			for (Sujet sujet : themeFromBDD.getSujets()) {
				SujetForm sujetTemp = new SujetForm(sujet);
				if(!lSujets.contains(sujetTemp)){
					lSujets.add(sujetTemp);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "LangageForm [langage=" + theme + ", lSujets=" + lSujets + "]";
	}

	public IdLibelle getTheme() {
		return theme;
	}

	public void setTheme(IdLibelle langage) {
		this.theme = langage;
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
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
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
		ThemeForm other = (ThemeForm) obj;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		return true;
	}


}
