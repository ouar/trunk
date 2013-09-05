/*
  Fichier: javascript.js

  Framework ICDC / DEI - couche de présentation
  
  Modifié pour utilisation conjointe avec la taglib DEI-Layout
  
*/


/*
   La variable debug détermine si l'execution des fonctions contenues dans les
   fonctions Javascript définies ci-dessous doivent etre exécutées ou pas en mode debug
   Conditionne l'affichage de certains messages de debug
   Mettre cette variable à false avant livraison
*/
var debug = false;

/*
   La variable bEstModifie permet de controler qu'une modification a ete effectuee sur un formulaire
   Elle est positionnee ? false lors du chargement du formulaire
   Elle est positionnee a true lorsqu'une modification sur le formulaire est effectuee par l'utilisateur :
   - Lors de l'evenement onChange des zones de saisies
   - Lors de l'activations des element d'aide a la saisie : calendrier, boutons d'aide.
*/
var bEstModifie = false;

/*
   La variable bFirstTabIndex permet de fixer qu'une modification a ete effectuee sur un formulaire
   Elle est positionnee à false lors du chargement du formulaire
   Elle est positionnee à true lorsqu'une modification sur le formulaire est effectuee par l'utilisateur :
   - Lors de l'evenement onChange des zones de saisies
   - Lors de l'activations des element d'aide a la saisie : calendrier, boutons d'aide.
*/
var bFirstTabIndex = false;

var bCancel = false;



/*****************************************************************************
function initFunction()

 Rôle :
 - changer dynamiquement le style des champs obligatoires du formulaire
	 (en utilisant la classe INPUTOBLIGATOIRE d?finie dans la feuille de style)
 - donner le focus au premier ?l?ment du formulaire (tabindex = 1)
 - affecter la fonction changeChamp a l'événement onChange des éléments du formulaire

Utilisation :
Cette fonction est systématiquement appelée au chargement des pages contenant un formulaire.
Elle est appel?e dans la balise BODY :
		 <BODY  onload="initFunction();">

{
	for (i=0; i<window.document.forms.length; i++)
	{
		// La fonction colorForm permet de colorier les champs obligatoires du formulaire
		colorForm(window.document.forms[i], "INPUTOBLIGATOIRE");

		for (j=0; j<window.document.forms[i].length; j++)
		{
			targetElement = window.document.forms[i][j];

			// Recherche du premier element du formulaire
			var firstElement;
			if (targetElement.getAttribute("tabindex") == 1)
			{
				firstElement = targetElement;
			}
			Onchange = targetElement.getAttribute("WAFOnchange");			
			// Affectation de la fonction changeChamp a l'evenement onChange
			// (cette fonction permet de detecter tout changement dans un formulaire)

			if(!!Onchange){
				targetElement.onchange = new Function('changeChamp(this);'+Onchange+';');
			}else{
				targetElement.onchange = function(){changeChamp(this);};
			}			

			// CONSTAT : Lorsque des champs de type CheckBox d'un formulaire ne sont pas coch?s,
			// ils ne sont pas soumis avec le formulaire dans la requ?te HTTP
			// PB : On ne sait donc pas d?tecter qu'une CheckBox a ?t? d?coch?e.
			// Pour r?pondre a ce probl?me, il convient de mettre en place syst?matiquement
			// les m?canismes suivants:
			// - associer ? chaque ?l?ment de type CheckBox un ?l?ment de type Hidden
			// - d?clarer pour chasque ?l?ment de type CheckBox les attributs suivants:
			//     WAFCheckedElement, WAFCheckedValue et WAFUnCheckedValue
			// - WAFCheckedElement: doit contenir la m?me valeur que l'attribut 'name' du champ Hidden associ? ? la CheckBox
			// Le code suivant permet de mettre ? jour automatiquement le champ Hidden  :
			//  - avec la valeur WAFCheckedValue lorsque la checkbox est coch?e
			//  - avec la valeur WAFUnCheckedValue lorsque la checkbox est d?coch?e
			if (targetElement.type == "checkbox")
			{
				CheckedElement = targetElement.getAttribute("WAFCheckedElement");
				CheckedValue = targetElement.getAttribute("WAFCheckedValue");
				UnCheckedValue = targetElement.getAttribute("WAFUnCheckedValue");

				if (targetElement.name != null && targetElement.name != '')
				{
					if (debug)
					alert('Attention, pour une CheckBox il ne faut pas renseigner l\'attribut \'name\', afin d\'?viter un conflit ?ventuel avec le champ Hidden associ?!');
				}

				if (CheckedElement  != null && CheckedElement  != "" && CheckedValue  != null && CheckedValue  != "" && UnCheckedValue != null && UnCheckedValue != "" )
				{
					eval('ElementChecked = window.document.forms[i].' + CheckedElement + ';');
					if (ElementChecked == null )
					{
						if (debug)
						alert('Attention, un ?l?ment de nom ' + CheckedElement + ' devrait ?tre associ? ? la checkbox !');
					}
					else
					{
						if (targetElement.value == CheckedValue)
						{
							targetElement.checked=true;
							eval('window.document.forms[i].'+CheckedElement +'.value="'+ CheckedValue +'";');
						}
						if (targetElement.value == UnCheckedValue)
						{
							targetElement.checked=false;
							eval('window.document.forms[i].'+CheckedElement +'.value="'+ UnCheckedValue +'";');
						}
						k = i.toString() ;
						targetElement.onclick = function(){if (this.checked) {eval('window.document.forms['+ k +'].'+CheckedElement +'.value="'+ CheckedValue +'";');} else {eval('window.document.forms[' + k +'].'+CheckedElement +'.value="'+ UnCheckedValue +'";');} };
					}
				}
				else
				{
					if (debug)
					alert('Attention, les attributs WAFCheckedElement, WAFCheckedValue et WAFUnCheckedValue doivent ?tre d?finis pour la checkBox !');
				}
			}
			// fin traitement de la checkbox

		}
	}
}

*****************************************************************************/
function colorForm(frm, className)
/*
Role : colorForm permet de colorier les champs obligatoires du formulaire frm
en changeant son style (changement de la classe)

parametres :
- frm   : formlaire a colorie
- className : classe associee aux elements obligatoires (definie dans la feuille de style)
*/
{
	var i;
	var j = 1;

	for(i = 0; i < frm.length; i++)
	{
		targetElement =  frm[i] ;

		obligatoire = targetElement.getAttribute("WAFObligatoire");
		if(obligatoire == "true")
		{
			//Si il n'y est pas d?ja
			if(targetElement.className.indexOf(className)==-1){
				targetElement.className = targetElement.className+" "+className;
			}
		}
		else if(targetElement.className.indexOf(className) > -1){
			targetElement.className = targetElement.className.substring(0, targetElement.className.indexOf(className));
		}
	}
}

/******************************************************************************/
function reInitFormColor(){
	for( i = 0; i< window.document.forms.length; i++) {
		for(j = 0; j < window.document.forms[i].length; j++) {
			tmpTargetElement = window.document.forms[i][j];
			var k = tmpTargetElement.className.indexOf("INPUTPROBLEME");
			if(k > -1) {
				tmpTargetElement.className = tmpTargetElement.className.substring(0, k) 
				                             + tmpTargetElement.className.substring(k + 13);
			}
		}
	}
}	



/*****************************************************************************
Rôle : Contrôle de la validité de la saisie effectuée sur l'ensemble des 
éléments d'un formulaire passé en paramètre

Les controles sont effectués, sur le formulaire, élément par élément.
Dès qu'un problème est rencontre, on l'affiche et les contrôles sont arrètés.
Un formulaire ne peut être soumis tant que des problèmes sont détectés.
(EXCEPTION: l'appui sur le bouton Annuler DISPENSE des contrôles de validation)

Retourne:
 - true: - si le bouton annuler a été sélectionné (pas de validation)
         - si un bouton submit autre qu'annuler a été selectionné et que la
           saisie est correcte
- false: - si un bouton submit autre qu'annuler a été selectionné et que la
           saisie est incorrecte

Utilisation :
Cette fonction est systématiquement appelée lors de la soumission d'un 
formulaire (événement onSubmit du formulaire ou onClick du bouton déclenchant
la soumission du formulaire, y-compris le bouton annuler qui est détecté par
son attribut "name" qui doit avoir la valeur "SYSFWKCancel")
*/
function valideFormulaireGenerique(frm)
{
	// [LD 28/03/07]:  MODIFIE LA LOGIQUE DE CETTE FONCTION:
	// On teste dès le début si bCancel vaut true, car il est inutile
	// et gênant de forcer la validation client du formulaire si le bouton
	// "annuler" a été sélectionné !! (auparavant, le test était effectué
	// après la validation du formulaire, et on était donc obligé notamment
	// de renseigner les champs obligatoires même en cas d'annulation !!)

   var i ;
  var newdInputField;
	//	var frm = window.document.forms[0]; // Un seul formulaire sur la page

	if(bCancel == true) {
	  // [LD 12/10/06]: le code dynamique d'ajoute de champ caché ci-dessous ne fonctionne pas
	  //alert('add hidden SYSFWKCancel input field step 1 of 5');
	  //newdInputField = document.createElement('input');
	  //alert('add hidden SYSFWKCancel input field step 2 of 5');
	  //newdInputField.setAttribute('type','hidden');
	  //alert('add hidden SYSFWKCancel input field step 3 of 5');
	  //newdInputField.setAttribute('name','SYSFWKCancel');
	  //alert('add hidden SYSFWKCancel input field step 4 of 5');
	  /////////////frm.appendChild(newInputField);
	  //document.forms[0].appendChild(newInputField);
	  //alert('add hidden SYSFWKCancel input field step 5 of 5');
	  //
	  // Autre essai (mais fait disparaître le bouton lors du clic,
	  // et suppose un seul formulaire par écran):
	  //document.getElementsByName("SYSFWKCancel")[0].type = "hidden";
	  // => Remplacé ce code par la bidouille suivante:
	  // (ATTENTION, marche uniquement avec un formulaire méthode POST,
	  //  ne fonctionne pas avec la méthode GET !!!)
	  frm.action = frm.action + "?SYSFWKCancel"
	  //alert('valideFormulaireGenerique() - traitement cancel OK');
	  // [/LD]
	  return true;
	}
	else {
		reInitFormColor();
	  colorForm(frm, "INPUTOBLIGATOIRE");
		for (i = 0; i< frm.length; i++)
		{
			targetElement =  frm[i] ;
			if (!valideChamp(targetElement))
			{
	    	targetElement.className = targetElement.className + " INPUTPROBLEME";
	      return false;
			}
		}
		if(debug) {
			alert('Contrôles de saisie OK --> Soumission formulaire' + frm.name);
		}
		return true;
	}
}


/******************************************************************************/
function setCancel(bValue)
{
  //alert('annulation du formulaire');
  bCancel = bValue;
}

/******************************************************************************/
function annuler()
/*
Rôle : Cette fonction doit etre appelee lorsque l'utilisateur annule la saisie
sur un formulaire.
Si une modification a eu lieu sur le formulaire, une demande de confirmation est affichee.

retour : true si annulation confirmée. false sinon.
*/
{
	ok_annuler = true;
	if (bEstModifie)
	{
		ok_annuler = confirm('Des modifications ont été effectuées ! Voulez vous les annuler ?');
	}
	// [LD 28/03/07]: cette fonction n'est en fait pas appelée par DEI-Layout
	// DEI-Layout utilise le flag "bCancel", et le positionne directement sans appeler
	// une fonction Javascript
	// Cependant, au cas où on décide d'appeler cette fonction en cas d'annulation, 
	// attention à la différence de sémantique: 
	// - bCancel est testé dans valideFormulaireGénérique pour dispenser de la validation
	//   du formulaire en cas d'appui sur Annuler
	//  - le flag ok_annuler utilisé ici doit être utilisé comme valeur de retour à un
	//    événement onSubmit. Ainsi, si ok_annuler renvoie "false", l'annulation elle-même
	//    sera annulée et le formulaire ne sera pas posté.
	return 	ok_annuler;
}


/******************************************************************************/
function valideChamp(targetElement)
/*
Role : Validation de la saisie effectuée sur un champ

parametres :
- targetElement  : Element a controler

Retour true si controles OK, false sinon
*/
{
	var msgErreur = '';

	// Lecture des attributs propres au framework
	// WAFObligatoire : Le champ est obligatoire si WADObligatoire="true"
	// WAFDescription : Description associee a l'element
	// WAFType 		  : Type de l'element saisie (VARCHAR, FLOAT, INTEGER, DATE)
	WAFObligatoire = targetElement.getAttribute("WAFObligatoire");
	WAFDescription = targetElement.getAttribute("WAFDescription");
	WAFType = targetElement.getAttribute("WAFType");

	// Controle que les champs obligatoires du formulaire sont renseignes
	if ( estControleVide(targetElement) )
	{
		if ( WAFObligatoire == "true" )
		{
			msgErreur = WAFDescription  + ' est obligatoire.\n' ;
			alert(msgErreur);
			focusElement(targetElement);
			return (false);
		}
	}
	else
	{
		if (WAFType == "INTEGER" )
		{
			// Controle que les champs de type ENTIER sont au bon format
			if (!EstControleEntier(targetElement))
			{
				msgErreur = WAFDescription  + ' doit ?tre de type entier.\n' ;
				alert(msgErreur);
				focusElement(targetElement);
				return (false);
			}
		}
		if (WAFType == "FLOAT" )
		{
			// Controle que les champs de type FLOAT ( r?els, montant ) sont au bon format
			if (!EstControleDecimal(targetElement))
			{
				msgErreur = WAFDescription  + ' doit ?tre de type d?cimal.\n' ;

				alert(msgErreur);
				focusElement(targetElement);
					return (false);
			}
			else
			{
				// WAFNbDecimal : Nb de decimal associe a un element de type FLOAT WAFType = FLOAT
				WAFNbDecimal = targetElement.getAttribute("WAFNbDecimal");
				if (WAFNbDecimal == null)
				{
				WAFNbDecimal = 2;
				}
				// Formatage du decimal avec le nombre de decimal demandes
				targetElement.value = formatDecimal(targetElement.value, WAFNbDecimal, "");
			}
		}
		if (WAFType == "DATE" )
		{
		// [LD 13/03/06 MODIFIE]: d?sactiv? la validation des dates,
		// car leur format de saisie n'est pas FORCEMENT dd/mm/yyyy !!!!
		/**
			if (!isValidDate(targetElement) )
			{
			msgErreur = WAFDescription + ' n\' est pas une date avec un format valide : jj/mm/aaaa.';
			alert(msgErreur);
			focusElement(targetElement);
			return false;
			}
		**/
		return true;
		// [/LD]
		}

		if ( (WAFType == "FLOAT" ) || (WAFType == "INTEGER" ) )
		{
			//WAFBorneInf : Borne inferieure des elements de type INTEGER et FLOAT
			//WAFBorneSup : Borne superieure des elements de type INTEGER et FLOAT
			// Controle que les ?ventuelles bornes sup?rieures et inf?rieures sont respect?es
			WAFBorneInf = targetElement.getAttribute("WAFBorneInf");
			WAFBorneSup = targetElement.getAttribute("WAFBorneSup");
			if ( !estValeurVide(WAFBorneInf) || !estValeurVide(WAFBorneSup) )
			{
				if ( !estValeurVide(WAFBorneInf) && !estValeurVide(WAFBorneSup) )
				// Les deux bornes Inf?rieures et Sup?rieures sont renseign?es
				{
					if (!controlBorne(targetElement, WAFBorneInf, WAFBorneSup))
					{
						msgErreur = WAFDescription + ' doit ?tre compris entre ' + WAFBorneInf + ' et ' + WAFBorneSup + '.\n';
						alert(msgErreur);
						focusElement(targetElement);
						return (false);
					}
				}
				else if ( !estValeurVide(WAFBorneInf)  )
				{
					// Seule la borne Inf?rieure est renseignee
					if (!controlBorneInf(targetElement, WAFBorneInf))
					{
						msgErreur = WAFDescription + ' doit ?tre sup?rieur ou ?gal ? ' + WAFBorneInf;
						alert(msgErreur);
						focusElement(targetElement);
						return (false);
					}
				}
				else
				{
					// Seule la borne Sup?rieure est renseignee
					if (!controlBorneSup(targetElement, WAFBorneSup))
					{
						msgErreur = WAFDescription + ' doit ?tre inf?rieur ou ?gal ? ' + WAFBorneSup;
						alert(msgErreur);
						focusElement(targetElement);
						return (false);
					}
				}
			}
		}
	}
	return (true);
}

/******************************************************************************/
function controlBorneInf(targetElement, BorneInf)
/*
Role : Verifie qu'un element est superieur ou egal a une valeur
parametres :
- targetElement  : element a controler
- BorneInf       : Borne inferieure

Retour : true si targetElement superieur ou egal a la borne inferieure autorisee
*/
{
var val=parseFloat(targetElement.value);
return (val >= BorneInf);

}

/******************************************************************************/
function controlBorneSup(targetElement, BorneSup)
/*
Role :  Verifie qu'un element est inferieur a une valeur
parametres :
- targetElement  : element a controler
- BorneSup       : Borne superieure
Retour : true si targetElement inferieur ou egal a la borne superieure autorisee
*/
{

var val=parseFloat(targetElement.value);

if (debug) alert('pb Borne Sup si  ' + val + ' <= a ' + BorneSup);
return (val <= BorneSup);
}

/******************************************************************************/
function controlBorne(targetElement, BorneInf, BorneSup)
/*
 Verifie qu'un element est compris entre les bornes inferieure et superieure autorisees
parametres :
- targetElement  : element a controler
- BorneInf       : Borne inferieure
- BorneSup       : Borne superieure
 Retour : true si targetElement compris entre borne inf et borne sup
*/
{
var val=parseFloat(targetElement.value);
return (val >= BorneInf && val <= BorneSup);
}

/******************************************************************************/
function estControleVide(targetElement)
/*
Role : Verifie qu'un element n'est pas vide (le controle doit implementer la methode value)
parametres :
- targetElement  : element a controler
Retour : true si champ vide, false sinon
*/
{
	return ( estValeurVide(targetElement.value) );
}

/******************************************************************************/
function estValeurVide(valeur)
/*
Role : Verifie qu'une valeur n'est pas vide
parametres :
- valeur  : valeur a controler
Retour : true si valeur vide, false sinon
*/
{
	if (valeur == null)
	{ return (true); }
	else
	{ return ((valeur).replace(/[ ]/g, '') == '');	}
}

/******************************************************************************/
function EstControleEntier(targetElement)
/*
Role : Verifie qu'un element a un contenu de type entier
(le controle doit implementer la methode value)
parametres :
- targetElement  : element a controler
Retour : true si champ vide ou entier, false sinon
*/
{
	return (((targetElement.value).replace(/[0-9-]*/, '') == '') || estControleVide(targetElement) );
}

/******************************************************************************/
function EstControleDecimal(targetElement)
/*
Role : Verifie qu'un controle a un contenu d?cimal
(le controle doit implementer la methode value)
parametres :
- targetElement  : element a controler
Retour : true si champ vide ou numeric, false sinon
*/
{
	if ( carCount(targetElement.value, ',') + carCount(targetElement.value, '.') > 1)
	// On verifie que le decimal contient au plus une virgule et/ou un point
	{
		return (false);
	}
	else
	{
	// On verifie que le decimal ne contient pas d'autres caracteres
	// que des numeriques, une virgule ou un point
		return (((targetElement.value).replace(/[0-9.,-]*/, '') == '') || estControleVide(targetElement) );
	}
}

/******************************************************************************/
function focusElement( targetElement)
/*
Role : Donne le focus a l'element passe en parametre s'il est accessible
parametres :
- targetElement  : element a controler
*/
{
	if (!targetElement.disabled )
	{
		if (targetElement.type != "hidden"){
				targetElement.focus();
		}
		if (targetElement.type == "text")
		{
			targetElement.focus();
		}
	}
}

/******************************************************************************/
function selectElement( targetElement)
/*
Role : Donne le select a l'element passe en parametre s'il est accessible
parametres :
- targetElement  : element a controler
*/
{
	if (!targetElement.disabled )
	{
		if (targetElement.type != "hidden"){
				targetElement.select();
		}
		if (targetElement.type == "text")
		{
			targetElement.select();
		}
	}
}

/******************************************************************************/
function formatDecimal(valeur, nbdecimal,separateur_millier)
/*
Role : formate une valeur avec N decimales apr?s la virgule et un separateur de millier
parametres :
- valeur  : valeur a formater
- decimal : nombre de decimale
- separateur_millier : separateur de milliers
*/
// formate un chiffre avec decimal chiffres apr?s la virgule
{
	valeur = valeur.replace(/[ ]/g, '');
	valeur = valeur.replace(/[,]/, '.');

	var deci=Math.round( Math.pow(10,nbdecimal)*(Math.abs(valeur)-Math.floor(Math.abs(valeur)))) ;
	var val=Math.floor(Math.abs(valeur));

	if ((nbdecimal==0)||(deci==Math.pow(10,nbdecimal))) {val=Math.floor(Math.abs(valeur)); deci=0;}
	var val_format=val+"";
	var nb=val_format.length;
	for (var i=1;i<4;i++) {
		if (val>=Math.pow(10,(3*i))) {
			val_format=val_format.substring(0,nb-(3*i))+separateur_millier+val_format.substring(nb-(3*i));
		}
	}
	if (nbdecimal>0) {
		var decim="";
		for (var j=0;j<(nbdecimal-deci.toString().length);j++) {decim+="0";}
		deci=decim+deci.toString();
		val_format=val_format+"."+deci;
	}
	if (parseFloat(valeur)<0) {val_format="-"+val_format;}

	val_format = val_format.replace(/[,]/, '.');

	return val_format;
}

/******************************************************************************/
function carCount( src, car )
/*
Role  : Compte le nombre de caractere de type car contenu dans une chaine src
parametres :
- src  : chaine sur laquelle porte la recherche
- car  : caractere a rechercher dans la chaine
Retour : Nombre de caractere de type car contenu dans une chaine src
*/
{
	var compt = src.split(car).length - 1;
	{ return compt;}
}

/******************************************************************************/
function isChampDateInferieure ( champ_date_deb, champ_date_fin) {
/*
Role : Controle que la date contenu dans un element de type date debut
est inferieur ou egale a la date contenu dans un element de type date fin
parametres :
champ_date_deb : Element contenant la date de debut
champ_date_fin : Element contenant la date de fin

Retour : true si champ_date_deb est <= champ ? date_fin
false sinon
*/
	ok = isValDateInferieure(champ_date_deb.value, champ_date_fin.value)

	if (!ok)
	{
		WAFDescriptionDateDeb = champ_date_deb.getAttribute("WAFDescription");
		WAFDescriptionDateFin = champ_date_fin.getAttribute("WAFDescription");
		alert( WAFDescriptionDateFin + ' doit ?tre sup?rieure ou ?gale ? ' + WAFDescriptionDateDeb);
	}

	return ok;
}

/******************************************************************************/
function isValDateInferieure ( date_deb, date_fin) {
/**
Role : Controle qu'une date debut est inferieure ou egale a une date fin
parametres :
date_deb : Valeur de la date de debut
date_fin : Valeur de la date de fin

Retour : true si date_deb est <= champ ? date_fin
false sinon
*/

	ok = true;

	var dd_deb = date_deb.substr(0,2);
	var mm_deb = date_deb.substr(3,2);
	var yy_deb = date_deb.substr(6,4);
	var dd_fin = date_fin.substr(0,2);
	var mm_fin = date_fin.substr(3,2);
	var yy_fin = date_fin.substr(6,4);
	if (yy_deb > yy_fin) ok = false;


	if (ok && (yy_deb == yy_fin) && (mm_deb > mm_fin)) ok = false;

	if (ok && (yy_deb == yy_fin) && (mm_deb == mm_fin) && (dd_deb > dd_fin)) ok = false;

	return ok;
}

/******************************************************************************/
function isValidDate(targetElement)
/*
Role : Controle que la date saisie est correcte
Format acceptes :  JJ/MM/AA   ou JJ/MM/AAAA
On accepte 1 ou 2 caracteres pour JJ ( si 1, complete par 0 )
On accepte 1 ou 2 caracteres pour MM ( si 1, complete par 0 )
On accepte 2 ou 4 caracteres pour AA ( si 2, complete par le siecle courant )

Retour : true si le contenu de targetElement est de type date, false sinon
false sinon
*/

{
	var dateStr = targetElement.value;
	var datePat = /^(\d{1,2})(\/)(\d{1,2})\2(\d{2}|\d{4})$/;

	var matchArray = dateStr.match(datePat); // est ce que le format est correct ?
	if (matchArray == null)
	{
		if (debug)  alert("Format de date invalide.")
		return false;
	}
	day = matchArray[1];
	month = matchArray[3];
	year = matchArray[4];

	if (day.length == 1)
	{
	day = '0'+ day;
	}
	if (month.length == 1)
	{
	month = '0'+ month;
	}
        /* Ici on decide de bloquer la date sur 4 digits */
        /* Si on veut pouvoir autoriser les annees sur 2 ou 4 digits,
         il faudra enlever la condition ci-dessous et utiliser la 2ieme 
         condition qui complete l'annee par l'annee courante */
/*	if (year.length == 2)
	{
		return false;
	}
*/
	if (year.length == 2)
	{
		y = new Date();
		year = y.getFullYear().toString().substring(0,2) + year;
	}

	if (month < 1 || month > 12)
	{
		if (debug)  alert("Le Mois doit être compris entre 1 et 12.");
		return false;
	}
	if (day < 1 || day > 31)
	{
		if (debug)  alert("Le Jour doit être compris entre 1 et 31.");
		return false;
	}
	if ((month==4 || month==6 || month==9 || month==11) && day==31)
	{
		if (debug)  alert("Le mois "+month+" n\'a pas 31 jours.")
		return false;
	}
	if (month == 2) { // Controle pour le mois de fevrier ( Annee bissextile )
		var anneeBissextile = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29 || (day==29 && !anneeBissextile))
		{
			if (debug)  alert("Fevrier " + year + " n\'a pas " + day + " jours.");
			return false;
		}
	}
	targetElement.value=day+'/'+month+'/'+year;

	return true;  // La date saisie est correcte
}

/******************************************************************************/
function changeChamp(targetElement)
/*
R?le : Permet de valoriser la variable bEstModifie a true
indiquant qu'une mise a jour a ete effectuee sur le formulaire en cours.
parametre :
- targetElement  : element sur lequel intervient la modification
*/
{
	bEstModifie = true;
}


/******************************************************************************/
function colorLigne (theRow, theAction)
/*
Fonction graphique qui permet de surligner d'une certaine couleur
une ligne ("theRow") d'un tableau (balise <tr> .... </tr>).
Les actions sont : 'over','out' et 'click' pour un onmouseover,onmouseout et onClick d'une
balise <tr>.
*/
{
    var theCells = null;
    // couleur par defaut, background du body : #FFFFFF
    var theDefaultColor = '#FFFFFF' ;
    // couleur de roll over : #DEF6FF
    var thePointerColor = '#DEF6FF' ;
    // couleur de click quand les projets le demande : #FFCC99
    var theMarkColor = '#FFCC99' ;

    // 1. Pointer and mark feature are disabled or the browser can't get the
    //    row -> exits
    if ((thePointerColor == '' && theMarkColor == '')
        || typeof(theRow.style) == 'undefined') {
        return false;
    }

    // 2. Gets the current row and exits if the browser can't get it
    if (typeof(document.getElementsByTagName) != 'undefined') {
        theCells = theRow.getElementsByTagName('td');
    }
    else if (typeof(theRow.cells) != 'undefined') {
        theCells = theRow.cells;
    }
    else {
        return false;
    }

    // 3. Gets the current color...
    var rowCellsCnt  = theCells.length;
    var domDetect    = null;
    var currentColor = null;
    var newColor     = null;
    // 3.1 ... with DOM compatible browsers except Opera that does not return
    //         valid values with "getAttribute"
    if (typeof(window.opera) == 'undefined'
        && typeof(theCells[0].getAttribute) != 'undefined') {
        currentColor = theCells[0].getAttribute('bgcolor');
        domDetect    = true;
    }
    // 3.2 ... with other browsers
    else {
        currentColor = theCells[0].style.backgroundColor;
        domDetect    = false;
    } // end 3

    // 4. Defines the new color
    // 4.1 Current color is the default one
    if (currentColor == ''
        || currentColor.toLowerCase() == theDefaultColor.toLowerCase()) {
        if (theAction == 'over' && thePointerColor != '') {
            newColor = thePointerColor;
        }
        else if (theAction == 'click' && theMarkColor != '') {
            newColor = theMarkColor;
        }
    }
    // 4.1.2 Current color is the pointer one
    else if (currentColor.toLowerCase() == thePointerColor.toLowerCase()) {
        if (theAction == 'out') {
            newColor = theDefaultColor;
        }
        else if (theAction == 'click' && theMarkColor != '') {
            newColor = theMarkColor;
        }
    }
    // 4.1.3 Current color is the marker one
    else if (currentColor.toLowerCase() == theMarkColor.toLowerCase()) {
        if (theAction == 'click') {
            newColor = (thePointerColor != '')
                     ? thePointerColor
                     : theDefaultColor;
        }
    } // end 4

    // 5. Sets the new color...
    if (newColor) {
        var c = null;
        // 5.1 ... with DOM compatible browsers except Opera
        if (domDetect) {
            for (c = 0; c < rowCellsCnt; c++) {
                theCells[c].setAttribute('bgcolor', newColor, 0);
            } // end for
        }
        // 5.2 ... with other browsers
        else {
            for (c = 0; c < rowCellsCnt; c++) {
                theCells[c].style.backgroundColor = newColor;
            }
        }
    } // end 5

    return true;
}
/******************************************************************************/
function valideFormulaire(frm)
{
      if ( !valideFormulaireGenerique(frm) )
      {
	      return false;
      }
      else
      {
	      return true;
      }

}
//Valide et precise l'action
function valideFormulaire(frm,action){
	if ( !valideFormulaireGenerique(frm) ){
            return false;
    }else{
            return fcSubmit(frm,action);
    }
}
//Valide,precise l'action et submit
function valideSubmitFormulaire(frm,action){
	if ( !valideFormulaireGenerique(frm) ){
            return false;
    }else{
		if(!!action){
	    	frm.action=action;
	     }
	     frm.submit();
    }
}
//Precise l'action
function fcSubmit(frm,action,target){
	if(!!action){
    	frm.action=action;
     }
	if(!!target){
    	frm.target=target;
     }
     return true;		
}

/************ [LD 09/03/06]: commented out and tried new version below
function clickCheckBox(checkElement){
	if(checkElement.checked){
		checkElement.nextSibling.value=checkElement.value;
	}
	else {
		if(checkElement.value == "1"){
			checkElement.nextSibling.value="0";
		}
		else if(checkElement.value == "0") {
			checkElement.nextSibling.value="1";
		}
		// [LD 09/03/06: Added]
		else if(checkElement.value == "on") {
			checkElement.nextSibling.value="off";
		}
		else if(checkElement.value == "off") {
			checkElement.nextSibling.value="on";
		}
		// [/LD]
		else if(checkElement.value) {
			checkElement.nextSibling.value=checkElement.value;
		}
		else {
			checkElement.nextSibling.value= !checkElement.value;
		}						
	}
}
*****************/

// [LD 09/03/06]: new test version
function clickCheckBox(checkElement) {
	if(checkElement.checked) {
		checkElement.nextSibling.value = "on";
	}
	else {
	 checkElement.nextSibling.value = "off";
	}
}
// [/LD]










/**
 * struts-layout core javascript
 *
 * All rights reserved.
 */
 
// Dynamic result update
// for mathDataCollection on CollectionInputTab

function roundNum (number,X){
	return Math.round(number*Math.pow(10,X))/Math.pow(10,X);
}

function mathDataUpdate (operation, resultId, tableId, columnId, rowsNumber){
	var result = document.getElementById(resultId);
	newResult = 0.0;
	if  (operation=="sum"){
		for (i=0; i<rowsNumber; i++) {
			var cel = document.getElementById("mathData_t" + tableId + "l" + i +"c" + columnId);
			if (isNaN(parseFloat(cel.value))){
				alert("\"" + cel.value + "\"" + " is not a number");
				return false;
			}		
			newResult = parseFloat(newResult) + parseFloat(cel.value);
		}
	} else if  (operation=="max"){
		for (i=0; i<rowsNumber; i++) {
			var cel = document.getElementById("mathData_t" + tableId + "l" + i +"c" + columnId);
			if (isNaN(parseFloat(cel.value))){
				alert("\"" + cel.value + "\"" + " is not a number");
				return false;
			}
			if (i==0){
				newResult = cel.value;
			}
			if (newResult < cel.value){
				newResult = cel.value;
			}		
		}
	} else if  (operation=="min"){
		for (i=0; i<rowsNumber; i++) {
			var cel = document.getElementById("mathData_t" + tableId + "l" + i +"c" + columnId);
			if (isNaN(parseFloat(cel.value))){
				alert("\"" + cel.value + "\"" + " is not a number");
				return false;
			}
			if (i==0){
				newResult = cel.value;
			}
			if (newResult > cel.value){
				newResult = cel.value;
			}		
		}
	} else {
		alert("Sorry !!. \n\"" + operation + "\" operation is not supported yet. ");
	}
	result.innerHTML = roundNum(newResult,2);
}

// type checking functions

function checkValue(field, property, type, required) {
	if (document.images[property + "required"]!=null) {
		if (field.value!="") {		
			document.images[property + "required"].src= imgsrc + "clearpixel.gif";
			if (type=="NUMBER" && !isNumber(field.value)) document.images[property + "required"].src= imgsrc + "ast.gif";
			if (type=="DATE" && !isDate(field.value)) document.images[property + "required"].src = imgsrc + "ast.gif";
			if (type=="EMAIL" && !isEmail(field.value)) document.images[property + "required"].src= imgsrc + "ast.gif";		
		} else {	
			if (required) document.images[property + "required"].src= imgsrc + "ast.gif";
		}
	}
}

// Return true if value is an e-mail address
function isEmail(value) {
	invalidChars = " /:,;";
	if (value=="") return false;
	
	for (i=0; i<invalidChars.length;i++) {
	   badChar = invalidChars.charAt(i);
	   if (value.indexOf(badChar,0) != -1) return false;
	}
	
	atPos = value.indexOf("@", 1);
	if (atPos == -1) return false;
	if (value.indexOf("@", atPos + 1) != -1) return false;
	
	periodPos = value.indexOf(".", atPos);
	if (periodPos == -1) return false;
	
	if (periodPos+3 > value.length) return false;

	return true;
}



// Return true if value is a number
function isNumber(value) {
	if (value=="") return false;

	var d = parseInt(value);
	if (!isNaN(d)) return true; else return false;		

}

// return true if value is a date
// ie in the format XX/YY/ZZ where XX YY and ZZ are numbers
function isDate(value) {
	if (value=="") return false;
	
	var pos = value.indexOf("/");
	if (pos == -1) return false;
	var d = parseInt(value.substring(0,pos));
	value = value.substring(pos+1, 999);
	pos = value.indexOf("/");
	if (pos==-1) return false;
	var m = parseInt(value.substring(0,pos));
	value = value.substring(pos+1, 999);
	var y = parseInt(value);	
	if (isNaN(d)) return false;	
	if (isNaN(m)) return false;	
	if (isNaN(y)) return false;	
	
	var type=navigator.appName;
	if (type=="Netscape") var lang = navigator.language;
	else var lang = navigator.userLanguage;
	lang = lang.substr(0,2);

	if (lang == "fr") var date = new Date(y, m-1, d);
	else var date = new Date(d, m-1, y);
	if (isNaN(date)) return false;	
	return true;
 }

// menu functions

function initMenu(menu) {
	if (getMenuCookie(menu)=="hide") {
		document.getElementById(menu).style.display="none";
	} else {
		document.getElementById(menu).style.display="";
	}
}

function changeMenu(menu) {
if (document.getElementById(menu).style.display=="none") {
	document.getElementById(menu).style.display="";
	element = document.getElementById(menu+"b");
	if (element != null) {
		document.getElementById(element).style.display="none";
	}
	setMenuCookie(menu,"show");
} else {
	document.getElementById(menu).style.display="none";
	element = document.getElementById(menu+"b");
	if (element != null) {	
		var width = document.getElementById(menu).offsetWidth;	
		if (navigator.vendor == ("Netscape6") || navigator.product == ("Gecko"))
			document.getElementById(menu+"b").style.width = width;	
		else 
			document.getElementById(menu+"b").width = width;
		document.getElementById(menu+"b").style.display="";
	}
	setMenuCookie(menu,"hide");
}
return false;
}

function changeDisplayState(elementId, imageId, visibleImageSrc, hiddenImageSrc) {
	var display;
	if (document.getElementById(elementId).style.display=="none") {
		document.getElementById(elementId).style.display="";
		display = true;
	} else {
		document.getElementById(elementId).style.display="none";
		display = false;
	}
	if (imageId) {
		document.getElementById(imageId).src = imgsrc + (display ? visibleImageSrc : hiddenImageSrc);
	}
}

function setMenuCookie(name, state) {	
	if (name.indexOf("treeView")!=-1) {
		if (state=="show") {
			var cookie = getMenuCookie("treeView", "");
			if (cookie=="???") cookie = "_";
			cookie = cookie + name + "_";
			document.cookie = "treeView=" + escape(cookie);

		} else {
			var cookie = getMenuCookie("treeView", "");
			var begin = cookie.indexOf("_" + name + "_");
			if (cookie.length > begin + name.length + 2) {
				cookie = cookie.substring(0, begin+1) + cookie.substring(begin + 2 + name.length);
			} else {
				cookie = cookie.substring(0, begin+1);
			}
			document.cookie = "treeView=" + escape(cookie);
		}
		return;
	} 
	if (name.indexOf("selectedTab")!=-1) {
		document.cookie = "selectedTab=" + escape(state) + getCookieContextPath();
	} else {
		var cookie = name + "STRUTSMENU=" + escape(state);
		document.cookie = cookie;	
	}
}

function getCookieContextPath() {
	if (window.contextPath) {
		return "; path=" + window.contextPath;
	} else {
		return "";
	}
}


function setTabCookie(name, value) {
	var cookie = getMenuCookie("selectedTab", "");	
	var start;
	var end;
	if (cookie=="undefined") cookie = "";
	if (cookie==null) cookie = "";
	if (cookie=="???") cookie = "";	
	start = cookie.indexOf(name + "=");
	if (start==-1) {
		cookie = cookie + name + "=" + value + ";"
	} else {
		end = cookie.substring(start).indexOf(";");
		cookie = cookie.substring(0, start) + name + "=" + value + cookie.substring(start+end);
	}
	setMenuCookie("selectedTab", cookie);
}

function getMenuCookie(name, suffix) {
	if (suffix==null) {
		suffix = "STRUTSMENU";
	}
	var prefix = name + suffix + "=";
	var cookieStartIndex = document.cookie.indexOf(prefix);
	if (cookieStartIndex == -1) return "???";
	var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	if (cookieEndIndex == -1) cookieEndIndex = document.cookie.length;
	return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}

// sort functions
function arrayCompare(e1,e2) {
	return e1[0] < e2[0] ? -1 : (e1[0] == e2[0] ? 0 : 1);

}

var tables = new Array();
function arraySort(tableName, column, lineNumber, columnNumber) {
	var aTable = tables[tableName];
	var arrayToSort;
	var array;
	var reverse = 0;
	if (aTable) {
		array = aTable[0];
		arrayToSort = new Array(lineNumber);
		for (i=0;i<lineNumber;i++) {
			arrayToSort[i] = new Array(2);
			arrayToSort[i][0] = array[i][column];
			arrayToSort[i][1] = i;				
		}
		reverse = 1 - aTable[1];
		aTable[1] = reverse;
	} else {
		array = new Array(lineNumber);
		arrayToSort = new Array(lineNumber);
		for (i=0;i<lineNumber;i++) {	
			array[i] = new Array(columnNumber);
			for (j=0;j<columnNumber;j++) {
				obj = document.getElementById("t" + tableName + "l" + (i+1) +"c" + j);		
				array[i][j] = obj.innerHTML;
			}
			array[i][columnNumber] = obj.parentNode.parentNode.onmouseover;
			array[i][columnNumber+1] = obj.parentNode.parentNode.onmouseout;			
			
			arrayToSort[i] = new Array(2);
			arrayToSort[i][0] = array[i][column];
			arrayToSort[i][1] = i;		
	
			aTable = new Array(2);
			aTable[0] = array;
			aTable[1] = 0;
			tables[tableName] = aTable;
		}
	}

	arrayToSort.sort(arrayCompare);
	if (reverse) {
		arrayToSort.reverse();
	}

	for (i=0;i<lineNumber;i++) {
		goodLine = arrayToSort[i][1];
		for (j=0;j<columnNumber;j++) {
			document.getElementById("t" + tableName + "l" + (i+1) +"c" + j).innerHTML = array[goodLine][j];
		}
		document.getElementById("t" + tableName + "l" + (i+1) +"c" + 0).parentNode.parentNode.onmouseover = array[goodLine][columnNumber];
		document.getElementById("t" + tableName + "l" + (i+1) +"c" + 0).parentNode.parentNode.onmouseout = array[goodLine][columnNumber+1];
	}
}

// calendar functions

var calformname;
var calformelement;
var calpattern;
var calweekstart;

/**
 * Static code included one time in the page.
 *
 * a {text-decoration: none; color: #000000;}");
 * TD.CALENDRIER {background-color: #C2C2C2; font-weight: bold; text-align: center; font-size: 10px; }");
 *
 * bgColor => #000000, #C9252C, 
 */
function printCalendar(day1, day2, day3, day4, day5, day6, day7, first, month1, month2, month3, month4, month5, month6, month7, month8, month9, month10, month11, month12, day, month, year) {
	document.write('<div id="caltitre" style="z-index:10;">');	
	document.write('<table cellpadding="0" cellspacing="0" border="0" width="267">');
//	document.write('<form>');
	document.write('<tr><td colspan="15" class="CALENDARBORDER"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td></tr>');
	document.write('<tr>');
	document.write('	<td class="CALENDARBORDER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=20></td>');
	document.write('	<td class="CALENDARTITLE" colspan="3" align="right"><img src="' + imgsrc + 'previous.gif" onclick="cal_before(' + day + ');"></td>');
	document.write('	<td colspan=7 align="center" class="CALENDARTITLE" nowrap>');
	
	// month
	document.write('<select id="calmois" name="calmois" onchange="cal_chg(' + day + ');"><option value=0>...</option>');	
	
	// use the good day for week start.
	// store the day the week start for later.
	calweekstart = first;	
	// compute an array of the days, starting from Sunday.
	caldays = new Array(7);
	caldays[0] = day1;
	caldays[1] = day2;
	caldays[2] = day3;
	caldays[3] = day4;
	caldays[4] = day5;
	caldays[5] = day6;
	caldays[6] = day7;
	// compute an array of the days, starting at the good day.
	computedcaldays = new Array(7);
	for (i=0; i<7; i++) {		
		computedcaldays[(i+1-calweekstart+7)%7] = caldays[i];
	}
			
	for(i=1;i<=12;i++) {
		var str='<option value=' + i + '>';
		monthIndex = i-1;
		switch (monthIndex) {
			case 0: str += month1; break;
			case 1: str += month2; break;
			case 2: str += month3; break;
			case 3: str += month4; break;
			case 4: str += month5; break;
			case 5: str += month6; break;
			case 6: str += month7; break;
			case 7: str += month8; break;
			case 8: str += month9; break;
			case 9: str += month10; break;
			case 10: str += month11; break;
			case 11: str += month12; break;
		}
		document.write(str);
	}	

	document.write('</select>');
	
	// year
	document.write('<select id="calyear" name="calyear" onchange="cal_chg('+ day + ');">');	
	document.write("</select>");
	
	document.write('	</td>');
	document.write('	<td class="CALENDARTITLE" align="left" colspan="3"><img src="' + imgsrc + 'next.gif" onclick="cal_after(' + day + ');">&nbsp;&nbsp;<img src="' + imgsrc + 'close.gif" onclick="hideCalendar()"></td>');
	document.write('	<td class="CALENDARBORDER" width=1><img src="' + imgsrc + 'shim.gif" width="1" height="1"></td>');
	document.write('</tr>');
	document.write('<tr><td colspan=15 class="CALENDARBORDER"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td></tr>');
	document.write('<tr>');
	document.write('	<td class="CALENDARBORDER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[0] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[1] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[2] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[3] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[4] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[5] + '</td>');
	document.write('	<td class="CALENDRIER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('	<td class="CALENDRIER" width="38">' + computedcaldays[6] + '</td>');
	document.write('	<td class="CALENDARBORDER" width="1"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>');
	document.write('</tr>');
	document.write('<tr><td colspan=15 class="CALENDARBORDER"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td></tr>');
//	document.write('</form>');
	document.write('</table>');
	document.write('</div>');
//	document.write('<div id="caljour" style="position:absolute; left:0px; top:45px; width:253; height:130; z-index:10;"></div>');
	document.write('<div id="caljour" style="z-index:10;"></div>');	
}

/**
 * Show the calendar
 */
function showCalendar(year, month, day, pattern, formName, formProperty, event, startYear, endYear) {
	if (document.forms[formName].elements[formProperty].disabled) {
			return;
	}
	if (startYear!=null) {
		var calyear = document.getElementById("calyear");
		for (i = startYear; i <= endYear; i++) {			
			calyear.options[i - startYear] = new Option(i,i);
		}
		calyear.options.length = endYear - startYear + 1;
	}

	// Update the calendar.
	if (document.layers) {
		document.slcalcod.document.caltitre.document.forms[0].calmois.selectedIndex=month;
	} else if (document.all) {
		document.all.calmois.selectedIndex= month;
	} else {
		document.getElementById("calmois").selectedIndex=month;
	}
	if (document.forms[formName].elements[formProperty].stlayout) {
		var lc_day = document.forms[formName].elements[formProperty].stlayout.day;
		var lc_month = document.forms[formName].elements[formProperty].stlayout.month;
		var lc_year = parseInt(document.forms[formName].elements[formProperty].stlayout.year);
		cal_chg(lc_day, lc_month, lc_year);	
	} else {
		cal_chg(day, month, year);	
	}

	if(document.all) {
		// IE.
		var position = cal_place(event);
		document.all.slcalcod.style.left = position[0];
		document.all.slcalcod.style.top = position[1];
		document.all.slcalcod.style.visibility="visible";
	} else if(document.layers) {
		// Netspace 4
		document.slcalcod.left = e.pageX+10;
		document.slcalcod.top = e.pageY+10;
		document.slcalcod.visibility="visible";
	} else {
		// Mozilla
		var calendrier = document.getElementById("slcalcod");
		var position = cal_place(event);
		calendrier.style.left = position[0];
		calendrier.style.top = position[1];				
		calendrier.style.visibility="visible";
	}
	if (document.all) {
		hideElement("SELECT");
	}
	calformname = formName;
	calformelement = formProperty;
	calpattern = pattern;
}

/**
 * Compute the size of the window.
 */
function cal_window_size() {
	var myWidth = 0, myHeight = 0;
  	if( typeof( window.innerWidth ) == 'number' ) {
	    //Non-IE
	    myWidth = window.innerWidth;
	    myHeight = window.innerHeight;
  	} else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
	    //IE 6+ in 'standards compliant mode'
	    myWidth = document.documentElement.clientWidth;
	    myHeight = document.documentElement.clientHeight;
	} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
	    //IE 4 compatible
	    myWidth = document.body.clientWidth;
	    myHeight = document.body.clientHeight;
	}
	return [myWidth, myHeight];  
}

/**
 * Compute where the calendar popup should be placed
 */
function cal_place(event) {
	var calendrier = document.getElementById("slcalcod");
	var ofy=document.body.scrollTop;
	var ofx=document.body.scrollLeft;
	var size = cal_window_size();

	var endX = calendrier.clientWidth + event.clientX + ofx + 10;	
	var endY = calendrier.clientHeight + event.clientY + ofy + 10;
	
	var calX;
	var calY;

	if (endX>size[0]) {
		calX = event.clientX + ofx - 10 - calendrier.clientWidth;
	} else {
		calX = event.clientX + ofx + 10;
	}
	
	if (endY>size[1]) {
		calY = event.clientY + ofy - 10 - calendrier.clientHeight;
	} else {
		calY = event.clientY + ofy + 10;
	}
	
	return [calX, calY];
}

/**
 * Redraw the calendar for the current date and a selected month
 */
function cal_chg(day, month, year){
	var str='',j;	
	
	champMonth = document.getElementById("calmois");
	if (month==null) {		
		month = champMonth.options[champMonth.selectedIndex].value;
	} else {
		champMonth.selectedIndex = month;
	}
		
	
	champYear = document.getElementById("calyear");
	if (year==null) {		
		year = champYear.options[champYear.selectedIndex].value;
	} else {
		index = year - champYear.options[0].value;
		if (index >= 0 && index < champYear.options.length) {
			champYear.selectedIndex = index;
		} else {
			// the initial year is not in the calendar allowed years.
			year = champYear.options[0].value;
		}
	}
	
	
	if(month>0) {
	
		j=1;
		weekEnd1Pos = (1 - calweekstart + 7) % 7;
		weekEnd2Pos = (7 - calweekstart + 7) % 7;
				
		str+='<table cellpadding=0 cellspacing=0 border=0 width=267>\n';
		for(u=0;u<6;u++){
			str+='	<tr>\n';
			for(i=0;i<7;i++){
				ldt=new Date(year,month-1,j);				
				str+='		<td class="CALENDARBORDER" width=1><img src="' + imgsrc + 'shim.gif" width=1 height=20></td>\n';
				
				str+='		<td class="CALENDAR'; 
				if((ldt.getDay()+1-calweekstart+7)%7==i && ldt.getDate()==j && j==day /*&& newMonth==month && lc_annee==year*/) {
					str+='SELECTED'; 
				} else if(i==weekEnd1Pos || i==weekEnd2Pos) {
					str+='WEEKEND'; 
				} else {
					str+='WEEK'; 
				}
				str+='" width="38" align="center">';
				if ((ldt.getDay()+1-calweekstart+7)%7==i && ldt.getDate()==j) {
					str+='<a class="CALENDRIER" href="javascript://" class="CALENDRIER" onmousedown="dtemaj(\'' + j + '\',\'' + month + '\',\'' + year +'\');">'+j+'</a>'; 
					j++;
				} else {
					str+='&nbsp;';
				}
				str+='</td>\n';
			}
			str+='		<td class="CALENDARBORDER" width=1><img src="' + imgsrc + 'shim.gif" width=1 height=1></td>\n';
			str+='	</tr>\n';
			str+='	<tr><td colspan=15 class="CALENDARBORDER"><img src="' + imgsrc + 'shim.gif" width=1 height=1></td></tr>\n';
		}
		str+='</table>\n';
	
	}
	
	if(document.all) {
		document.all.caljour.innerHTML=str;
	}
	if(document.layers) {
		obj=document.calendrier.document.caljour; 
		obj.top=48; 
		obj.document.write(str); 
		obj.document.close();
	}
	if (!document.all && document.getElementById) {
		document.getElementById("caljour").innerHTML = str;
	}
}

/**
 * Display the previous month
 */
function cal_before(day, month, year) {
	var champMonth, champYear;
	champMonth = document.getElementById("calmois");
	champYear = document.getElementById("calyear");
			
	if (champMonth.selectedIndex>1) { 
		champMonth.selectedIndex--;
	} else if (champYear.selectedIndex>0) {
		champYear.selectedIndex--;
		champMonth.selectedIndex = champMonth.options.length - 1;
	}
	cal_chg(day, champMonth.options[champMonth.selectedIndex].value, champYear.options[champYear.selectedIndex].value);
}

/**
 * Display the next month
 */
function cal_after(day, month, year) {
	// get required objects
	var champMonth, champYear;
	champMonth = document.getElementById("calmois");
	champYear = document.getElementById("calyear");
	if (champMonth.selectedIndex < champMonth.options.length - 1) {
		champMonth.selectedIndex++;
	} else if (champYear.selectedIndex < champYear.options.length - 1) {
		champYear.selectedIndex++;	
		champMonth.selectedIndex = 1;
	}
	cal_chg(day, champMonth.options[champMonth.selectedIndex].value, champYear.options[champYear.selectedIndex].value);
}

/**
 * Update the date in the input field and hide the calendar.
 * PENDING: find a way to make the format customable.
 */
function dtemaj(jour, mois, annee){
	document.forms[calformname].elements[calformelement].value = formatDate(jour, mois, annee);
	document.forms[calformname].elements[calformelement].stlayout = new Object();
	document.forms[calformname].elements[calformelement].stlayout.day = jour;
	document.forms[calformname].elements[calformelement].stlayout.month = mois;
	document.forms[calformname].elements[calformelement].stlayout.year = annee;
	hideCalendar();
}

function formatDate(day, month, year) {
	var date = "";
	var pos = 0;
	var pattern;
	var previousPattern;
	var patternLength = 0;
	if (calpattern!=null && calpattern.length>0) {		
		previousPattern = calpattern.charAt(0);
		while (pos <= calpattern.length) {
			if (pos < calpattern.length) {
				pattern = calpattern.charAt(pos);
			}  else {
				pattern = "";
			}
			if (pattern != previousPattern) {			
				switch (previousPattern) {
					case 'y':
						date += padYear(year, patternLength);				
						break;
					case 'M':
						date += padNumber(month, patternLength);
						break;
					case 'd':
						date += padNumber(day, patternLength);
						break;
					case '\'':
						// PENDING
						break;
					default:
						date += previousPattern;
				}
				previousPattern = pattern;
				patternLength = 0;
			}
			patternLength++;
			pos++;
		}
	}
	return date;
}

function padYear(year, patternLength) {
	if (patternLength==2 && year.length==4) {
		return year.substring(2);
	} else {
		return year;
	}
}

function padNumber(number,length) {
    var str = '' + number;
    while (str.length < length)
        str = '0' + str;
    return str;
}

function hideCalendar() {
	if(document.all) {
		// IE.
		document.all.slcalcod.style.visibility="hidden";
		showElement("SELECT");
	} else if(document.layers) {
		// Netspace 4
		document.slcalcod.visibility="hidden";
	} else {
		// Mozilla
		var calendrier = document.getElementById("slcalcod");
		calendrier.style.visibility="hidden";
	}
}

/**
 * Fix IE bug
 */
function hideElement(elmID)
{
	if (!document.all) {
		return;
	}
	x = parseInt(document.all.slcalcod.style.left);
	y = parseInt(document.all.slcalcod.style.top);
	var node = event.srcElement;
    while(node.tagName != "DIV") {
     	node = node.parentNode;
    	if (node.tagName == 'HTML') break;
	}
    if(node.tagName == "DIV"){
     	x+= node.scrollLeft;
        y+=node.scrollTop;
    }
	//xxx = 253; // document.all.slcalcod.offsetWidth;	
	//yyy = 145; // document.all.slcalcod.offsetHeight;
	
	xxx = document.all.slcalcod.offsetWidth;
	yyy = document.all.slcalcod.offsetHeight;
		
	for (i = 0; i < document.all.tags(elmID).length; i++)
	{
		obj = document.all.tags(elmID)[i];
		if (! obj || ! obj.offsetParent || obj.id=="calmois" || obj.id=="calyear")
			continue;

		// Find the element's offsetTop and offsetLeft relative to the BODY tag.
		objLeft   = obj.offsetLeft;
		objTop    = obj.offsetTop;
		objParent = obj.offsetParent;
		if(obj.style.visibility != "hidden"){
		while (objParent.tagName.toUpperCase() != "BODY")
		{
			objLeft  += objParent.offsetLeft;
			objTop   += objParent.offsetTop;
			objParent = objParent.offsetParent;
		}
		}
		obj.statusVisibility = obj.style.visibility;
										
		// Adjust the element's offsetTop relative to the dropdown menu
		//objTop = objTop - y;
	
		if (x > (objLeft + obj.offsetWidth) || objLeft > (x + xxx))
			;
		else if (objTop > y + yyy)
			;
		else if (y > (objTop + obj.offsetHeight))
			;
		else
             if(obj.statusVisibility != "hidden"){
			obj.style.visibility = "hidden";
	}
}
}

/**
 * Fix IE bug
 */
function showElement(elmID)
{
	if (!document.all) {
		return;
	}
	for (i = 0; i < document.all.tags(elmID).length; i++)
	{
		obj = document.all.tags(elmID)[i];
		if (! obj || ! obj.offsetParent)
			continue;
			
		if(obj.statusVisibility != "hidden")
		obj.style.visibility = "";
	}
}

/**
 * Tabs code.
 *
 * @param tabVarName: name of the form variable that holds the id of the selected tab.
 */
function selectTab(tabGroupId, tabGroupSize, selectedTabId, enabledStyle, disabledStyle, errorStyle, tabKeyName, tabKeyValue) {
	// first unselect all tab in the tag groups.
	for (i=0;i<tabGroupSize;i++) {
		element = document.getElementById("tabs" + tabGroupId + "head" + i);
		if (element.classNameErrorStdLayout) {
			element.className = errorStyle;
			element.style.color = "";			
		} else if (element.className == enabledStyle) {
			element.className = disabledStyle;
			element.style.color = "";
		} else if (element.className == errorStyle) {
			// do nothing more
		}
		
		document.getElementById("tabs" + tabGroupId + "tab" + i).style.display = "none";
	}
	if (document.getElementById("tabs" + tabGroupId + "head" + selectedTabId).className==errorStyle) {
		document.getElementById("tabs" + tabGroupId + "head" + selectedTabId).classNameErrorStdLayout = new Object();
	}
	document.getElementById("tabs" + tabGroupId + "head" + selectedTabId).className = enabledStyle;
	document.getElementById("tabs" + tabGroupId + "head" + selectedTabId).style.cursor = "default";
	document.getElementById("tabs" + tabGroupId + "tab" + selectedTabId).style.display = "";
	
	// update a cookie holding the name of the selected tab.
	if (tabKeyName!=null) {
		setTabCookie(tabKeyName, tabKeyValue);
	}
}
function onTabHeaderOver(tabGroupId, selectedTabId, enabledStyle) {
	element = document.getElementById("tabs" + tabGroupId + "head" + selectedTabId);
	if (element.className == enabledStyle) {
		element.style.cursor = "default";
	} else {
		element.style.cursor = "hand";
	}
}

/**
 * Treeview code
 */
function loadTree(url, tree) {
	element = document.getElementById("treeView" + url);
	element.innerHTML = tree;	
	element.style.display = "";
	element = document.getElementById("treeViewNode" + url);
	element.href = "javascript://";
	setMenuCookie("treeView" + url, "show")	
}

function changeTree(tree, image1, image2) {
	if (!isTreeviewLocked(tree)) {
		var image = document.getElementById("treeViewImage" + tree);
		if (image.src.indexOf(image1)!=-1) {
			image.src = image2;
		} else {
			image.src = image1;
		}
	
		if (document.getElementById("treeView" + tree).innerHTML == "") {
			return true;
		} else {
			changeMenu("treeView" + tree);
			return false;
		}
	} else {
		return false;
	}
}

function changeTreeAndSubtrees(tree) {
	var image = document.getElementById("treeViewImage" + tree);
	
	var link = image.parentNode;
	if (link.href.indexOf("javascript://") == -1) {
		// il s'agit d'un lien vers le treeview.do
		// => les sous-menus n'ont pas ?t? charg?s dans la page HTML
		// => on ne peut pas proc?der au changement
		return false;
	}
	
	if (image.src.indexOf("Close")!=-1) {
		reg=new RegExp("Close", "g");
		image.src = image.src.replace(reg, "Open");
	} else {
		reg=new RegExp("Open", "g");
		image.src = image.src.replace(reg, "Close");
	}
	
	if (document.getElementById("treeView" + tree).innerHTML == "") {
		return true;
	} else {
		// change the menu itself
		menu = "treeView" + tree;
		changeMenu(menu);
					
		toShow = true;
		if (document.getElementById(menu).style.display=="none") {
			// the "menu" menu has just been hidden : all its subtrees must collapse too
			toShow = false;
		}
		
		list = document.getElementsByTagName("td");
		for (i=0; i<list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menu) != -1
				&& currentElement.id!=menu) {
				// we are at a submenu level
				subTreeName = currentElement.id.substring(8);
				
				if (currentElement.style.display=="none" && toShow
					|| currentElement.style.display=="" && !toShow) {	
					image = document.getElementById("treeViewImage" + subTreeName);
					
					link = image.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
						// il s'agit d'un javascript
						// => les sous-menus ont ?t? charg?s dans la page HTML
						// => on peut proc?der au changement
						
						if (image.src.indexOf("Close")!=-1) {
							reg=new RegExp("Close", "g");
							image.src = image.src.replace(reg, "Open");
						} else {
							reg=new RegExp("Open", "g");
							image.src = image.src.replace(reg, "Close");
						}
					
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {
							//return true;
						} else {
							changeMenu("treeView" + subTreeName);
						}
					}
					
				}
			}
		}
		
		return false;
	}
}

function expandFirstLevels(treeviewId, numberOfLevelsToExpand) {
	menuId = "treeView" + treeviewId;
		
	list = document.getElementsByTagName("td");
	for (i=0; i<list.length; i++) {
		currentElement = list[i];
		if (currentElement.id.indexOf(menuId) != -1
			&& currentElement.id!=menuId) {
			// we are at a submenu level
			
			idSuffix = currentElement.id.substring(menuId.length);
			if (countStringOccurence(idSuffix, "*") <= numberOfLevelsToExpand) {
				
				subTreeName = currentElement.id.substring(8);
				
				image = document.getElementById("treeViewImage" + subTreeName);
				
				link = image.parentNode;
				if (link.href.indexOf("javascript://") != -1) {
					// il s'agit d'un javascript
					// => les sous-menus ont ?t? charg?s dans la page HTML
					// => on peut proc?der au changement
					
					if (image.src.indexOf("Close")!=-1) {
						reg=new RegExp("Close", "g");
						image.src = image.src.replace(reg, "Open");
					} else {
						reg=new RegExp("Open", "g");
						image.src = image.src.replace(reg, "Close");
					}
				
					if (document.getElementById("treeView" + subTreeName).innerHTML == "") {
						//return true;
					} else {
						changeMenu("treeView" + subTreeName);
					}
				}
			}
		}
	}
}

function openAll(treeviewId, numberOfLevelsToExpand) {
	if (!isTreeviewLocked(treeviewId)) {
	    menuId = "treeView" + treeviewId;
	    var doModify;
		list = document.getElementsByTagName("td");
		for (i=0; i<list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menuId) != -1
				&& currentElement.id!=menuId) {
				// we are at a submenu level
	
				idSuffix = currentElement.id.substring(menuId.length);
				if (countStringOccurence(idSuffix, "*") <= numberOfLevelsToExpand) {
	
					subTreeName = currentElement.id.substring(8);
	
					image = document.getElementById("treeViewImage" + subTreeName);
	
					link = image.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
	
						if (image.src.indexOf("Close")!=-1) {
							reg=new RegExp("Close", "g");
							image.src = image.src.replace(reg, "Open");
							doModify = true;
						} else {
						    // do nothing
	                        doModify = false;
						}
	
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {
							//return true;
						} else {
						    if(doModify) {
							    changeMenu("treeView" + subTreeName);
	                        }
						}
					}
				}
			}
		}
	}
}

function closeAll(treeviewId, numberOfLevelsToExpand) {
	if (!isTreeviewLocked(treeviewId)) {
	    menuId = "treeView" + treeviewId;
	    var doModify;
		list = document.getElementsByTagName("td");
		for (i=0; i<list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menuId) != -1
				&& currentElement.id!=menuId) {
				// we are at a submenu level
	
				idSuffix = currentElement.id.substring(menuId.length);
				if (countStringOccurence(idSuffix, "*") <= numberOfLevelsToExpand) {
	
					subTreeName = currentElement.id.substring(8);
	
					image = document.getElementById("treeViewImage" + subTreeName);
	
					link = image.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
	
						if (image.src.indexOf("Close")!=-1) {
						    // do nothing						
							doModify = false;
						} else {
							reg=new RegExp("Open", "g");
							image.src = image.src.replace(reg, "Close");
							doModify = true;
						}
	
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {
							//return true;
						} else {
						    if (doModify) {
							    changeMenu("treeView" + subTreeName);
	                        }
						}
					}
				}
			}
		}
	}
}

function countStringOccurence(stringToTest, occurenceToCount) {
	index = stringToTest.indexOf(occurenceToCount);
	if (stringToTest.indexOf(occurenceToCount) != -1) {
		/*document.write((index + occurenceToCount.length) + "<br/>");
		document.write(stringToTest.substring(index + occurenceToCount.length) + "<br/>");
		occ = countStringOccurence(stringToTest.substring(index + occurenceToCount.length) , occurenceToCount);
		document.write(occ + "<br/>");*/
		return 1 + countStringOccurence(stringToTest.substring(index + occurenceToCount.length) , occurenceToCount);
	} else {
		return 0;
	}
}

var lockedTrees = new Array();

function lockTreeview(in_name) {
	lockedTrees[in_name] = "locked";
	var item = document.getElementById("treeView" + in_name);
	var links = item.getElementsByTagName("a");
	var link;
	item.style.cursor = "wait";
	for (i=0; i < links.length; i++) {
		link = links.item(i);
		link.style.cursor = "wait";
	}
}

function unlockTreeview(in_name) {
	lockedTrees[in_name] = null;
	var item = document.getElementById("treeView" + in_name);
	var links = item.getElementsByTagName("a");
	var link;
	item.style.cursor = "default";
	for (i=0; i < links.length; i++) {
		link = links.item(i);
		link.style.cursor = "default";
	}
}

function isTreeviewLocked(in_name) {
	var pos = in_name.indexOf('*');
	var name = pos==-1 ? in_name : in_name.substring(0, pos);
	var value = lockedTrees[name];
	return "locked" == value;
}

/**
 * Popup code
 */
function openpopup(form, popup, width, height, e) {
	var xx, yy;
	xx = e.screenX;
	yy = e.screenY;
	window.open('about:blank', 'popup', 'directories=0, location=0, menubar=0, status=0, toolbar=0, width=' + width + ', height=' + height + ', top=' + yy + ', left=' + xx); 	
	var action = form.action;
	var target = form.target;
	if (popup == null || popup == "") {
		popup = action;
	}
	form.target='popup';
	form.action = popup;
	form.submit();
	form.target = target;
	form.action = action;
		
	return false;
}

function closepopup(form, openerField, popupField) {
	var inputField = form[popupField];
	var value;
	if (inputField.options) {
		value = inputField.options[form[popupField].selectedIndex].value;
	} else if (inputField.type == "file") {
		value = inputField.value;
	} else {
		for (i=0; i < form.elements.length; i++) {
			var element = form.elements[i];
			if (element.name == popupField && element.checked) {
				value = element.value;
				break;
			}
		}
	}
	window.opener.document.forms[0][openerField].value = value;
	window.close();
}

/**
 * form changes detect code
 */
function checkFormChange(link, text) {
  var ok = true;
  for (var form=0; form < document.forms.length; form++) {
    what = document.forms[form];
    for (var i=0, j=what.elements.length; i<j; i++) {

        if (what.elements[i].type == "checkbox" || what.elements[i].type == "radio") {
            if (what.elements[i].checked != what.elements[i].defaultChecked) {
				ok = false; break;
		    }
		} else if (what.elements[i].type == "text" || what.elements[i].type == "hidden" || what.elements[i].type == "password" || what.elements[i].type == "textarea") {
            if (what.elements[i].value != what.elements[i].defaultValue) {
				ok = false; break;
		    }
		} else if (what.elements[i].type == "select-one" || what.elements[i].type == "select-multiple") {
			var selectSet = false;
			for (var k=0, l=what.elements[i].options.length; k<l; k++) {
				if (what.elements[i].options[k].defaultSelected) {
					selectSet = true;
				}
			}
			for (var k=0, l=what.elements[i].options.length; k<l; k++) {
				if (what.elements[i].options[k].selected != what.elements[i].options[k].defaultSelected && (selectSet || k!=0)) {
					ok = false; break;
				}
			}
		} else if (what.elements[i].type == "submit") {
			continue;	    
		} else if (what.elements[i].type == "button") {
			continue;		
		}  else if (what.elements[i].type == "file") {
			if (what.elements[i].value !=null && what.elements[i].value!="") {
				ok = false;
				break;			
			}			
		} else {
			alert(what.elements[i].type);
		}
    }
  }
    if (ok) {	
		window.location.href = link;
		return;
	    }
    if (confirm(text == null ? "Data will be lost. Continue ?" : text)) {
		window.location.href = link;
		return;
    }
}

/**
 * Shows the detail of the specified line.
 */
function showDetail(id, line) {
	// Get the object 
	var object = id[line];
	
	var fields = document.getElementsByTagName("input");
	var field;
	var value;
	
	// Update each property
	for (i in object) {
	
		// find the field tag.
		field = null;
		for (j in fields) {
			if (fields[j].type=="text" && fields[j].name==i) {
				field = fields[j];
				break;
			}
		}
		
		if (field) {
			value = object[i];
			field.value = value;
		}
	}
}

/**
 * Clear the details
 */
function clearDetail(id) {
	// Get the first object to check its properties
	var object = id[0];
	
	var fields = document.getElementsByTagName("input");
	var field;
	var value;
	
	// Update each property
	for (i in object) {
	
		// find the field tag.
		field = null;
		for (j in fields) {
			if (fields[j].type=="text" && fields[j].name==i) {
				field = fields[j];
				break;
			}
		}
		
		if (field) {
			value = object[i];
			field.value = "";
		}
	}
}

/**
 * Init dependent combo
 */
function initDependentComboHandler(masterSelectName, childSelectName, jsArrayName, jsChildArrayName, childSelectedValue) {
	// find the master select.
	var combo = findCombo(masterSelectName);
	var customFunction = new Function("return updateCombo('" + masterSelectName + "', '" + childSelectName + "', " + jsArrayName + ", '" + jsChildArrayName + "');");
	combo.onchange = customFunction;
	combo.onchange();
	
	// init child combo selected value.
	var childCombo = findCombo(childSelectName);
	for (i=0; i < childCombo.options.length;i++) {
		if (childCombo.options[i].value==childSelectedValue) {
			childCombo.selectedIndex = i;
			break;
		}
	}
}

function findCombo(comboName) {
	var elements = document.getElementsByTagName("SELECT");
	var combo;
	for (i in elements) {
		if (elements[i].name == comboName) {
			combo = elements[i];
		}
	}
	return combo;
}
 
/**
 * Update dependent combo.
 * @param masterCombo : the main select object.
 * @param comboName : the name of the child select object.
 * @param jsData : the name of the js data array holding the data.
 * @param jsCollectionProperty : the name of the nested collection property.
 */
function updateCombo(masterComboName, childComboName, jsData, jsCollectionProperty) {
	var masterCombo = findCombo(masterComboName);
	var combo = findCombo(childComboName);

	// get the option list.
	var value = masterCombo.options[masterCombo.selectedIndex].value;

	// get the selected bean
	var selectedValue = masterCombo.options[masterCombo.selectedIndex].value;
	var masterSelectedOption;
	for (i=0; i < jsData.length; i++) {		
		if (jsData[i].value == selectedValue) {
			masterSelectedOption = jsData[i];
			break;
		}
	}

	// remove old options
	while (combo.options.length!=0) {
		combo.remove(0);
	}

	// add new options
	if (masterSelectedOption!=null) {
		for (i = 0; i < masterSelectedOption[jsCollectionProperty].length; i++) {
			var option = new Option(masterSelectedOption[jsCollectionProperty][i].label, masterSelectedOption[jsCollectionProperty][i].value);
			if (document.all) {
				combo.add(option);
			} else {
				combo.add(option, null);
			}
		}
	}
}

/**
 * Get the key that was pressed
 */
function getKeyCode(e) {
	var code;
	if (!e) var e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	//var character = String.fromCharCode(code);
	return code;
}

/**
 * Go to the specified pager page.
 */
function pagerGoto(inputField, e, url, paramName, max) {	
	if (getKeyCode(e) != 13) {
		return;
	}	
	
	var value = inputField.value;
	var computedUrl = url;
	
	if (isNaN(parseInt(value, 10))) {
		return;
	}
	if (value <= 0) {
		return;
	}
	if (value > max) {
		return;
	}
		
	if (url.indexOf("?") == -1) {
		computedUrl += "?";
	} else {
		computedUrl += "&";
	}
	computedUrl += paramName + "=" + (value - 1);
	document.location = computedUrl;
}

function showRootMenu(td) {
	td.id = "css_hover";
	var element;
	var i;
	var length = td.childNodes.length;
	for (i=0; i < length; i++) {
		element = td.childNodes[i];	
		if (element.nodeName=="UL") {
			element.style.display = "block";		
		}
	}
}

function hideRootMenu(td) {
	td.id = null;
	var element;
	var i;
	var length = td.childNodes.length;
	for (i=0; i < length; i++) {
		element = td.childNodes[i];	
		if (element.nodeName=="UL") {
			element.style.display = "none";		
		}
	}
}

/**
 * Select / unselect all checkboxes for the collectionTag.
 */
function selectAllCollectionItems(checkbox,n,v,j) {
	var f = checkbox.form;
	var chk   = ( v == null ? true : v );
	for ( i = 0; i < j ; i++) {
		var name =  n + "[" + i + "]";
		if( f[name].type == "checkbox" ) {
			f[name].checked = checkbox.checked;
		}
	}
}

/**
 * For Layer tag
 */

var layerXOffset = 20;
var layerYOffset = 0;

function showLayoutLayer(ID,event) {
	var thisLayer = document.getElementById(ID);
	objLeft = 0;
	objTop = 0;
	objParent = thisLayer.offsetParent;
	while (objParent.tagName.toUpperCase() != "BODY") {
		objLeft  += objParent.offsetLeft;
		objTop   += objParent.offsetTop;
		objParent = objParent.offsetParent;
	}
	
	var layerPosY = event.clientY - objTop;
	var layerPosX = event.clientX - objLeft;	
   	var finalPosX = layerPosX - layerXOffset;   
   	if(finalPosX < 0) finalPosX = 0;
   	document.getElementById(ID).style.top = layerPosY + layerYOffset;
   	document.getElementById(ID).style.left = finalPosX;
//	document.getElementById(ID).style.zIndex= 3;
   	document.getElementById(ID).style.visibility = "visible";
}

function hideLayoutLayer(ID) {
    document.getElementById(ID).style.visibility = "hidden";
}