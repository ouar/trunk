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
<title>Paramétrage Type Sujet</title>
</head>
<body>

	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->
		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>

		<div id="BoxCentre" style="display: inline-block">
			<form:form method="post" action="Ajouter" name="Ajouter" modelAttribute="administrationFormBean" id="adminQuestion" enctype="multipart/form-data">


				<table id="dataTableAdminQuestions" class="display">
					<thead>
						<tr class="ui-state-default">
							<!-- <th align="left" class="devant ui-state-default"><div class="titre-colonne">Langage</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libellé Type Sujet</div></th> -->
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libellé question</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libellée niveau</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">duréee réfléxion</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">nombre réponse possible</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Image</div></th>



						</tr>
					</thead>
					<tbody>
						<c:forEach var="questionBean" items="${administrationFormBean.listQuestionsFiltres}">
							<tr>
								<%-- <td>${question.typeSujet.langage.libelle}</td>
								<td>${question.typeSujet.libelle}</td> --%>
								<td>${questionBean.libQuestion}</td>
								<td>${questionBean.difficulteBean.libelle}</td>
								<td>${questionBean.intDureeReflexion}</td>
								<td>${questionBean.bolUniqueReponse}</td>
								<td>${questionBean.urlImage}</td>

							</tr>
						</c:forEach>



					</tbody>
				</table>

				<input type="submit" value="Ajouter" id="adminbtnAjouterQuestion" />
				<input name="vueEncoursUtlisation" type="hidden" value="Question" />

				

				<div id="divAjoutQuestion" style="display: inline-block">


					<label>Type sujet :</label>${administrationFormBean.libelleTypeSujetFiltree}
					
					<br><br><br>
					<label>Niveau :</label>
					
					<form:select path="idNiveauQuestion" id="idNiveauQuestion">

						<c:forEach var="NiveauQuestion" items="${sessionScope['ListeNiveauxDifficultes']}">
							<form:option value="${NiveauQuestion.id}" >${NiveauQuestion.libNiveau}</form:option>
						</c:forEach>

					</form:select>
					
					<label>Libellé question :</label><form:input path="libelleQuestion"/> 
					
					<label>durée réfléxion  :</label><form:input path="dureeReflexion"/>
					
					<label>Reponses :</label>
					Réponse1 :<form:input path="reponse"/>
					Réponse2 :<form:input path="reponse"/>
					Réponse3 :<form:input path="reponse"/>
					Réponse4 :<form:input path="reponse"/>
					
					Image  : <form:input path="image" type="file"/>
					
				
				</div>
			
			<form:input path="idTypeSujet"  cssStyle="visibility: hidden;"/>
			</form:form>
		</div>

	</div>
</body>
</html>