package fr.icdc.dei.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.icdc.dei.shared.dto.PersonneDTO;

public interface TableauServiceAsync {
	void recupererPersonnes(String nomTableau, AsyncCallback<PersonneDTO[]> callback)
			throws IllegalArgumentException;
}
