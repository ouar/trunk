package fr.gfi.quiz.presentation.referentiel.dto;

/**
 * Ville DTO
 * 
 * @author CCMT Team
 *
 */
public class VilleDto {

	private Long id;

	private Long codePostal;

	private String nom;

	public Long getId() {
		return id;
	}
	
	public VilleDto() {
	}


	public VilleDto(Long id, Long codePostal, String nom) {
		super();
		this.id = id;
		this.codePostal = codePostal;
		this.nom = nom;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
