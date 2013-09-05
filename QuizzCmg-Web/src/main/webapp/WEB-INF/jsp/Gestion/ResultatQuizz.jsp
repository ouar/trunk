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
			<span class="titre"><img
				src="<%=application.getContextPath()%>/images/quizz.gif" />
				Resultats du  quizz </span>

<form:form method="post"  action="GenererQuizz" name="GenererQuizz"  commandName="gestionFormBean" modelAttribute="gestionFormBean">
			
				<c:set var="quizz" value="${gestionFormBean.quizz}"
						scope="page" />
				
			
						
					<span class="libNomCandidatDateQuizz">Nom du candidat : </span> <c:out value="${quizz.libNomCandidat}"/><br/>
					<span class="libNomCandidatDateQuizz">Nom du candidat : </span> <c:out value="${quizz.datQuizz}"/><br/>
				
			

			
				<c:set var="listWrapperResultatsQuizz" value="${gestionFormBean.listWrapperResultatsQuizz}"
						scope="page" />
					<ul>
					<c:forEach var="wrapperResultatsQuizz" items="${listWrapperResultatsQuizz}">
						<li class="question">
						<div class="libelleQuestion"><c:out value="${wrapperResultatsQuizz.libQuestion}" /> (<c:out value="${wrapperResultatsQuizz.libNiveau}" />  )</div>
						
						<ul>
						<c:forEach var="reponse" items="${wrapperResultatsQuizz.lWrapperReponses}">
							<li>																															
									<input type="checkbox" disabled="disabled" <c:if test="${reponse.hasReponseCandidat}" > checked ="checked" </c:if> /> 
									<span 
										<c:choose> 
											<c:when test="${reponse.reponseCorrecte}" > class="bonneReponse" </c:when> 
											<c:otherwise> <c:if test="${reponse.hasReponseCandidat}" >  class="mauvaiseReponse" </c:if> </c:otherwise>  
										</c:choose> 
									> 
									<c:out value="${reponse.libReponseParametre}"/> </span> 
							</li>
						</c:forEach>
						</ul>
						</li>
					</c:forEach>
					</ul>
			</form:form>	
		</div>

	</div>
	
	

</body>
</html>