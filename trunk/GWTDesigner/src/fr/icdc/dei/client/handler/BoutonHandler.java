package fr.icdc.dei.client.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import fr.icdc.dei.client.AccueilPresenter;

public class BoutonHandler implements ClickHandler {
	private AccueilPresenter presenter;

	public BoutonHandler(AccueilPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onClick(ClickEvent event) {
		presenter.interrogerServeur();
	}
}
