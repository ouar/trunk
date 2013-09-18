<%@page language="java"%>

<%@ page import="fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<jsp:useBean id="CONNECTE" class="fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User" scope="session" />

<%
	User user = (User) request.getSession().getAttribute("CONNECTE");
%>

<div id="BoxPresentation">

	<%
		if (user != null && ((user.getIdRole() & 2) == 2)) {
	%>

	<ul>
		<li><a href="AfficherFormulaireGenerationQuizz">Génerer un Quizz</a></li>
	</ul>

	<%
		}
	%>
	<%
		if (user != null && ((user.getIdRole() & 1) == 1)) {
	%>
	<ul>
		<li><a href="AfficherListQuizz">Consulter un Quizz</a></li>
	</ul>
	<%
		}
	%>
	<%
		if (user != null && ((user.getIdRole() & 4) == 4)) {
	%>

	<ul>
		<li><a href="AdministrationQuizz?vueEncoursUtlisation=langage">Administration</a>
	</ul>

	<%
		}
	%>

</div>