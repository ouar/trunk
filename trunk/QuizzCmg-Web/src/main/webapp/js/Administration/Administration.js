var tableauAdminTypeSujet;
var tableauAdminQuestions;
var tableauAdminLangage;
var adminListTypeSujets = [];

$(document).ready(function() {

	$("#adminbtnAjouter").click(function() {
		ajouterTypeSujet();
	});
	tableauAdminTypeSujet = getTableauTypeSujet(null);

	tableauAdminQuestions = getTableauQuestions(null);

	detail();

	/***/
	$.fn.ObjetAdmin = function(idLangage, libelleLangage, idTypeSujet, libelleTypeSujet, idNiveau, libelleNiveau) {

		this.elements = {
			idLangage : idLangage,
			libelleLangage : libelleLangage,
			idTypeSujet : idTypeSujet,
			libelleTypeSujet : libelleTypeSujet,
			idNiveau : idNiveau,
			libelleNiveau : libelleNiveau
		};
	};

});

function getTableauTypeSujet(jsonData) {

	var dataTable = $('#dataTableAdminTypeSujet').dataTable({

		"bAutoWidth" : false,
		"bPaginate" : false,
		"bFilter" : false,
		"aoColumns" : [ {
			"mDataProp" : "libelleLangage"
		}, {
			"mDataProp" : "libelleTypeSujet"
		}, {
			"mDataProp" : "idTypeSujet"
		}, {
			"mDataProp" : "idLangage"
		} ],
		"aaData" : jsonData,

		'oLanguage' : {
			"sProcessing" : "Veuillez patienter ...",
			"sLengthMenu" : "Nombre de lignes par page _MENU_",
			"sZeroRecords" : "Pas de r&eacute;sultat correspondant &agrave; votre recherche.",
			"sInfo" : "_START_ &agrave; _END_/_TOTAL_ r&eacute;sultat(s)",
			"sInfoEmpty" : "0 r&eacute;sultat(s)",
			"sInfoFiltered" : "(sur _MAX_ donn&eacute;es)",
			"sInfoPostFix" : "",
			"sSearch" : "Filtre &nbsp;(Destination)",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "Premi\u00e8re page",
				"sPrevious" : "Page pr\u00e9c\u00e9dente",
				"sNext" : "Page suivante",
				"sLast" : "Derni\u00e8re page"
			}

		},
		"fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
			var idLangage = aData["libelleLangage"];
			var libelleLangage = aData["libelleTypeSujet"];
			var idTypeSujet = aData["idTypeSujet"];
			// var libelleTypeSujet = aData["libelleTypeSujet"];
			// var idNiveau = aData["idNiveau"];
			// var libelleNiveau = aData["libelleNiveau"];

			var libelleTypeSujet;
			var idNiveau;
			var libelleNiveau;
			var imgSup = "<p style='text-align: center;'><a onclick=\"supprimerTypeSujet(" + idLangage + ",'" + libelleLangage + "'," + idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" + libelleNiveau + "');\" style='cursor: pointer;'><img src='../images/pictos_supprimer.gif' title='Supprimer' alt='Supprimer'></a></p>";
			var imgTag = "<p style='text-align: center;'><a onclick=\"ajouterQuestion(" + idLangage + ",'" + libelleLangage + "'," + idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" + libelleNiveau + "');\" style='cursor: pointer;'><img src='../images/pictos_supprimer.gif' title='Ajouter Question' alt='Ajouter Question'></a></p>";
			$('td:eq(2)', nRow).html(imgTag); // quand il
			$('td:eq(3)', nRow).html(imgSup); // quand il
			// s'agit de la
			// cellule
			// 3 on rajoute le code html
			return nRow;
		}

	});

	return dataTable;

	//	
}

function getTableauQuestions(jsonData) {

	var dataTable = $('#dataTableAdminQuestions').dataTable({

		"bAutoWidth" : true,
		"bPaginate" : false,
		"bFilter" : false,
		/*
		 * "aoColumns" : [ { "mDataProp" : "libelleLangage" }, { "mDataProp" :
		 * "libelleTypeSujet" }, { "mDataProp" : "idTypeSujet" }, { "mDataProp" :
		 * "idLangage" }, { "mDataProp" : "idLangage" }, { "mDataProp" :
		 * "idLangage" }, { "mDataProp" : "idLangage" } ],
		 */
		"aaData" : jsonData,

		'oLanguage' : {
			"sProcessing" : "Veuillez patienter ...",
			"sLengthMenu" : "Nombre de lignes par page _MENU_",
			"sZeroRecords" : "Pas de questions liées à ce type de sujet",
			"sInfo" : "",
			"sInfoEmpty" : "",
			"sInfoFiltered" : "(sur _MAX_ donn&eacute;es)",
			"sInfoPostFix" : "",
			"sSearch" : "Filtre &nbsp;(Destination)",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "Premi\u00e8re page",
				"sPrevious" : "Page pr\u00e9c\u00e9dente",
				"sNext" : "Page suivante",
				"sLast" : "Derni\u00e8re page"
			}

		}
	/*
	 * "fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
	 * var idLangage = aData["libelleLangage"]; var libelleLangage =
	 * aData["libelleTypeSujet"]; var idTypeSujet = aData["idTypeSujet"]; // var
	 * libelleTypeSujet = aData["libelleTypeSujet"]; // var idNiveau =
	 * aData["idNiveau"]; // var libelleNiveau = aData["libelleNiveau"];
	 * 
	 * var libelleTypeSujet; var idNiveau; var libelleNiveau; var imgSup = "<p style='text-align: center;'><a
	 * onclick=\"supprimerTypeSujet(" + idLangage + ",'" + libelleLangage + "'," +
	 * idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" +
	 * libelleNiveau + "');\" style='cursor: pointer;'><img
	 * src='../images/pictos_supprimer.gif' title='Supprimer' alt='Supprimer'></a></p>";
	 * var imgTag = "<p style='text-align: center;'><a
	 * onclick=\"ajouterQuestion(" + idLangage + ",'" + libelleLangage + "'," +
	 * idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" +
	 * libelleNiveau + "');\" style='cursor: pointer;'><img
	 * src='../images/pictos_supprimer.gif' title='Ajouter Question'
	 * alt='Ajouter Question'></a></p>"; $('td:eq(2)', nRow).html(imgTag); //
	 * quand il $('td:eq(3)', nRow).html(imgSup); // quand il // s'agit de la //
	 * cellule // 3 on rajoute le code html return nRow; }
	 */

	});

	return dataTable;

	//	
}

function getTableauLangage(jsonData) {

	var dataTable = $('#dataTableAdminLangage').dataTable({

		"bAutoWidth" : false,
		"bPaginate" : false,
		"bFilter" : false,
		// "aoColumnDefs" : [ {
		// "bSortable" : false,
		// "aTargets" : [ 0 ]
		// } ],

		"aoColumns" : [ {
			"bVisible" : true,
		}, {
			"mDataProp" : "libelleLangage"
		}, {
			"mDataProp" : "idLangage",
		}, {
			"mDataProp" : "idTypeSujet",
			"bVisible" : false
		} ],

		"aaData" : jsonData,

		'oLanguage' : {
			"sProcessing" : "Veuillez patienter ...",
			"sLengthMenu" : "Nombre de lignes par page _MENU_",
			"sZeroRecords" : "Pas de r&eacute;sultat correspondant &agrave; votre recherche.",
			"sInfo" : "_START_ &agrave; _END_/_TOTAL_ r&eacute;sultat(s)",
			"sInfoEmpty" : "0 r&eacute;sultat(s)",
			"sInfoFiltered" : "(sur _MAX_ donn&eacute;es)",
			"sInfoPostFix" : "",
			"sSearch" : "Filtre &nbsp;(Destination)",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "Premi\u00e8re page",
				"sPrevious" : "Page pr\u00e9c\u00e9dente",
				"sNext" : "Page suivante",
				"sLast" : "Derni\u00e8re page"
			}

		}
	/*
	 * "fnRowCallback" : function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
	 * var idLangage = aData["libelleLangage"]; var libelleLangage =
	 * aData["libelleTypeSujet"]; var idTypeSujet = aData["idTypeSujet"]; // var
	 * libelleTypeSujet = aData["libelleTypeSujet"]; // var idNiveau =
	 * aData["idNiveau"]; // var libelleNiveau = aData["libelleNiveau"];
	 * 
	 * var libelleTypeSujet; var idNiveau; var libelleNiveau; var imgSup = "<p style='text-align: center;'><a
	 * onclick=\"supprimerTypeSujet(" + idLangage + ",'" + libelleLangage + "'," +
	 * idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" +
	 * libelleNiveau + "');\" style='cursor: pointer;'><img
	 * src='../images/pictos_supprimer.gif' title='Supprimer' alt='Supprimer'></a></p>";
	 * var imgTag = "<p style='text-align: center;'><a
	 * onclick=\"ajouterQuestion(" + idLangage + ",'" + libelleLangage + "'," +
	 * idTypeSujet + ",'" + libelleTypeSujet + "'," + idNiveau + ",'" +
	 * libelleNiveau + "');\" style='cursor: pointer;'><img
	 * src='../images/pictos_supprimer.gif' title='Ajouter Question'
	 * alt='Ajouter Question'></a></p>"; $('td:eq(2)', nRow).html(imgTag); //
	 * quand il $('td:eq(3)', nRow).html(imgSup); // quand il // s'agit de la //
	 * cellule // 3 on rajoute le code html return nRow; }
	 */

	});

	return dataTable;

	//	
}



function ajouterTypeSujet() {
	var idLangageSelectionne = $("#idLangage option:selected").val();
	var libelleLangageSelectionne = $("#idLangage option:selected").text();
	var libelleTypeSujet = $("#libelleTypeSujet").val();
	if (libelleTypeSujet != null && libelleTypeSujet != "") {

		var administrationNouveau = new $.fn.ObjetAdmin(idLangageSelectionne, libelleLangageSelectionne, null, libelleTypeSujet, '', '');

		adminListTypeSujets.push(administrationNouveau);
		raffraichirTableauTypeSujet();
	}

}

function raffraichirTableauTypeSujet() {

	var jsonData = [];

	$.grep(adminListTypeSujets, function(ObjetAdmin, index) {

		var myObject = new Object();
		myObject.idLangage = ObjetAdmin.elements.idLangage;
		myObject.libelleLangage = ObjetAdmin.elements.libelleLangage;
		myObject.idTypeSujet = ObjetAdmin.elements.idTypeSujet;
		myObject.libelleTypeSujet = ObjetAdmin.elements.libelleTypeSujet;
		myObject.idNiveau = ObjetAdmin.elements.idNiveau;
		myObject.libelleNiveau = ObjetAdmin.elements.libelleNiveau;
		jsonData.push(myObject);

	});
	try {

		tableauAdminTypeSujet.fnDestroy();
		tableauAdminTypeSujet = getTableauTypeSujet(jsonData);
	} catch (e) {

	}
	 $('#typeSujetAjoute').val(JSON.stringify(jsonData));


}



function fnFormatDetails(nTr,dataTableCible) {
	var aData = dataTableCible.fnGetData(nTr);

	var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';

	var tableauIdTypeSujet = $.parseJSON(aData['idTypeSujet']);

	$.each(tableauIdTypeSujet, function(i, value) {
		sOut += '<tr><td>Libllé sujet:</td><td>' + value.libelle + '</td></tr>';
	});
	sOut += '</table>';
	return sOut;
}

function detail() {
	/*
	 * Insert a 'details' column to the table
	 */
	var nCloneTh = document.createElement('th');
	var nCloneTd = document.createElement('td');
	nCloneTd.innerHTML = '<img src="../images/pictos_supprimer.gif">';
	nCloneTd.className = "center";

	$('#dataTableAdminLangage thead tr').each(function() {
		this.insertBefore(nCloneTh, this.childNodes[0]);
	});

	$('#dataTableAdminLangage tbody tr').each(function() {
		this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
	});
	tableauAdminLangage = getTableauLangage(null);
	$('#dataTableAdminLangage tbody td img').on('click', function() {
		var nTr = $(this).parents('tr')[0];
		if (tableauAdminLangage.fnIsOpen(nTr)) {
			/* This row is already open - close it */
			this.src = "../images/pictos_supprimer.gif";
			tableauAdminLangage.fnClose(nTr);
		} else {
			/* Open this row */
			this.src = "../images/pictos_supprimer.gif";
			tableauAdminLangage.fnOpen(nTr, fnFormatDetails(nTr,tableauAdminLangage), 'details');
		}
	});
}

function ajouterTypeSujet(idLangage) {
	
	$('#vueEncoursUtlisation').val("typeSujet");
	
	$('#idLangage').val(idLangage);
	
}


function ajouterLangage() {
	
	$('#vueEncoursUtlisation').val("langage");
	
	$('#idLangage').val(-1);
	
}

