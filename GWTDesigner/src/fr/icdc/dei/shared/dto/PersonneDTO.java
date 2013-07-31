package fr.icdc.dei.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonneDTO implements IsSerializable {
	private String nom;
	private String prenom;
	private int age;

	public PersonneDTO() {

	}

	public PersonneDTO(String nom, String prenom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
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

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
