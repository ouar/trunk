<%@page language="java"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>



<table id="dataTablepanier" class="display">
	<thead>
		<tr class="ui-state-default">
			<th align="left" class="devant ui-state-default"><div class="titre-colonne">Langage</div></th>
			<th align="left" class="devant ui-state-default"><div class="titre-colonne">Type Sujet</div></th>
			<th align="left" class="devant ui-state-default"><div class="titre-colonne">Difficulté</div></th>
			<th align="left" class="devant ui-state-default"><div class="titre-colonne"></div></th>
			<th align="left" class="devant ui-state-default"><div class="titre-colonne"></div></th>
			<th align="left" class="devant ui-state-default"><div class="titre-colonne"></div></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="beanTypeSujetNiveau" items="${gestionFormBean.listNiveauTypeSujetPanier}">

			<tr>
				<td>${beanTypeSujetNiveau.typeSujet.langage.libelle}</td>
				<td>${beanTypeSujetNiveau.typeSujet.libelle}</td>
				<td>${beanTypeSujetNiveau.niveauQuestion.libNiveau}</td>
				<td style="text-align: left">${beanTypeSujetNiveau.typeSujet.id}</td>
				<td style="text-align: left">${beanTypeSujetNiveau.typeSujet.langage.id}</td>
				<td style="text-align: left">${beanTypeSujetNiveau.niveauQuestion.id}</td>

			</tr>
		</c:forEach>

	</tbody>
</table>
