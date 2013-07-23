package com.dngconsulting.hello.client;

import java.io.Serializable;
import java.util.Date;

public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286127119984204060L;

	private Integer id;

	private Double last;

	private String name;

	private String symbol;

	private Date updatedDate;

	public Double getLast() {
		return last;
	}

	public void setLast(Double last) {
		this.last = last;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Stock(Integer id, Double last, String name, String symbol, Date updatedDate) {
		this();
		this.id = id;
		this.last = last;
		this.name = name;
		this.symbol = symbol;
		this.updatedDate = updatedDate;
	}

	public Stock() {
		// TODO Auto-generated constructor stub
	}

}
