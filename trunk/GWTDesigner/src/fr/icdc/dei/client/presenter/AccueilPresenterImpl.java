package fr.icdc.dei.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.icdc.dei.client.AccueilPresenter;
import fr.icdc.dei.client.AccueilVue;
import fr.icdc.dei.client.services.TableauService;
import fr.icdc.dei.client.services.TableauServiceAsync;
import fr.icdc.dei.shared.FieldVerifier;
import fr.icdc.dei.shared.dto.PersonneDTO;

public class AccueilPresenterImpl implements AccueilPresenter {
	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final TableauServiceAsync tableauService = GWT.create(TableauService.class);

	private final AccueilVue vue;

	public AccueilPresenterImpl(AccueilVue vue) {
		this.vue = vue;
	}

	public void interrogerServeur() {
		String nomTableau = vue.recupererChampNom();
		if (!FieldVerifier.isTableauValide(nomTableau)) {
			vue.afficherErreur("Merci d'entrer le mot \"personnes\"");
			return;
		}

		vue.reinitialiser(nomTableau);

		if (nomTableau != null && "personnes".equalsIgnoreCase(nomTableau)) {
			tableauService.recupererPersonnes(nomTableau, new AsyncCallback<PersonneDTO[]>() {
				public void onFailure(Throwable caught) {
					vue.afficherErreur("Une erreur s'est produite sur le serveur");
				}

				public void onSuccess(PersonneDTO[] listePersonnes) {
					if (listePersonnes != null && listePersonnes.length > 0) {
						vue.afficherTableau(listePersonnes);
					} else {
						vue.afficherErreur("Aucune personne n'est disponible");
					}
				}
			});
		} else {
			vue.afficherErreur("Merci d'entrer le mot \"personnes\"");
		}
	}

	public void changerEcran(String valeur) {
		Window.alert("Champ cliqué : " + valeur + "\nIt work's baby !");
	}
}
