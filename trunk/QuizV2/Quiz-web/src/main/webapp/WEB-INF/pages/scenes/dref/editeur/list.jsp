<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" 	prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="hasRole('Administrateur')">

	<div class="row">
		<div class="col-md-12">
			<h2 class="pgtitre"><fmt:message key="Quiz.editeur.list.title" /></h2>
		</div>
	</div>

	<!-- bloc recherche -->
	<div class="row">
		<div class="box col-md-12">
			<div class="box-header">
				<h2><span class="glyphicon glyphicon-search"></span> <span class="break" ></span> <fmt:message key="Quiz.editeur.list.serach.title" /></h2>
			</div>
			<div class="box-content">

				<form:form cssClass="form-horizontal" modelAttribute="criteresRechercheEditeur" action="${pageContext.request.contextPath}/web/dref/editeur/search" method="POST">

					<fieldset>

						<div class="form-group">
							<label class="col-md-3 control-label"  for="name"><fmt:message key="Quiz.editeur.list.serach.criteria.nom" /> </label>
							<form:input  cssClass="col-md-6" path="nom" />
							<span class="col-md-3 help-inline error"><form:errors path="nom" /></span>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary"><fmt:message key="Quiz.editeur.list.serach.criteria.bouton.submit" /></button>
						</div>

					</fieldset>

				</form:form>

			</div>
		</div>
	</div>

	<!-- resultat recherche -->

	<div class="row">
		<div class="box col-md-12">
			<div class="box-header">
				<h2><span class="glyphicon glyphicon-list"></span>  <span class="break" ></span> <fmt:message key="Quiz.editeur.list.serach.result.title" /></h2>
			</div>
			<div class="box-content">

				<c:choose>
					<c:when test="${empty listEditeurs}">
						<div class="errormessage warning"><fmt:message key="Quiz.editeur.list.serach.result.empty" /></div>
					</c:when>
					<c:otherwise>
						<table id="tableEditeur" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th><fmt:message key="Quiz.editeur.list.serach.result.nom" /></th>
									<th><fmt:message key="Quiz.editeur.list.serach.result.adresse" /></th>
									<th><fmt:message key="Quiz.editeur.list.serach.result.codePostal" /></th>
									<th><fmt:message key="Quiz.editeur.list.serach.result.ville" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="unEditeur" items="${listEditeurs}">
									<tr>
										<td><a
											href="<c:url value="/web/dref/editeur/show?idEditeur=${unEditeur.id}"/>"><c:out
													value="${unEditeur.nom}" /> </a>
										</td>
										<td><c:out value="${unEditeur.adresse}" />
										</td>
										<td><c:out value="${unEditeur.ville.codePostal}" />
										</td>
										<td><c:out value="${unEditeur.ville.nom}" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>

			</div><!-- fin box content -->
		</div><!-- fin box  -->
	</div>

</sec:authorize>
