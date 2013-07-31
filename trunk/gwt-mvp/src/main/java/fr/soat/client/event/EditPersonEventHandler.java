package fr.soat.client.event;

import com.google.gwt.event.shared.EventHandler;


/**
 * Handler - Gestion de l'évenement de l'édition d'une personne
 * @author Kader
 *
 */
public interface EditPersonEventHandler extends EventHandler{
	void onEditPersonn(EditPersonEvent event);
}
