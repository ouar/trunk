package fr.gfi.quiz.entite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the ouvrage database table.
 * 
 */
@Entity
@Table(name="ouvrage")
public class Ouvrage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dateParution;
	
	private BigDecimal prix;
	
	private String isbn;

	private Long nbPages;

	private Long stock;

	private String titre;

	@Version
	private int version;

	//bi-directional many-to-one association to Editeur
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="editeur", nullable=false)
	private Editeur editeur;

	public Ouvrage() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateParution() {
		return this.dateParution;
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
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getNbPages() {
		return this.nbPages;
	}

	public void setNbPages(Long nbPages) {
		this.nbPages = nbPages;
	}

	public Long getStock() {
		return this.stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Editeur getEditeur() {
		return this.editeur;
	}

	public void setEditeur(Editeur editeurBean) {
		this.editeur = editeurBean;
	}

}