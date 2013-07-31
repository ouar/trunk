package fr.soat.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.soat.client.presenter.FormPersonPresenter;
import fr.soat.client.util.PersonData;
import fr.soat.shared.Person;

/**
 * Peresenter pour le formulaire de saisie des coordonnées de la personne Il
 * implement la vue de la View
 * 
 * @see FormPersonPresenter.IFormPersonView
 * @author Kader
 * 
 */
public class FormPersonView extends Composite implements
		FormPersonPresenter.IFormPersonView {

	private static FormPersonViewUiBinder uiBinder = GWT
			.create(FormPersonViewUiBinder.class);

	interface FormPersonViewUiBinder extends UiBinder<Widget, FormPersonView> {
	}

	@UiField(provided = true)
	ListBox civilite;

	@UiField
	TextBox nom;

	@UiField
	TextBox prenom;

	@UiField
	TextBox email;

	@UiField
	Button saveBtn;

	private FormPersonPresenter presenter;
	private PersonData personData;
	private static final String BOX_ERROR = "box-error";

	/**
	 * Constructeur sans args du Presenter
	 */
	public FormPersonView() {
		personData = new PersonData();
		civilite = new ListBox();
		initCivilite();
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Populer la liste de civilités
	 */
	private void initCivilite() {
		for (int i = 0; i < personData.getCivilites().length; i++) {
			civilite.addItem(personData.getCivilites()[i], String.valueOf(i));
		}
	}

	@UiHandler("saveBtn")
	void onClick(ClickEvent e) {
		if (checkPerson()) {
			this.presenter.doEnregistrerPerson(getPerson());
		} else {
			Window.alert("Veuillez renseigner les champs obligatoires ");
		}

	}

	/**
	 * Vérifier la validaité des champs obligatoires
	 * 
	 * @return
	 */
	private boolean checkPerson() {
		boolean isValid = true;
		if (fieldIsNotValid(this.nom.getValue())) {
			isValid = false;
		}
		if (fieldIsNotValid(this.prenom.getValue())) {
			isValid = false;
		}
		if (fieldIsNotValid(this.email.getValue())) {
			isValid = false;
		}
		if (civilite.getValue(civilite.getSelectedIndex()) == null) {
			isValid = false;
		}
		return isValid;
	}

	
	/**
	 * Champs valide
	 * 
	 * @param value
	 * @return
	 */
	private boolean fieldIsNotValid(String value) {
		return value == null || value.trim().equals("");
	}

	/**
	 * Retourner la person du formulaire
	 * 
	 * @return
	 */
	private Person getPerson() {
		Person person = new Person();
		person.setCivilite(civilite.getItemText(civilite.getSelectedIndex()));
		person.setNom(nom.getValue());
		person.setPrenom(prenom.getValue());
		person.setEmail(email.getValue());
		return person;
	}

	@Override
	public void setPrenseter(FormPersonPresenter presenter) {
		this.presenter = presenter;
	}

}
