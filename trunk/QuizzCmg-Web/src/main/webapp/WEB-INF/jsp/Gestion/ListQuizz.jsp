<%@page language="java"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
<head>
<link type="text/css" href="<%=application.getContextPath()%>/css/formulaire-css3.css" media="projection, screen" rel="stylesheet">

</head>
<body>

	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->

		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>


		<div id="BoxCentre">
		
		<form:form method="post"  action="ConsulterQuizz" name="ConsulterQuizz"  commandName="gestionFormBean" modelAttribute="gestionFormBean">
			
	
		  <span class="titre">Liste des quizz</span>
		  
		  <p>
		
		  <select name="idQuizzAConsulter">
		  	<c:forEach var="quizz" items="${gestionFormBean.listQuizz}">
		  	<option value="${quizz.id}">${quizz.datQuizz} ${quizz.libNomCandidat}</option>
		  	</c:forEach>
		  </select>
		  <br/>
		  </p>
		 
		  <div>
		  	<input type="submit" value="Consulter"/>
		  </div>
	 </form:form>
	</div>

	</div>
	
	

</body>





</html>