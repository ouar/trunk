<%@page language="java"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<html>
<head>
<link type="text/css"
	href="<%=application.getContextPath()%>/css/formulaire-css3.css"
	media="projection, screen" rel="stylesheet">
<script type="text/javascript"
	src="<%=application.getContextPath()%>/js/jquery-1.7.2-dev.js"></script>

</head>
<body>

	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->

		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>


		<div id="BoxCentre" >
			<span class="titre"><img
				src="<%=application.getContextPath()%>/images/quizz.gif" />
				Lien Quizz </span>
			<div id="divFlashCode"></div>
			<form:form method="post"  action="Repondrequizz" name="Repondrequizz"  commandName="gestionFormBean" modelAttribute="gestionFormBean">		
				<%-- <c:set var="quizz" value="${gestionFormBean.quizz}"
					scope="page" />
				<ul>
					<c:forEach var="quizzquestion" items="${quizz.quizzQuestions}">
						<li class="question">
							<div class="libelleQuestion">
								<c:out value="${quizzquestion.question.libQuestion}" />
							</div> <span style="display: none">${quizzquestion.question.id}</span>
							<ul>
								<c:forEach var="reponse"
									items="${quizzquestion.question.reponses}">
									<li>${reponse.libReponse}</li>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul> --%>
				<script type="text/javascript">
					var urlFlashCode = '${gestionFormBean.urlFlashCode}';

					$(document).ready(
							function() {

								$("#divFlashCode").html(
										$("<img>").attr("src", urlFlashCode));

							});
				</script>

			</form:form>
		</div>

	</div>


</body>
</html>