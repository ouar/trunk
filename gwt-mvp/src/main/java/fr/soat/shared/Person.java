package fr.soat.shared;

/**
 * Model - Person 
 * @author Kader
 *
 */
public class Person {
	
	private String civilite;
	private String nom;
	private String prenom;
	private String email;
	
	/**
	 * Constructeur Person sans args 
	 */
	public Person() {
		super();
	}

	/**
	 * Constructeur Person avec args
	 * @param nom
	 * @param presnom
	 * @param email
	 */
	public Person(String civilite, String nom, String presnom, String email) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = presnom;
		this.email = email;
	}
	
	// Getteurs & Setteurs 
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String presnom) {
		this.prenom = presnom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
