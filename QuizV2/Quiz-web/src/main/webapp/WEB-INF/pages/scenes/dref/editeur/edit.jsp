<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" 	prefix="form" %>

   <c:choose>
        <c:when test="${type eq 'create'}"><c:set var="actionUrl" value="${pageContext.request.contextPath}/web/dref/editeur/create" /></c:when>
        <c:otherwise>
		   <c:choose>
		        <c:when test="${type eq 'update'}"><c:set var="actionUrl" value="${pageContext.request.contextPath}/web/dref/editeur/update" /></c:when>
	    	    <c:otherwise>
        			<c:set var="actionUrl" value="${pageContext.request.contextPath}/web/dref/editeur/create" />
    	    	</c:otherwise>
		    </c:choose>
        </c:otherwise>
    </c:choose>

	<%-- URL d'autocompl�tion construite avec c:url afin de positionner le token CSRF --%>
	<c:url var="suggestTypeUrl" value="/web/rest/ville/search" />

	<%-- cette URL est stock�e comme attribut HTML5 data-* sur un champ cach� afin qu'elle puisse �tre exploit�e cot� client javascript --%>
	<input type="hidden" id="villeSuggestURL" data-suggest-url="${suggestTypeUrl}"/>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-header">
				<h2><span class="glyphicon glyphicon-edit"></span>  <span class="break" ></span> <fmt:message key="Quiz.editeur.edit.title" /></h2>
			</div>
			<div class="box-content">

				<form:form class="form-validate form-horizontal" modelAttribute="editeurForm" action="${actionUrl}" method="POST">

					<fieldset>

						<form:input type="hidden" path="id"/>
						<form:input type="hidden" path="version"/>

						<div class="form-group">
							<label class="col-md-3 control-label" for="name"><fmt:message key="Quiz.editeur.edit.nom" /> </label>
							<form:input cssClass="col-md-6" data-rule="{size:[2, 126]}" path="nom" />
							<span class="col-md-3 help-inline error"><form:errors path="nom" /></span>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="adresse"><fmt:message key="Quiz.editeur.edit.adresse" /></label>
							<form:input cssClass="col-md-6" path="adresse" />
							<span class="col-md-3 help-inline error"><form:errors path="adresse" /></span>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="nomVille"><fmt:message key="Quiz.editeur.edit.ville" /></label>
							<form:input  cssClass="col-md-6" id="ville" path="nomVille" />
							<span class="col-md-3 help-inline error"><form:errors path="nomVille" /></span>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label" for="codePostal"><fmt:message key="Quiz.editeur.edit.codePostal" /></label>
							<form:input  cssClass="col-md-6" id="codePostal" data-rule="{codePostal:true}" path="codePostal" />
							<span class="col-md-3 help-inline error"><form:errors path="codePostal" /></span>
						</div>


						<div class="form-actions">
							<button type="submit" class="btn btn-primary"><fmt:message key="Quiz.editeur.edit.bouton.submit" /></button>
							<a href="<c:url value="/web/dref/editeur/search"/>" class="btn btn-default"><fmt:message key="Quiz.editeur.edit.bouton.back" /></a>
						</div>

					</fieldset>

				</form:form>
			</div>

		</div>
	</div>

	<div class="row">
		<div class="box col-md-12">
			<c:if  test="${not empty editeurForm.ouvrages}">
				<div class="box-header">
					<h2><fmt:message key="Quiz.editeur.edit.ouvrage.title" /></h2>
				</div>
				<div class="box-content">
					<table class="table table-bordered table-striped" >
						<caption></caption>
					 	<thead>
							<tr>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.titre" /></th>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.dateParution" /></th>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.isbn" /></th>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.prix" /></th>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.nbPages" /></th>
								<th><fmt:message key="Quiz.editeur.edit.ouvrage.stock" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="unOuvrage" items="${editeurForm.ouvrages}" >
								<tr>
									<td>
										<c:out value="${unOuvrage.titre}" />
									</td>
									<td>
										<c:out value="${unOuvrage.dateParution}" />
									</td>
									<td>
										<c:out value="${unOuvrage.isbn}" />
									</td>
									<td>
										<c:out value="${unOuvrage.prix}" /> (&euro;)
									</td>
									<td>
										<c:out value="${unOuvrage.nbPages}" />
									</td>
									<td>
										<c:out value="${unOuvrage.stock}" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
 			</c:if>
		</div>
	</div>