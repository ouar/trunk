package fr.soat.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import fr.soat.client.event.EditPersonEvent;
import fr.soat.shared.Person;

/**
 * Presenter -  Formulaire de saisie des coordonnées d'une personne 
 * @author Kader
 *
 */
public class FormPersonPresenter implements Presenter{
	
	/**
	 * View - interface passive du formulaire - Partie de la vue
	 * @author Kader
	 *
	 */
	public interface IFormPersonView {
		void setPrenseter(FormPersonPresenter presenter);
		Widget asWidget();
	}
	
	private final IFormPersonView formPersonView;
	private final EventBus eventBus;
	
	/**
	 * Constructeur presenter 
	 * @param formPersonView
	 * @param eventBus
	 */
	public FormPersonPresenter(EventBus eventBus, IFormPersonView formPersonView) {
		this.formPersonView = formPersonView;
		this.formPersonView.setPrenseter(this);
		this.eventBus = eventBus;
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(formPersonView.asWidget());
	}

	/**
	 * Enregistrer la personne et afficher les coordonnées dans la page de détail
	 * @param person
	 */
	public void doEnregistrerPerson(Person person) {
		eventBus.fireEvent(new EditPersonEvent(person));
	}
	
}
