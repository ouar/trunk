package fr.icdc.dei.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.icdc.dei.shared.dto.PersonneDTO;

@RemoteServiceRelativePath("tableau")
public interface TableauService extends RemoteService {
	PersonneDTO[] recupererPersonnes(String nomTableau) throws IllegalArgumentException;
}
