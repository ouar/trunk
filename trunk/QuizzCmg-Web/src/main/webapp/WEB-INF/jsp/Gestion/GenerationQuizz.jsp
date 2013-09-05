<%@page language="java"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib uri="quizzCmgTag" prefix="quizzCmgTag"%>





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
<script type="text/javascript" src="<%=application.getContextPath()%>/js/Gestion/gestion.js"></script>



</head>

<body>
	<div id="content">
		<jsp:include page="../include/entete.jsp" />
		<!--  Note: le tag html:errors ne fonctionne pas
			      => on garde l'ancien html:Warning pour l'instant
			  -->




		<jsp:include page="../include/menu_gauche.jsp"></jsp:include>



		<form:form method="post" action="GenererQuizz" name="GenererQuizz" commandName="gestionFormBean" modelAttribute="gestionFormBean" id="generation">
			<quizzCmgTag:quizzCmg lLangageBeans="${ListeLangages}" listPanier="${gestionFormBean.listNiveauTypeSujetPanier}" listAselectionner="${gestionFormBean.listNiveauTypeSujetAselectionner}"></quizzCmgTag:quizzCmg>

			<div id="BoxCentre" style="display: inline-block">
				<span class="titre"><img src="<%=application.getContextPath()%>/images/quizz.gif" /> Génération d'un Quizz </span>

				<p>

					<label>Langage </label>
					<form:select path="IdLangage">


					</form:select>



					<br />
				</p>
				<div id="quizzCandidat" style="padding-bottom: 20px;">

					<table id="tabTypeSujet">
						<thead>
							<tr>
								<th><label>Type de sujet</label></th>
								<th><label>Niveau de difficulté</label></th>
							</tr>
						</thead>

						<c:forEach var="typeSujet" items="${sessionScope['LISTETYPESSUJETSPANIER']}" varStatus="status">
							<tr>
								<td style="padding-top: 5px; padding-bottom: 5px;"></td>
								<td style="padding-top: 5px; padding-bottom: 5px;"><select name="IdNiveauQuestionnaire${status.count}" id="IdNiveauQuestionnaire${status.count}">
										<c:forEach var="niveau" items="${applicationScope['LISTENIVEAUXDIFFICULTES']}" varStatus="status">
											<option value="${niveau.id}" <c:if test="${niveau.id eq GESTION_SCENARIO_BEAN.idNiveauQuestionnaire}">selected="selected"</c:if>>${niveau.libNiveau}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:forEach>

					</table>

				</div>
				<p>
					<label>Nom du Candidat</label>
					<form:input path="NomCandidat" name="NomCandidat" size="55" maxlength="40" id="idChampNomCandidat" />

				</p>

				<p>
					<label>Prenom du Candidat</label>
					<form:input path="PrenomCandidat" name="PrenomCandidat" id="idChampPrenomCandidat" size="55" maxlength="40" />

				</p>
				<input type="submit" value="Générer" id="btnGenerer" /> <input type="button" value="Ajouter" id="btnAjouter" /> <input type="hidden" id="panier" name="panier" /> <input type="hidden" id="aselectionner" name="aselectionner" />

			</div>




			<div id="BoxDroite" style="display: inline-block">
				<span class="titre"> Mon Panier </span>



				<jsp:include page="panier.jsp"></jsp:include>

			</div>
		</form:form>
	</div>

</body>
</html>
