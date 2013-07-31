package fr.icdc.dei.client.vue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import fr.icdc.dei.client.AccueilPresenter;
import fr.icdc.dei.client.AccueilVue;
import fr.icdc.dei.client.handler.BoutonHandler;
import fr.icdc.dei.shared.dto.PersonneDTO;

public class AccueilVueImpl implements AccueilVue {
	private AccueilPresenter presenter;
	private Button sendButton;
	private TextBox nameField;
	private Label errorLabel;
	private CellTable<PersonneDTO> cellTable;
	private SimplePager pagineur;
	private List<PersonneDTO> listeElementsTableau;
	private final static CellTable.Resources CELL_RESOURCES = GWT.create(CellTableResources.class);

	public AccueilVueImpl() {

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void lancerApplication() {
		RootPanel rootPanel = RootPanel.get("appliGWT");
		rootPanel.setStyleName("rootPanel");

		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		rootPanel.add(dockLayoutPanel);
		dockLayoutPanel.setSize("572px", "464px");

		final Label lblDitesBonjour = new Label("Récupérez la liste de votre choix :");
		dockLayoutPanel.addNorth(lblDitesBonjour, 3.0);

		HorizontalPanel hPanel = new HorizontalPanel();
		nameField = new TextBox();
		nameField.setText("personnes");
		nameField.setFocus(true);
		hPanel.add(nameField);

		errorLabel = new Label();
		errorLabel.setStyleName("erreur");
		hPanel.add(errorLabel);

		dockLayoutPanel.addNorth(hPanel, 3.0);

		sendButton = new Button("Récupérer");
		dockLayoutPanel.addNorth(sendButton, 3.0);
		sendButton.addStyleName("sendButton");
		BoutonHandler boutonHandler = new BoutonHandler(presenter);
		sendButton.addClickHandler(boutonHandler);

		cellTable = new CellTable<PersonneDTO>(8, CELL_RESOURCES);
		cellTable.setWidth("100%", true);

		SimplePager.Resources paginateurResources = GWT.create(SimplePager.Resources.class);
		pagineur = new SimplePager(TextLocation.CENTER, paginateurResources, false, 0, false);
		pagineur.setDisplay(cellTable);
		pagineur.setVisible(false);
		dockLayoutPanel.addSouth(pagineur, 3.0);

		dockLayoutPanel.add(cellTable);

		TextColumn<PersonneDTO> colonneNom = new TextColumn<PersonneDTO>() {
			@Override
			public String getValue(PersonneDTO object) {
				return object.getNom();
			}
		};
		TextColumn<PersonneDTO> colonnePrenom = new TextColumn<PersonneDTO>() {
			@Override
			public String getValue(PersonneDTO object) {
				return object.getPrenom();
			}
		};
		Column<PersonneDTO, Number> colonneAge = new Column<PersonneDTO, Number>(new NumberCell()) {
			@Override
			public Number getValue(PersonneDTO object) {
				return (Number) object.getAge();
			}
		};

		Column<PersonneDTO, String> colonneCliquable = new Column<PersonneDTO, String>(new CelluleCliquable(presenter)) {
			@Override
			public String getValue(PersonneDTO object) {
				return object.getNom();
			}
		};

		colonneNom.setSortable(true);
		colonnePrenom.setSortable(true);
		colonneAge.setSortable(true);
		colonneCliquable.setSortable(true);

		cellTable.setColumnWidth(colonneNom, 30.0, Unit.PCT);
		cellTable.setColumnWidth(colonnePrenom, 30.0, Unit.PCT);
		cellTable.setColumnWidth(colonneAge, 20.0, Unit.PCT);
		cellTable.setColumnWidth(colonneCliquable, 20.0, Unit.PCT);

		cellTable.addColumn(colonneNom, "Nom");
		cellTable.addColumn(colonnePrenom, "Prénom");
		cellTable.addColumn(colonneAge, "Age");
		cellTable.addColumn(colonneCliquable, "Clic !");

		ListDataProvider<PersonneDTO> fournisseurDonnees = new ListDataProvider<PersonneDTO>();
		fournisseurDonnees.addDataDisplay(cellTable);
		listeElementsTableau = fournisseurDonnees.getList();

		ListHandler<PersonneDTO> triHandler = new ListHandler<PersonneDTO>(listeElementsTableau);

		triHandler.setComparator(colonneNom, new Comparator<PersonneDTO>() {
			public int compare(PersonneDTO o1, PersonneDTO o2) {
				return (o1.getNom().compareTo(o2.getNom()));
			}
		});
		triHandler.setComparator(colonnePrenom, new Comparator<PersonneDTO>() {
			public int compare(PersonneDTO o1, PersonneDTO o2) {
				return (o1.getPrenom().compareTo(o2.getPrenom()));
			}
		});
		triHandler.setComparator(colonneAge, new Comparator<PersonneDTO>() {
			public int compare(PersonneDTO o1, PersonneDTO o2) {
				if (o1 == null) {
					return -1;
				} else if (o2 == null) {
					return +1;
				} else {
					if (o1.getAge() < o2.getAge()) {
						return -1;
					} else if (o1.getAge() > o2.getAge()) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		});
		triHandler.setComparator(colonneCliquable, new Comparator<PersonneDTO>() {
			public int compare(PersonneDTO o1, PersonneDTO o2) {
				return (o1.getNom().compareTo(o2.getNom()));
			}
		});
		cellTable.addColumnSortHandler(triHandler);
		cellTable.setVisible(false);
	}

	public void enregistrerPresenter(AccueilPresenter presenter) {
		this.presenter = presenter;
	}

	public String recupererChampNom() {
		errorLabel.setText("");
		return nameField.getText();
	}

	public void afficherErreur(String erreur) {
		errorLabel.setText(erreur);
	}

	public void reinitialiser(String nom) {
		sendButton.setEnabled(false);
	}

	public void afficherTableau(PersonneDTO[] listePersonnes) {
		listeElementsTableau.clear();
		List<PersonneDTO> resultatsPersonnes = Arrays.asList(listePersonnes);
		cellTable.setRowCount(resultatsPersonnes.size(), true);
		for (PersonneDTO resultat : resultatsPersonnes) {
			listeElementsTableau.add(resultat);
		}
		cellTable.setVisible(true);
		pagineur.setVisible(true);
	}
}
