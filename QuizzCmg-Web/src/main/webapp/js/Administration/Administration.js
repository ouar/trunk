var tableauAdminQuestions;

$(document).ready(function() {

	detail();

	initialiserTypeReponse(1);
});

function getTableauQuestions(jsonData) {

	var dataTable = $('#dataTableAdminQuestions').dataTable({

		"bAutoWidth" : true,
		"bPaginate" : false,
		"bFilter" : false,
		"aoColumns" : [ {
			"bVisible" : true,
		}, {
			"mDataProp" : "libQuestion"
		}, {
			"mDataProp" : "libelleNiveauDifficulte",
		}, {
			"mDataProp" : "intDureeReflexion",
		}, {
			"mDataProp" : "bolUniqueReponse",
		}, {
			"mDataProp" : "urlImage",
		}, {
			"mDataProp" : "TEST",
		}, {
			"mDataProp" : "reponses",
			"bVisible" : false
		} ],
		"aaData" : jsonData,

		'oLanguage' : {
			"sProcessing" : "Veuillez patienter ...",
			"sLengthMenu" : "Nombre de lignes par page _MENU_",
			"sZeroRecords" : "Pas de questions li�es � ce type de sujet",
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

	});

	return dataTable;

}

function fnFormatDetails(nTr, dataTableCible) {
	var aData = dataTableCible.fnGetData(nTr);

	var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';

	var tableaureponses = $.parseJSON(aData['reponses']);

	$.each(tableaureponses, function(i, value) {
		if (value.bolTypeReponse) {
			sOut += '<tr><td>Libll� reponse :</td><td BGCOLOR="#00ff00">'
					+ value.libReponse + '</td></tr>';
		} else {
			sOut += '<tr><td>Libll� reponse:</td><td>' + value.libReponse
					+ '</td></tr>';
		}

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
	nCloneTd.innerHTML = '<img src="../images/datatable/details_open.png">';
	nCloneTd.className = "center";

	$('#dataTableAdminQuestions thead tr').each(function() {
		this.insertBefore(nCloneTh, this.childNodes[0]);
	});

	$('#dataTableAdminQuestions tbody tr').each(function() {
		this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
	});
	tableauAdminQuestions = getTableauQuestions(null);
	$('#dataTableAdminQuestions tbody td img').on(
			'click',
			function() {
				var nTr = $(this).parents('tr')[0];
				if (tableauAdminQuestions.fnIsOpen(nTr)) {
					/* This row is already open - close it */
					this.src = "../images/datatable/details_open.png";
					tableauAdminQuestions.fnClose(nTr);
				} else {
					/* Open this row */
					this.src = "../images/datatable/details_close.png";
					tableauAdminQuestions.fnOpen(nTr, fnFormatDetails(nTr,
							tableauAdminQuestions), 'details');
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

function ajouterReponse() {

	var numReponse = $('#nombreReponses').val();
	numReponse = parseInt(numReponse) + parseInt("1");
	$('#nombreReponses').val(numReponse);
	var sOut = 'R�ponse' + numReponse + '';
	sOut += '<input name="reponse' + numReponse + '"  type="text" value=""/>';
	sOut += 'Vrai/Faux';
	sOut += '<input name="checkReponse' + numReponse + '" id="checkReponse'
			+ numReponse + '" type="checkbox"/>';
	sOut += '<input name="_typeReponse' + numReponse + '" id="_typeReponse'
			+ numReponse + '" type="hidden" value="reponse' + numReponse
			+ '_FAUSSE"/>';
	var aAjouter = numReponse - 1;
	$('#checkReponse' + aAjouter).after(sOut);

	initialiserTypeReponse(numReponse);

}

function initialiserTypeReponse(numReponse) {

	$('#checkReponse' + numReponse).change(
			function() {
				if ($(this).is(':checked')) {
					$('#_typeReponse' + numReponse).val(
							"reponse" + numReponse + "_VRAI");

				} else {
					$('#_typeReponse' + numReponse).val(
							"reponse" + numReponse + "_FAUSSE");

				}
			});
}
