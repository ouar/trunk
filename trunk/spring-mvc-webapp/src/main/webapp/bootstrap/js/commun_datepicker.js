$(function($) {
	// choix de la langue française
	$.datepicker.regional['fr'] = {
		clearText : 'Effacer',
		clearStatus : '',
		closeText : 'Fermer',
		closeStatus : 'Fermer sans modifier',
		prevText : '&nbsp;',
		prevStatus : 'Voir le mois précédent',
		nextText : '&nbsp;',
		nextStatus : 'Voir le mois suivant',
		currentText : 'Courant',
		currentStatus : 'Voir le mois courant',
		monthNames : [ 'janvier', 'février', 'mars', 'avril', 'mai', 'juin',
				'juillet', 'août', 'septembre', 'octobre', 'novembre',
				'décembre' ],
		monthNamesShort : [ 'Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul',
				'Aoû', 'Sep', 'Oct', 'Nov', 'Déc' ],
		monthStatus : 'Voir un autre mois',
		yearStatus : 'Voir un autre année',
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

	$.datepicker.setDefaults($.datepicker.regional['fr']);//Réglez tous les date pickers pour que le texte soit en français.
});

//declaration des dates picker
$(function() {
	//tous les éléments input avec un attribut class="datePick" seront de type datepicker
		$( 'input.datePick' ).datepicker({
			showOn: "button", //la saisie de la date s'affiche seulement quand on clique sur l'image placée après le champ "datePick"
			buttonImage: "../../images/calendar.gif",  //Initialise le datePicker avec la bonne image
			buttonImageOnly: true //L'image placée après le champ "datePick" , est l'élément déclencheur.
		});
	});
