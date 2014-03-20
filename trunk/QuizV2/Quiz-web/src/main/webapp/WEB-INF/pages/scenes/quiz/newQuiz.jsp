<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" 		prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		 	prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" 	prefix="form" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="content-language" content="fr">

		<title><tiles:getAsString name="title" /></title>



		<!-- CSS Twitter Bootstrap  -->
		<link href="<c:url value="/resources/css/libs/bootstrap-3.0.css" />" rel="stylesheet"  />

		<!-- CSS plugins JQuery -->
		<link href="<c:url value="/resources/css/libs/ui/jquery-ui-1.10.4.custom.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/libs/DT_bootstrap-3.0.css" />" rel="stylesheet" />
		<!-- CSS projet -->
		<link href="<c:url value="/resources/css/Quiz.css" />" rel="stylesheet" />
		<!-- CSS page -->
		<link href="<c:url value="/resources/css/scenes/quiz/newQuiz.css" />" rel="stylesheet" />


		<!-- JQuery -->
	    <script src="<c:url value="/resources/js/libs/jquery-1.10.2.min.js" />"></script>
	     <!-- JQuery Widget -->
		 <script src="<c:url value="/resources/js/libs/jquery-ui-1.10.4.custom.min.js" />"></script>
		 <!-- Bootstrap -->
		 <script src="<c:url value="/resources/js/libs/bootstrap-3.0.min.js" />"></script>


		<script src="<c:url value="/resources/js/scenes/quiz/Class-0.0.2.min.js" />"></script>
		<script src="<c:url value="/resources/js/scenes/quiz/ihmChoixSujets.js" />"></script>
		<script>
			//tableau qui va contenir les langages/sujets/difficultés à saisir
			var tabDispo = new Array();

			//tableau qui va contenir les langages/sujets/difficultés sélectionnés dans le panier
			//var tabChoisi = new Array();

			<c:forEach var="langage" items="${lLangages}">
				<c:forEach var="sujet" items="${langage.lSujets}">
					<c:forEach var="difficulte" items="${sujet.lDifficultes}">
						<c:out value="tabDispo.push(new entree(new element('${langage.langage.id}','${langage.langage.libelle}'),new element('${sujet.sujet.id}','${sujet.sujet.libelle}'), new element('${difficulte.difficulte.id}','${difficulte.difficulte.libelle}')));" escapeXml="false"></c:out>
					</c:forEach>
				</c:forEach>
			</c:forEach>

			var ihm = new ihm(tabDispo,'<c:url value="/resources/img/pictos_supprimer.gif" />');
		</script>
	</head>
	<body onload="ihm.refresh()">
	<div class="container" id="container">

			<!-- HEADER -->
			<div class="row">
				<tiles:insertAttribute name="header" />
			</div>

			<div class="row">
			    <div class="col-md-3">
					<!-- MENU -->
				    <tiles:insertAttribute name="menu" />
				</div>
				<div class="col-md-9">
						<!-- Message Panel -->
					<div class="row">
						<div id="messagePanel" style="display:none;" class="alert alert-error offset4 span4"></div>
					</div>
					<div class="row">
						<form:form method="post" action="create" name="formCreationQuiz" commandName="gestionFormBean" modelAttribute="gestionFormBean" id="generation">
						<div class="col-md-8">
								<span class="titre">
									<img src="<c:url value="/resources/img/question.png" />" />
									Génération d'un Quizz
								</span>
								<p>
									<label>Nom du Candidat</label>
									<form:input path="NomCandidat" name="NomCandidat" size="55" maxlength="40" id="idChampNomCandidat" />

								</p>

								<p>
									<label>Prenom du Candidat</label>
									<form:input path="PrenomCandidat" name="PrenomCandidat" id="idChampPrenomCandidat" size="55" maxlength="40" />

								</p>
								<form:hidden path="jsonSujetDifficulte" id="idDujetDifficulte"/>
								<!-- <input type="hidden" id="idDujetDifficulte" name="JsonSujetDifficulte"/> -->
								<div id="quizzCandidat">
								</div>

							</div>
							<div class="col-md-4" >
								<span class="titre"><img
									src="<c:url value="/resources/img/panier.png" />" />
									Mon Panier </span>
								<div id="divPanier"></div>
							<br/>
							</div>
						</form:form>
					</div>
				</div>
			</div>


			<hr />

			<div class="footer" id="footer">
		       <tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>