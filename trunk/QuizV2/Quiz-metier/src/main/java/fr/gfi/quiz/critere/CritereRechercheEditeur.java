package fr.gfi.quiz.critere;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class CritereRechercheEditeur implements java.io.Serializable {
	
	private String id;
	
	@Pattern(regexp="^[A-Za-z0-9]*$",message="le nom ne doit contenir que des caractères alphanumerics !")
	private String nom;
	
	private String sort;
	
	private int startRow;
	
	private int fetchSize;
	
	private int tRowCount;

	public CritereRechercheEditeur() {

	}

	public CritereRechercheEditeur(String id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public int getTRowCount() {
		return tRowCount;
	}

	public void setTRowCount(int rowCount) {
		tRowCount = rowCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this); 
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
