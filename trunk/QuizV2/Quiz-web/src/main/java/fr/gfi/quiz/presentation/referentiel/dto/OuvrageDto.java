package fr.gfi.quiz.presentation.referentiel.dto;

import java.util.Date;

import fr.gfi.quiz.entite.Ouvrage;

/**
 * Ouvrage DTO
 * 
 * @author CCMT Team
 *
 */
public class OuvrageDto {
	
	private int id;
	
	private EditeurDto editeurDto;
	
	private String titre;
	
	private Date dateParution;
	
	private int nbpages;
	
	private Integer stock;

	public OuvrageDto(){
		
	}
	
	public OuvrageDto(Ouvrage ouvrage){
		setId(ouvrage.getId().intValue());
//		EditeurDTO dto = new EditeurDTO(ouvrage.getEditeur());
//		setEditeur(dto);
		setDateParution(ouvrage.getDateParution());
		setNbpages(ouvrage.getNbPages().intValue());
		setStock(ouvrage.getStock().intValue());
		setTitre(ouvrage.getTitre());
	}	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the editeurDto
	 */
	public EditeurDto getEditeur() {
		return editeurDto;
	}

	/**
	 * @param editeurDto the editeurDto to set
	 */
	public void setEditeur(EditeurDto editeurDto) {
		this.editeurDto = editeurDto;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the dateParution
	 */
	public Date getDateParution() {
		return dateParution;
	}

	/**
	 * @param dateParution the dateParution to set
	 */
	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	/**
	 * @return the nbpages
	 */
	public int getNbpages() {
		return nbpages;
	}

	/**
	 * @param nbpages the nbpages to set
	 */
	public void setNbpages(int nbpages) {
		this.nbpages = nbpages;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Ouvrage fromDTO(){ 
		Ouvrage from = new Ouvrage();
		from.setId(new Long(this.getId()));
		from.setDateParution(this.getDateParution());
		//from.setEditeur(this.getEditeur().fromDTO());
		from.setNbPages(new Long(this.getNbpages()));
		from.setStock(new Long(this.getStock()));
		from.setTitre(this.getTitre());
		
		return from;
	}	
	
}
