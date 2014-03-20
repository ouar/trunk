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

		<title><tiles:getAsString name="Création d'un nouveau Quiz" /></title>
		
		
		<!-- CSS Twitter Bootstrap  -->
		<link href="<c:url value="/resources/css/libs/bootstrap-3.0.css" />" rel="stylesheet"  />

		<!-- CSS plugins JQuery -->
		<link href="<c:url value="/resources/css/libs/jquery.dataTables-1.9.4.css" />" rel="stylesheet" >
		<link href="<c:url value="/resources/css/libs/ui/jquery-ui-1.10.2.custom.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/libs/DT_bootstrap-3.0.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/libs/select2-3.4.2.css" />" rel="stylesheet" />
		<!-- CSS projet -->
		<link href="<c:url value="/resources/css/Quiz.css" />" rel="stylesheet" />

		  <!-- All JavaScript at the bottom, except for Modernizr / Respond.
		       Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries
		       For optimal performance, use a custom Modernizr build: www.modernizr.com/download/ -->

		 <script src="<c:url value="/resources/js/libs/modernizr-2.6.1.min.js" />"></script>
		 
		<script>
			//tableau qui va contenir les langages/sujets/difficultés à saisir
			var tabDispo = new Array();
			
			//tableau qui va contenir les langages/sujets/difficultés sélectionnés dans le panier
			var tabChoisi = new Array();
			
			<c:forEach var="langage" items="${ListeLangages}">
				<c:forEach var="sujet" items="${langage.lSujet}">
					<c:forEach var="difficulte" items="${sujet.lDifficultes}">
						<c:out value="tabDispo.push(new entree(new element('${langage.id}','${langage.libelle}'),new element('${sujet.id}','${sujet.libelle}'), new element('${difficulte.id}','${difficulte.libelle}')));" escapeXml="false"></c:out>
					</c:forEach>
				</c:forEach>
			</c:forEach>
			
			var ihm = new ihm(tabDispo, tabChoisi);
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
						<form:form method="post" action="GenererQuizz" name="generation" commandName="gestionFormBean" modelAttribute="gestionFormBean" id="generation">
							<div id="BoxCentre">
								<span class="titre">
									<img src="<%=application.getContextPath()%>/images/question.png" />
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
				
				
				
				
									<div id="BoxPanier">
								<span class="titre"><img
									src="<%=application.getContextPath()%>/images/panier.jpg" />
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

	     <!-- JQuery -->
	     <script src="<c:url value="/resources/js/libs/jquery-1.10.2.min.js" />"></script>
	     <!-- JQuery Widget -->
		 <script src="<c:url value="/resources/js/libs/jquery-ui-1.10.2.custom.min.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.ui.datepicker-fr.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.metadata-2.0.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.validate-1.10.1.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/additional-methods-1.10.0.min.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/select2-3.4.2.js" />"></script>
		 <!-- JQuery Widget Datatables -->
		 <script src="<c:url value="/resources/js/libs/jquery.dataTables-1.9.4.min.js" />"></script>
		 <!-- PURL, parse des parametres http en js -->
		 <script src="<c:url value="/resources/js/libs/purl-2.2.1.js" />"></script>

		 <!-- Bootstrap -->
		 <script src="<c:url value="/resources/js/libs/bootstrap-3.0.min.js" />"></script>

	</body>
</html>