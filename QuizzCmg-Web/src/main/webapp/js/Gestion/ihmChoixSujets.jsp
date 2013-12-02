function element(id,libelle){
	this.id = id;
	this.libelle = libelle;
}

function entree(langage, sujet, difficulte){
	this.langage = langage;
	this.sujet = sujet;
	this.difficulte = difficulte;
}

entree.prototype.toJSON = function(){
	return '{"langageid":"'+this.langage.id+'",'+
        '"langagelib":"'+this.langage.libelle+'",'+
        '"sujetid":"'+this.sujet.id+'",'+
        '"sujetlib":"'+this.sujet.libelle+'",'+
        '"difficulteid":"'+this.difficulte.id+'",'+            
        '"difficultelib":"'+this.difficulte.libelle+'"}';
};	

var ihm = Class.create({

	init: function(tableau_langage_sujet_difficulte_disponible){
		this.tabDispo  = tableau_langage_sujet_difficulte_disponible;
		this.tabChoisi = new Array();
		this.langageSelectionne = "";
	},

	ajoutePanier: function(entreeAAjouter){
		//on vérifie que l'entree à ajouter est bien dans les diponibles
		//et n'est pas dans les choisies
		var difficulte = new element($("#difficulte_"+entreeAAjouter.sujet.id+" option:selected").val(),
		$("#difficulte_"+entreeAAjouter.sujet.id+" option:selected").text());
		entreeAAjouter.difficulte = difficulte;
		var indice = this.isIn(entreeAAjouter,this.tabDispo)
		if(indice > -1 && this.isIn(entreeAAjouter,this.tabChoisi) == -1){
			//on retire l'entrée de la liste des diponibles
			this.tabDispo.splice(indice,1);
			//on ajoute l'entrée à la liste des choisies
			this.tabChoisi.push(entreeAAjouter);
			//on joue l'effet
			$("#divSujet_"+entreeAAjouter.sujet.id).
			effect("transfer", {to: "#BoxPanier",className: "ui-effects-transfer"}, 200, function(){ihm.refresh();});
		}else{
			alert("Erreur, l'entrée est déjà choisie ou n'est plus disponible");
		}
	},
	
	retirePanier: function(entreeARetirer){
		//on vérifie que l'entree à retirer est bien dans les choisies
		//et n'est pas dans les disponibles
		var indice = this.isIn(entreeARetirer,this.tabChoisi)
		if(indice > -1 && this.isIn(entreeARetirer,tabDispo) == -1){
			//on retire l'entrée de la liste des diponibles
			this.tabChoisi.splice(indice,1);
			//on ajoute l'entrée à la liste des choisies
			this.tabDispo.push(entreeARetirer);
			//on joue l'effet
			$("#divSujetChoisi_"+entreeARetirer.sujet.id).
			effect("transfer", {to: "#quizzCandidat",className: "ui-effects-transfer"}, 200, function(){ihm.refresh();});
			
		}else{
			alert("Erreur, l'entrée est déjà disponible ou n'a as été choisie");
		}
	},
	
	refreshSujets:function(){
		if($('#idLangage').val() != undefined){
			this.langageSelectionne = $('#idLangage').val();
		}else{
			alert("Erreur !! Le select des langages n'a pas été trouvé");
		}
		$('#divSujets').html("");
		
		$(this.tabDispo).each(function(index,value){
			if(value.langage.id == ihm.langageSelectionne){
				if($("#divSujet_"+value.sujet.id).attr('id') == undefined){
					//si le sujet n'était pas affiché
					var divSujet = ihm.afficheSujetDisponible(value);
					$("#divSujets").append(divSujet);
				}else{
					//sinon on ajoute la difficulté au select du sujet
					$("#difficulte_" + value.sujet.id).append(ihm.createOption(value.difficulte.id,value.difficulte.libelle));
				}
			}
		});
		
	},
	
	refreshPanier:function(){
		$('#divPanier').html("");
		$(this.tabChoisi).each(function(index,value){
			var divLangage = $("#divLangageChoisi_"+value.langage.id);
			if(divLangage.length == 0){
				divLangage = ihm.createDiv("divLangageChoisi_"+value.langage.id,"divLangage");
				divLangage.appendChild(ihm.createSpan(value.langage.libelle,"titreLangage"));
				$('#divPanier').append(divLangage);
			}else{
				divLangage = divLangage[0];
			}
			var divSujet = $("#divSujetChoisi_"+value.sujet.id);
			if(divSujet.length == 0){
				divSujet = ihm.createDiv("divSujetChoisi_"+value.sujet.id,"divSujet");
				divLangage.appendChild(divSujet);
				divSujet.appendChild(ihm.createLabel(value.sujet.libelle));
			}else{
				divSujet = divSujet.get(0);
			}
			var spanDifficulte = ihm.createSpan(value.difficulte.libelle,"spanDifficulte");
			var lienSupprimeDifficulte = ihm.createLien("#", "", undefined);
			
			lienSupprimeDifficulte.setAttribute("onClick","ihm.retirePanier(new entree(new element('"+value.langage.id+"','"+value.langage.libelle+"'),"+
				"new element('"+value.sujet.id+"','"+value.sujet.libelle+"'), new element('"+value.difficulte.id+"','"+value.difficulte.libelle+"')));");
			var imgSuppr = ihm.createImg("<%=application.getContextPath()%>/images/pictos_supprimer.gif");
			lienSupprimeDifficulte.appendChild(imgSuppr);
			spanDifficulte.appendChild(lienSupprimeDifficulte);
			divSujet.appendChild(spanDifficulte);
			
		});
		var input = this.createInput("submit","Générer",undefined,"btnGenerer");
		input.setAttribute("onClick","ihm.post()")
		$('#divPanier').append(input);
	},
	
	populateSelectLangage: function(selectLangage){
		if(tabDispo.length>0){
			$(tabDispo).each(function(index,value){
				if($("#idLangage:has(option[value='"+value.langage.id+"'])").length == 0){
					var optionLangage = ihm.createOption(value.langage.id,value.langage.libelle)
					if(value.langage.id == ihm.langageSelectionne){
						optionLangage.setAttribute("selected","selected");
					}
					selectLangage.appendChild(optionLangage);
				}
			});
		}
	},
	
	refresh: function(){
		this.langageSelectionne = 1;
		if($('#idLangage').val() != undefined){
			this.langageSelectionne = $('#idLangage').val();
		}
		$('#quizzCandidat').html("");
		
		var divLangage = this.createDiv("divLangage","");
		$('#quizzCandidat').append(divLangage);
		if(this.tabDispo.length > 0){
			var label = this.createLabel("Langage")
			var selectLangage = this.createSelect("idLangage","Langage");
			selectLangage.setAttribute("onChange","ihm.refreshSujets();");
			divLangage.appendChild(label);
			divLangage.appendChild(selectLangage);
			var divSujets = this.createDiv("divSujets","");
			$('#quizzCandidat').append(divSujets);
			
			this.populateSelectLangage(selectLangage);
			this.refreshSujets();
		}
		if(this.tabChoisi.length>0){
			this.refreshPanier();
		}else{
			$('#divPanier').html("");
		}
	},
	
	afficheSujetDisponible: function(value){
		var divSujet = this.createDiv("divSujet_" + value.sujet.id, "divSujet");
		var label = this.createLabel(value.sujet.libelle)
		var selectDifficulte = this.createSelect("difficulte_" + value.sujet.id ,"ListNiveau" + value.sujet.id);
		selectDifficulte.appendChild(this.createOption(value.difficulte.id,value.difficulte.libelle));
		var lien = this.createLien("#", "Ajouter", "submit");
		lien.setAttribute("onClick","ihm.ajoutePanier(new entree(new element('"+value.langage.id+"','"+value.langage.libelle+"'),"+
				"new element('"+value.sujet.id+"','"+value.sujet.libelle+"'), null));");
		lien.setAttribute("style","float:right; margin-right:30px");
		
		divSujet.appendChild(label);
		divSujet.appendChild(selectDifficulte);
		divSujet.appendChild(lien);

		return divSujet;
	},

	post: function(){
		var json = "[";
		$(this.tabChoisi).each(function(index,value){
			json+=value.toJSON()+",";
		});
		json=json.substr(0,json.length-1)+"]";
		$("#idDujetDifficulte").val(json);
	},
	
	createLien: function(href, libelle, classe){
		var lien = document.createElement('a');
		lien.setAttribute("href",href);
		lien.appendChild(this.createText(libelle));
		if(classe != undefined && classe != ""){
			lien.setAttribute('class', classe);
		}
		return lien;
	},
	
	createImg:function(src){
		var img = document.createElement('img');
		img.setAttribute("src",src);
		return img;
	},
	
	createDiv: function(id,classe){
		var div = document.createElement('div');
		div.setAttribute('id', id);
		if(classe != undefined && classe != ""){
			div.setAttribute('class', classe);
		}
		return div
	},
	
	createSelect: function(id,name){
		var select= document.createElement('select');
		select.setAttribute('name', name);
		select.setAttribute('id', id);
		return select;
	},
	
	createOption: function(id,libelle){
		var option = document.createElement("option");
		option.setAttribute('value',id);
		var optionLib = document.createTextNode(libelle);
		option.appendChild(optionLib);
		return option;
	},
	
	createLabel: function(libelle){
		var label = document.createElement('label');
		label.appendChild(document.createTextNode(libelle));
		return label;
	},
	
	createCheckBox: function(value){
		var checkBox = document.createElement('input');
		checkBox.setAttribute('type','checkbox');
		checkBox.setAttribute('value',value);
		return checkBox;
	},
	
	createText: function(libelle){
		var texte = document.createTextNode(libelle);
		return texte;
	},
	

	createForm:function(action){
		var form = document.createElement('form');
		form.setAttribute("action",action);
		form.setAttribute("method","post");
		return form;
	},

	createInput: function(type,value,name,id){
		var input = document.createElement('input');
		input.setAttribute('type',type);
		input.setAttribute('value',value);
		input.setAttribute('id',id);
		return input;
	},
	
	createSpan: function(libelle, classe){
		var span = document.createElement('span');
		span.appendChild(ihm.createText(libelle));
		if(classe != undefined && classe != ""){
			span.setAttribute('class', classe);
		}
		return span;
	},		

	isIn: function(entree,tableauDEntree){
		var indice = -1;
		
		$(tableauDEntree).each(function(index,value){
			//comparaison des champs
			if(entree.langage.id == value.langage.id &&
				entree.sujet.id == value.sujet.id &&
				entree.difficulte.id == value.difficulte.id){
				indice = index;
			}
		});
		return indice;
	}

});
