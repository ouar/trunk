$(function($) {
	// choix de la langue fran�aise
	$.datepicker.regional['fr'] = {
		clearText : 'Effacer',
		clearStatus : '',
		closeText : 'Fermer',
		closeStatus : 'Fermer sans modifier',
		prevText : '&nbsp;',
		prevStatus : 'Voir le mois pr�c�dent',
		nextText : '&nbsp;',
		nextStatus : 'Voir le mois suivant',
		currentText : 'Courant',
		currentStatus : 'Voir le mois courant',
		monthNames : [ 'janvier', 'f�vrier', 'mars', 'avril', 'mai', 'juin',
				'juillet', 'ao�t', 'septembre', 'octobre', 'novembre',
				'd�cembre' ],
		monthNamesShort : [ 'Jan', 'F�v', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul',
				'Ao�', 'Sep', 'Oct', 'Nov', 'D�c' ],
		monthStatus : 'Voir un autre mois',
		yearStatus : 'Voir un autre ann�e',
		weekHeader : 'Sm',
		weekStatus : '',
		dayNames : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
				'Vendredi', 'Samedi' ],
		dayNamesShort : [ 'Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam' ],
		dayNamesMin : [ 'Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa' ],
		dayStatus : 'Utiliser DD comme premier jour de la semaine',
		dateStatus : 'Choisir le DD, MM d',
		dateFormat : 'dd/mm/yy',
		firstDay : 1,
		initStatus : 'Choisir la date',
		isRTL : false,
		showAnim : 'slide'//type d'animation "effet slide"
	};

	$.datepicker.setDefaults($.datepicker.regional['fr']);//R�glez tous les date pickers pour que le texte soit en fran�ais.
});

//declaration des dates picker
$(function() {
	//tous les �l�ments input avec un attribut class="datePick" seront de type datepicker
		$( 'input.datePick' ).datepicker({
			showOn: "button", //la saisie de la date s'affiche seulement quand on clique sur l'image plac�e apr�s le champ "datePick"
			buttonImage: "../../images/calendar.gif",  //Initialise le datePicker avec la bonne image
			buttonImageOnly: true //L'image plac�e apr�s le champ "datePick" , est l'�l�ment d�clencheur.
		});
	});
