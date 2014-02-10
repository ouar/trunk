<%@page language="java"%>

<%@ page import="fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:useBean id="CONNECTE"
	class="fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User"
	scope="session" />


<div id="BoxPresentation">

	<sec:authorize access="hasRole('ROLE_USER')">
		<ul>
			<li><a href="AfficherFormulaireGenerationQuizz">Génerer un
					Quizz</a>
			</li>
		</ul>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<ul>
			<li><a href="AfficherListQuizz">Consulter un Quizz</a>
			</li>
		</ul>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<ul>
			<li><a href="AdministrationQuizz?vueEncoursUtlisation=langage">Administration</a>
			</li>
		</ul>

	</sec:authorize>

</div>