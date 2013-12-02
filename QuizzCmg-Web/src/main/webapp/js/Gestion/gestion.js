var tableauPanier;

$(document).ready(function() {

	$("#btnAjouter").click(function() {
		ajouterTypeSujetPanier();
	});

	genererQuizz();

	tableauPanier = getTableauPanier(null);

	createObject();

	selectLangage();

	$.supprimerTypeSujet = function(idTypeSujet, idNiveau, arr) {
		return $.grep(arr, function(elem, index) {

			return !(elem.elements.idTypeSujet == idTypeSujet && elem.elements.idNiveau == idNiveau);
		});
	};
//	$( "#generation" ).submit(function( event ) {
//		$('#idDujetDifficulte').val(JSON.stringify($('#idDujetDifficulte').val()));
//		event.preventDefault();
//		});
	

});

function getTableauPanier(jsonData) {

	var dataTable = $('#dataTablepanier').dataTable({
		// "oLanguage": { "sUrl":
		// "/ressourcescamino/Commun/datatable/datatable_fr.txt"}
		"bJQueryUI" : true,
		"bAutoWidth" : false,
		"iDisplayLength" : 2,
		"iDisplayStart" : 0,
		"aLengthMenu" : [ '2', '5', '10', '25', '50', '100', '250' ],
		"bStateSave" : true,
		"bRefreshCookie" : false,
		"aaSorting" : [ [ 1, "asc" ] ],
		"bPaginate" : false,
		"bFilter" : false,
		"aoColumns" : [ {
			"mDataProp" : "libelleLangage"
		}, {
			"mDataProp" : "libelleTypeSujet"
		}, {
			"mDataProp" : "libelleNiveau"
		}, {
			"mDataProp" : "idTypeSujet"
		}, {
			"mDataProp" : "idLangage",
			"bVisible" : false
		}, {
			"mDataProp" : "idNiveau",
			"bVisible" : false
		} ],
		"aaData" : jsonData,

		'oLanguage' : {
			'sUrl' : '../../js/datatable_fr.txt'// choix de la langue française
		},
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			var idLangage = aData["idLangage"];
			var libelleLangage = aData["libelleLangage"];
			var idTypeSujet = aData["idTypeSujet"];
			var libelleTypeSujet = aData["libelleTypeSujet"];
			var idNiveau = aData["idNiveau"];
			var libelleNiveau = aData["libelleNiveau"];
			var imgTag = "<p style='text-align: center;'><a onclick=\"supprimerTypeDeSujetPanier(" + idLangage + ",'" + libelleLangage + "'," + idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" + libelleNiveau + "');\" style='cursor: pointer;'><img src='../../images/pictos_supprimer.gif' title='Supprimer' alt='Supprimer'></a></p>";
			$('td:eq(3)', nRow).html(imgTag); // quand il s'agit de la cellule
			// 3 on rajoute le code html
			return nRow;
		}

	});

	return dataTable;

	//	
}

function createObject() {

	/***/
	$.fn.Langage = function(idLangage, libelleLangage, listTypeSujet) {

		this.elements = {
			idLangage : idLangage,
			libelleLangage : libelleLangage,
			listTypeSujet : listTypeSujet
		};

	};
	/***/
	$.fn.TypeSujet = function(idTypeSujet, libelleTypeSujet, listNiveau) {

		this.elements = {
			idTypeSujet : idTypeSujet,
			libelleTypeSujet : libelleTypeSujet,
			listNiveau : listNiveau
		};

	};

	/***/
	$.fn.NiveauQuestion = function(idNiveau, libelleNiveau) {

		this.elements = {
			idNiveau : idNiveau,
			libelleNiveau : libelleNiveau
		};

	};
	/***/
	$.fn.Quizz = function(listObjetPanier) {

		//
		this.elements = {
			listObjetPanier : listObjetPanier
		};
		//
		this.supprimerTypeSujet = function(idTypeSujet, idNiveau) {

			this.elements.listObjetPanier = $.supprimerTypeSujet(idTypeSujet, idNiveau, this.elements.listObjetPanier);

		};
		//
		this.ajouterTypeSujet = function(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau) {

			var objetPanier = new $.fn.ObjetPanier(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau);

			this.elements.listObjetPanier.push(objetPanier);
		};
		//
		this.selectionnable = function(idNiveau, idTypeSujet) {

			isElementSelectionnable = false;

			$.each(this.elements.listObjetPanier, function() {

				if ((this.elements.idNiveau == idNiveau && this.elements.idTypeSujet == idTypeSujet)) {
					isElementSelectionnable = true;

				}

			});
			return isElementSelectionnable;
		};

	};

	/***/
	$.fn.ObjetPanier = function(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau) {

		this.elements = {
			idLangage : idLangage,
			libelleLangage : libelleLangage,
			idTypeSujet : idTypeSujet,
			libelleTypeSujet : libelleTypeSujet,
			idNiveau : idNiveau,
			libelleNiveau : libelleNiveau
		};
	};

}

function selectLangage() {

	$("#tfIdLangage").change(function() {

		raffraichirOngletTypeSujet();
	});

}

function ajouterTypeSujetPanier(idTypeSujet, idNiveau) {

	var isChecked = $("input:checked").length;

	if (isChecked > 0) {
		var idTypeSujet;
		var libelleTypeSujet;
		var idLangage;
		var libelleLangage;
		var idNiveau;
		var libelleNiveau;
		$("input:checked").each(function() {
			var donnesChecke = this.value.split("_");
			idTypeSujet = donnesChecke[0];
			libelleTypeSujet = donnesChecke[1];
			idLangage = donnesChecke[2];
			libelleLangage = donnesChecke[3];
			idNiveau = $("#tListNiveau" + idTypeSujet + " option:selected").val();
			libelleNiveau = $("#tListNiveau" + idTypeSujet + " option:selected").text();

			aSelectionner.supprimerTypeSujet(idTypeSujet, idNiveau);

			panier.ajouterTypeSujet(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau);

		});
		raffraichirPanier();
	} else {
		alert("Veuillez choisir un type de sujet !");
	}

}

function supprimerTypeDeSujetPanier(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau) {

	panier.supprimerTypeSujet(idTypeSujet, idNiveau);

	aSelectionner.ajouterTypeSujet(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau);

	raffraichirPanier();
	raffraichirOngletTypeSujet();

}

function raffraichirPanier() {

	var jsonData = [];

	$.grep(panier.elements.listObjetPanier, function(ObjetPanier, index) {

		var myObject = new Object();
		myObject.idLangage = ObjetPanier.elements.idLangage;
		myObject.libelleLangage = ObjetPanier.elements.libelleLangage;
		myObject.idTypeSujet = ObjetPanier.elements.idTypeSujet;
		myObject.libelleTypeSujet = ObjetPanier.elements.libelleTypeSujet;
		myObject.idNiveau = ObjetPanier.elements.idNiveau;
		myObject.libelleNiveau = ObjetPanier.elements.libelleNiveau;
		jsonData.push(myObject);

	});
	try {
		tableauPanier.fnDestroy();
		tableauPanier = getTableauPanier(jsonData);
	} catch (e) {

	}

	raffraichirOngletTypeSujet();
	$('#panier').val(JSON.stringify(jsonData));
	// }

}

function raffraichirOngletTypeSujet() {

	var idLangageSelectionne = $("#tfIdLangage option:selected").val();

	for ( var i = 0; i < tableauListLangage.length; i++) {

		if (idLangageSelectionne == tableauListLangage[i].elements.idLangage) {

			var idLangage = tableauListLangage[i].elements.idLangage;

			var libelleLangage = tableauListLangage[i].elements.libelleLangage;

			var listTypeSujet = tableauListLangage[i].elements.listTypeSujet;

			$('#tabTypeSujet > tbody  > tr').each(function() {
				var tr = $(this);
				tr.remove();
			});
			for ( var j = 0; j < listTypeSujet.length; j++) {

				var idTypeSujet = listTypeSujet[j].elements.idTypeSujet;
				var libelleTypeSujet = listTypeSujet[j].elements.libelleTypeSujet;
				var listNiveauTypeSujet = listTypeSujet[j].elements.listNiveau;
				var select = "<select id='tListNiveau" + idTypeSujet + "'" + "name='tListNiveau" + idTypeSujet + "'>";
				var options = '';
				var auMoinsUnElement = false;
				for ( var z = 0; z < listNiveauTypeSujet.length; z++) {
					var idNiveau = listNiveauTypeSujet[z].elements.idNiveau;
					var libelleNiveau = listNiveauTypeSujet[z].elements.libelleNiveau;

					if (aSelectionner.selectionnable(idNiveau, idTypeSujet)) {
						options += '<option value="' + idNiveau + '">' + libelleNiveau + '</option>';
						auMoinsUnElement = true;
					}
				}
				select += options;
				select += "</select>";

				if (auMoinsUnElement) {
					var valeur = idTypeSujet + "_" + libelleTypeSujet + "_" + idLangage + "_" + libelleLangage;
					$("#tabTypeSujet").append('<tr><td style="padding-top: 5px; padding-bottom: 5px;"><input type="checkbox"  value=' + valeur + '>' + libelleTypeSujet + '</td>	<td style="padding-top: 5px; padding-bottom: 5px;">' + select + '</td></tr>');
				}

			}

		}

	}
	var jsonData = [];
	$.grep(aSelectionner.elements.listObjetPanier, function(ObjetPanier, index) {

		var myObject = new Object();
		myObject.idLangage = ObjetPanier.elements.idLangage;
		myObject.libelleLangage = ObjetPanier.elements.libelleLangage;
		myObject.idTypeSujet = ObjetPanier.elements.idTypeSujet;
		myObject.libelleTypeSujet = ObjetPanier.elements.libelleTypeSujet;
		myObject.idNiveau = ObjetPanier.elements.idNiveau;
		myObject.libelleNiveau = ObjetPanier.elements.libelleNiveau;
		jsonData.push(myObject);

	});

	$('#aselectionner').val(JSON.stringify(jsonData));

}

function genererQuizz() {

	$("#generation").submit(function() {
		var isFormulaireValid = true;

		if (panier.elements.listObjetPanier.length <= 0) {

			isFormulaireValid = false;
			alert("Veuillez choisir un type de sujet !");

		}
		if (isFormulaireValid && ($('#idChampNomCandidat').val() == "")) {

			isFormulaireValid = false;
			alert("Veuillez saisir le nom du candidat");

		}
		if (isFormulaireValid && ($('#idChampPrenomCandidat').val() == "")) {

			isFormulaireValid = false;
			alert("Veuillez saisir le prénom du candidat");

		}

		return isFormulaireValid;
	});

}
