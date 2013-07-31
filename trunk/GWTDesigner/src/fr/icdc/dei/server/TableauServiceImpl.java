package fr.icdc.dei.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.icdc.dei.client.services.TableauService;
import fr.icdc.dei.shared.FieldVerifier;
import fr.icdc.dei.shared.dto.PersonneDTO;

@SuppressWarnings("serial")
public class TableauServiceImpl extends RemoteServiceServlet implements TableauService {
	@Override
	public PersonneDTO[] recupererPersonnes(String nomTableau) throws IllegalArgumentException {
		PersonneDTO[] listePersonnes = null;

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		nomTableau = escapeHtml(nomTableau);

		// Verify that the input is valid.
		if (!FieldVerifier.isTableauValide(nomTableau)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to the client.
			throw new IllegalArgumentException("Merci d'entrer le mot \"personnes\"");
		} else {
			listePersonnes = new PersonneDTO[12];
			listePersonnes[0] = new PersonneDTO("Stock", "Tarek", 26);
			listePersonnes[1] = new PersonneDTO("Stockuza", "Evode", 28);
			listePersonnes[2] = new PersonneDTO("Stolueri", "Ali", 33);
			listePersonnes[3] = new PersonneDTO("Stecrou", "Pierre", 30);
			listePersonnes[4] = new PersonneDTO("Stalingez", "Yannick", 33);
			listePersonnes[5] = new PersonneDTO("Toutouyoutou", "Salah", 34);
			listePersonnes[6] = new PersonneDTO("Stiloper", "Akli", 37);
			listePersonnes[7] = new PersonneDTO("Stazerli", "Philippe", 49);
			listePersonnes[8] = new PersonneDTO("Stabin", "Nacer", 42);
			listePersonnes[9] = new PersonneDTO("Stupoin", "Romain", 25);
			listePersonnes[10] = new PersonneDTO("Stupoinet", "Benjamin", 35);
			listePersonnes[11] = new PersonneDTO("Starine", "Elhadji", 29);
		}
		return listePersonnes;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
