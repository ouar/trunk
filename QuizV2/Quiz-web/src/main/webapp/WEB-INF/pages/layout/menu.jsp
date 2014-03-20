<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="well sidebar-nav">

	<ul class="nav nav-pills nav-stacked">

	<sec:authorize access="hasRole('ROLE_USER')">
		<li><a href="AfficherFormulaireGenerationQuizz">Génerer un Quizz</a></li>
		<li><a href="AfficherListQuizz">Consulter un Quizz</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href="AdministrationQuizz?vueEncoursUtlisation=langage">Administration</a></li>
	</sec:authorize>

	</ul>

</div>



