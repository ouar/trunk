package fr.soat.client.util;

public class PersonData {
	
	/**
	 * Liste civilités 
	 */
	private String[] civilites;
	
	/**
	 * Constructeur pour civilités 
	 */
	public PersonData() {
		civilites = new String[]{"Mlle", "Mme", "Mr"};
	}

	/**
	 * 
	 * @return String[]
	 */
	public String[] getCivilites() {
		return civilites;
	}
	
}
