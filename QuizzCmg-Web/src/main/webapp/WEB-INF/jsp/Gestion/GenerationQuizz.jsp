<%@page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
<!--[if lt IE 8]>
    <script src="http://www.json.org/json2.js"></script>
<![endif]-->
<link type="text/css"
	href="<%=application.getContextPath()%>/css/formulaire-css3.css"
	media="projection, screen" rel="stylesheet">
	<link type="text/css"
	href="<%=application.getContextPath()%>/css/datatable/jquery.dataTables.css"
	media="projection, screen" rel="stylesheet">

<script type="text/javascript"	src="<%=application.getContextPath()%>/js/Gestion/jquery-1.9.1.js"></script>
<script type="text/javascript"	src="<%=application.getContextPath()%>/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript"	src="<%=application.getContextPath()%>/js/jquery.dataTables.js"></script>
<script type="text/javascript"	src="<%=application.getContextPath()%>/js/Gestion/gestion.js"></script> 
<script type="text/javascript"  src="<%=application.getContextPath()%>/js/Gestion/Class-0.0.2.min.js"></script>
<script type="text/javascript"	src="<%=application.getContextPath()%>/js/Gestion/ihmChoixSujets.jsp"></script>
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
	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->




		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>



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

</body>
</html>
