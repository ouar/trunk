<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div id="BoxPresentation">

	<ul>
		<li><a href="AfficherFormulaireGenerationQuizz">Génerer un Quizz</a></li>
	</ul>

	<ul>
		<li><a href="AfficherListQuizz">Consulter un Quizz</a></li>
	</ul>

	<ul>
		<li><a href="AdministrationQuizz?vueEncoursUtlisation=langage">Administration</a>
			<ul class="dropdown-menu">
				<li><a href="AdministrationQuizz?vueEncoursUtlisation=langage">Paramétrage Langage</a>
				<li><a href="AdministrationQuizz?vueEncoursUtlisation=typeSujet">Paramétrage Type sujet</a>
				<li><a href="AdministrationQuizz?vueEncoursUtlisation=Question">Paramétrage Question</a>
			</ul></li>
	</ul>



</div>
