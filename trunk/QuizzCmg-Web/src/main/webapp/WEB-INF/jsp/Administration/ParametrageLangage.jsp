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
<title>Paramétrage Langage</title>
</head>
<body>

	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->
		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>

		<div id="BoxCentre" style="display: inline-block">
			<form:form method="post" action="Ajouter" name="Ajouter" commandName="administrationFormBean" modelAttribute="administrationFormBean" id="adminLangage">
		


				<table id="dataTableAdminLangage" class="display">
					<thead>
						<tr class="ui-state-default">

							<th align="left" class="devant ui-state-default"><div class="titre-colonne">ID Langage</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne">Libellé Langage</div></th>
							<th align="left" class="devant ui-state-default"><div class="titre-colonne"></div></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="langageBean" items="${sessionScope['ListeLangages']}">
							<tr>
								<td>${langageBean.id}</td>
								<td>${langageBean.libelle}</td>
								<td>${langageBean.lSujetJson}</td>

							</tr>
						</c:forEach>



					</tbody>
				</table>
			
				<input type="submit" value="Ajouter" id="adminbtnAjouterLangage" />
				<input name="vueEncoursUtlisation" type="hidden" value="langage"/>				
				<form:input path="libelleLangage"/>
			
			
			</form:form>
		</div>

	</div>
</body>
</html>