package com.netapsys.springmvc.web.bean;

public class Context {

	private String urlWebService;

	private Context() {

	}
	

	private Context(String urlWebService) {
		INSTANCE = new Context();
		INSTANCE.urlWebService = urlWebService;
	}


	/** Instance unique non pr�initialis�e */
	private static Context INSTANCE = null;

	/** Point d'acc�s pour l'instance unique du singleton */
	public static Context getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Context();
		}
		return INSTANCE;
	}

	public String getUrlWebService() {
		return urlWebService;
	}

	public void setUrlWebService(String urlWebService) {
		this.urlWebService = urlWebService;
	}

}
