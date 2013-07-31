package fr.soat.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract interface Presenter {
	
	/**
	 * go to new place 
	 * @param container
	 */
  public abstract void go(final HasWidgets container);
}
