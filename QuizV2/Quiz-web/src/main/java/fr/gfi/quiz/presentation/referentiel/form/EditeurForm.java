package fr.gfi.quiz.presentation.referentiel.form;

import java.util.List;

public class EditeurForm {

	private Long id;

	private String adresse;

	private String nom;

	private int version;

	private String nomVille;

	private Long codePostal;

	private List<OuvrageForm> ouvrages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public Long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}

	public List<OuvrageForm> getOuvrages() {
		return ouvrages;
	}

	public void setOuvrages(List<OuvrageForm> ouvrages) {
		this.ouvrages = ouvrages;
	}
}
