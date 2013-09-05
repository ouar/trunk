<%@page language="java"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EDGE" />
<!--[if lt IE 8]>
    <script src="http://www.json.org/json2.js"></script>
<![endif]-->
<link type="text/css" href="<%=application.getContextPath()%>/css/formulaire-css3.css" media="projection, screen" rel="stylesheet">
<link type="text/css" href="<%=application.getContextPath()%>/css/datatable/jquery.dataTables.css" media="projection, screen" rel="stylesheet">

<script type="text/javascript" src="<%=application.getContextPath()%>/js/Gestion/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/Administration/Administration.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/Gestion/gestion.js"></script>
<title>Param�trage Type Sujet</title>
</head>
<body>

	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->
		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>

		<div id="BoxCentre" style="display: inline-block">
			<form:form method="post" action="Ajouter" name="Ajouter" commandName="administrationFormBean" modelAttribute="administrationFormBean" id="adminQuestion">


				<table id="dataTableAdminQuestions" class="display">
					<thead>
						<tr class="ui-state-default">
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Langage</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libell� Type Sujet</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libell� question</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libell�e niveau</div></th>			
								<th align="left" class="devant ui-state-default"><div class="titre-colonne">dur�ee r�fl�xion</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">nombre r�ponse possible</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Image</div></th>					



						</tr>
					</thead>
					<tbody>
						<c:forEach var="question" items="${sessionScope['ListeQuestionss']}">
							<tr>
								<td>${question.typeSujet.langage.libelle}</td>
								<td>${question.typeSujet.libelle}</td>
								<td>${question.libQuestion}</td>
								<td>${question.niveauQuestion.libNiveau}</td>		
								<td>${question.intDureeReflexion}</td>
								<td>${question.bolUniqueReponse}</td>		
								<td>${question.urlImage}</td>							
												
							</tr>
						</c:forEach>
						


					</tbody>
				</table>
				<%-- <input type="button" value="Ajouter" id="adminbtnAjouter" />

				<div id="divAjoutTypeSujet" style="display: inline-block">

					<form:select path="idLangage" id="idLangage">

						<c:forEach var="langageBean" items="${sessionScope['ListeLangages']}">
							<form:option value="${langageBean.id}">${langageBean.libelle}</form:option>
						</c:forEach>

					</form:select>

					<input type="text" id="libelleTypeSujet">
				</div>

				<div id="divAjoutQuestion" style="display: inline-block">

					<form:select path="idQuestion" id="idQuestion">

						<c:forEach var="NiveauQuestion" items="${sessionScope['ListeNiveauxDifficultes']}">
							<form:option value="${NiveauQuestion.id}">${NiveauQuestion.libNiveau}</form:option>
						</c:forEach>

					</form:select>

					<input type="text" id="libelleQuestion">
				</div>
				<script type="text/javascript" charset="utf-8">
					$(document).ready(function() {
						<c:forEach var="typeSujet" items="${sessionScope['ListeTypeSujets']}">
						var ObjetAdmin = new $.fn.ObjetAdmin('${typeSujet.langage.id}', '${typeSujet.langage.libelle}', '${typeSujet.id}', '${typeSujet.libelle}', '1', 'facile');
						adminListTypeSujets.push(ObjetAdmin);

						</c:forEach>

					});
				</script> --%>

			</form:form>
		</div>

	</div>
</body>
</html>