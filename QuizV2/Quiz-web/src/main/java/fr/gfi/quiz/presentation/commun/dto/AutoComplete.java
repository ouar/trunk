package fr.gfi.quiz.presentation.commun.dto;

import javax.validation.constraints.Pattern;

public class AutoComplete {

	@Pattern(regexp="^[a-zA-Z0-9ÀÂÇÈÉÊËÎÔÙÛàâçèéêëîôùû@.\\-_ ]*$")
	private String startsWith;
	private int maxSize;
	
	public String getStartsWith() {
		return startsWith;
	}
	public void setStartsWith(String startsWith) {
		this.startsWith = startsWith;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	
}
