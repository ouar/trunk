package fr.gfi.quiz.presentation.referentiel.dto;

import fr.gfi.quiz.entite.Editeur;
import fr.gfi.quiz.entite.Ville;

/**
 * editeur DTO
 * 
 * @author CCMT Team
 *
 */
public class EditeurDto {

	private int id;

	private String nom;

	private String adresse;

	private String codePostal;

	private String ville;


	public EditeurDto() {

	}

	public EditeurDto(Editeur ed) {
		this.setId(ed.getId().intValue());
		this.setNom(ed.getNom());
		this.setAdresse(ed.getAdresse());
		this.setCodePostal(ed.getVille().getCodePostal()+"");
		this.setVille(ed.getVille().getNom());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	public Editeur fromDTO() {
		Editeur from = new Editeur();
		from.setId(new Long(this.getId()));
		from.setAdresse(this.getAdresse());
		Ville ville = new Ville();
		ville.setCodePostal(new Long(this.getCodePostal()));
		ville.setNom(this.getVille());
		from.setNom(this.getNom());
		from.setVille(ville);

		return from;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EditeurDTO [id=" + id + ", nom=" + nom + ", adresse=" + adresse
				+ ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
