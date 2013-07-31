package fr.soat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtMvp implements EntryPoint {
  
   /**
   * Point d'entrée de l'application 
   */
  public void onModuleLoad() {
	   ClientFactory clientFactory = GWT.create(ClientFactory.class);
		AppController appViewer = new AppController(clientFactory);
		appViewer.go(RootPanel.get());
  }
}
