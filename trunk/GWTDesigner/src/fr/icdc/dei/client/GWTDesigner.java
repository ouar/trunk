package fr.icdc.dei.client;

import com.google.gwt.core.client.EntryPoint;

import fr.icdc.dei.client.presenter.AccueilPresenterImpl;
import fr.icdc.dei.client.vue.AccueilVueImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTDesigner implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		AccueilVue vue = new AccueilVueImpl();
		AccueilPresenter presenter = new AccueilPresenterImpl(vue);
		vue.enregistrerPresenter(presenter);
		vue.lancerApplication();
	}
}
