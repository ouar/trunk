package fr.gfi.quiz.presentation.referentiel.form;

import java.math.BigDecimal;
import java.util.Date;

public class OuvrageForm {

	private Long id;

	private Date dateParution;

	private BigDecimal prix;

	private String isbn;

	private Long nbPages;

	private Long stock;

	private String titre;

	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getNbPages() {
		return nbPages;
	}

	public void setNbPages(Long nbPages) {
		this.nbPages = nbPages;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


}
