package fr.soat.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

import fr.soat.client.event.EditPersonEvent;
import fr.soat.client.event.EditPersonEventHandler;
import fr.soat.client.presenter.DetailPersonPresenter;
import fr.soat.client.presenter.FormPersonPresenter;
import fr.soat.client.presenter.Presenter;
import fr.soat.shared.Person;

/**
 * Permet de gérer la logique qui n'est pas liée à aucun presenter, plutôt
 * réside dans l'application. Ce composant contient la gestion de l'historique et
 * les règles de navigation et de transition entre les vues. La gestion de la transition
 * est directement liée à l'historique
 * 
 * @author Kader
 * 
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

	/**
	 * Event Bus permet de forwarder/passer des évenements, ainsi qu'enregistrer
	 * ces évenements pour qu'ils soient notifiés
	 */
	private final EventBus eventBus;
	private HasWidgets container;
	private ClientFactory clientFactory;
	private Person person;
	public AppController(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.eventBus = clientFactory.getEventBus();
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		eventBus.addHandler(EditPersonEvent.TYPE, new EditPersonEventHandler() {
				@Override
				public void onEditPersonn(EditPersonEvent event) {
					person = event.getPerson();
					doEditPerson(person);
				}
		});

	}
	
	private void doEditPerson(Person person) {
		History.newItem("edit", false);
		Presenter presenter = new DetailPersonPresenter(eventBus, clientFactory.getDetailPersonView(), person);
		presenter.go(container);
	}
		
	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("form");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("form")) {
				presenter = new FormPersonPresenter(eventBus, clientFactory.getFormPersonView());
			} else if (token.equals("edit")) {
				presenter = new DetailPersonPresenter(eventBus, clientFactory.getDetailPersonView(), person);
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}
}
