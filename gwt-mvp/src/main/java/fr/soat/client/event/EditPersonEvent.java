package fr.soat.client.event;

import com.google.gwt.event.shared.GwtEvent;

import fr.soat.shared.Person;

/**
 * Evenement d'édition d'une personne
 * @author Kader
 *
 */
public class EditPersonEvent extends GwtEvent<EditPersonEventHandler>{
	public static Type<EditPersonEventHandler> TYPE = new Type<EditPersonEventHandler>();
	
	private Person person;
	
	public EditPersonEvent(Person person) {
		super();
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditPersonEventHandler> getAssociatedType() {
		return EditPersonEvent.TYPE;
	}

	@Override
	protected void dispatch(EditPersonEventHandler handler) {
		handler.onEditPersonn(this);
	}
	
	
}
