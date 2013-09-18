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
<%-- <script type="text/javascript" src="<%=application.getContextPath()%>/js/Gestion/gestion.js"></script> --%>
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
			<form:form method="post" action="Ajouter" name="administrationFormBean" commandName="administrationFormBean" modelAttribute="administrationFormBean" id="adminLangage">
 		
 			  ${erreur}
				<div style="display: inline-block; border: 1px black solid;width: 100%">
					<c:forEach var="langageBean" items="${sessionScope['ListeLangages']}">
						<div style="border-right: 1px solid black; width: 100%; height: 100%">
							<div style="width: 50%; float: left; display: table;">
								<label>${langageBean.libelle}</label>
							</div>
							<div style="width: 50%; float: right; display: table-cell;">
								<c:forEach var="sujetBean" items="${langageBean.lSujet}">
									<label><a href="../QuizzCmg-Web/DetailTypeSujet?idTypeSujet=${sujetBean.id}&libelleTypeSujet=${sujetBean.libelle}">${sujetBean.libelle}</a></label>
									<a href="../QuizzCmg-Web/Supprimer?idTypeSujet=${sujetBean.id}&vueEncoursUtlisation=typeSujet"><img src="../images/pictos_supprimer.gif"></a>
								</c:forEach>
								<input type="submit" value="Ajouter type sujet" id="adminbtnAjouterTypeSujet" onclick="ajouterTypeSujet(${langageBean.id});" />
								
								<form:input path="libelleTypeSujet" />
									
							</div>
						</div>
					</c:forEach>
				</div>

				<input type="submit" value="Ajouter Langage" id="adminbtnAjouterLangage" onclick="ajouterLangage();" />
				<form:input path="libelleLangage" />
				<input name="vueEncoursUtlisation" id="vueEncoursUtlisation" type="hidden" />
				<input name="idLangage" id="idLangage" type="hidden" />

			</form:form>

		</div>

	</div>
</body>
</html>