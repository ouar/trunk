package com.mkyong.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionHibernate {

	private ApplicationContext appContext;

	private ConnectionHibernate() {
		appContext = new ClassPathXmlApplicationContext("config/BeanLocations.xml");
	}

	/** Instance unique non pr�initialis�e */
	private static ConnectionHibernate INSTANCE = null;

	/** Point d'acc�s pour l'instance unique du singleton */
	public static ConnectionHibernate getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionHibernate();
		}
		return INSTANCE;
	}

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

}
