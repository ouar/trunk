package fr.soat.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import fr.soat.shared.Person;

/**
 * View - implements la vue du presenter 
 * @author Kader
 *
 */
public class DetailPersonPresenter implements Presenter {
	
	public interface IDetailPersonView {
		/**
		 * Presenter pour la vue 
		 * @param presenter
		 */
		void setPrenseter(DetailPersonPresenter presenter);
		/**
		 * Editer la personne dans la vue 
		 * @param person
		 */
		void setPerson(Person person);
		/**
		 * Vue widget 
		 * @return
		 */
		Widget asWidget();
	}
	
	private final IDetailPersonView detailPersonView;
	private final EventBus eventBus;
	private Person person;
	/**
	 * Constructeur presenter 
	 * @param detailPersonView
	 * @param eventBus
	 * @param person 
	 */
	public DetailPersonPresenter(EventBus eventBus, IDetailPersonView detailPersonView, Person person) {
		this.detailPersonView = detailPersonView;
		this.detailPersonView.setPrenseter(this);
		this.person = person;
		this.eventBus = eventBus;
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		this.detailPersonView.setPerson(this.person);
		container.add(detailPersonView.asWidget());
	}

}
