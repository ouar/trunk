<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="box col-md-12">
		<div class="box-header">
			<h2><span class="glyphicon glyphicon-edit"></span>  <span class="break" ></span> <fmt:message key="Quiz.editeur.detail.title" /></h2>
		</div>
		<div class="box-content">

			<div class="row">
				<div class="col-md-9">

					<div class="row">
						<div class="col-md-6">
							<strong><fmt:message key="Quiz.editeur.detail.nom" /></strong>
						</div>
						<div class="col-md-6">
							<c:out value="${selectedEditeur.nom}" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><fmt:message key="Quiz.editeur.detail.adresse" /></strong>
						</div>
						<div class="col-md-6">
							<c:out value="${selectedEditeur.adresse}" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><fmt:message key="Quiz.editeur.detail.codePostal" /></strong>
						</div>
						<div class="col-md-6">
							<c:out value="${selectedEditeur.ville.codePostal}" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<strong><fmt:message key="Quiz.editeur.detail.ville" /></strong>
						</div>
						<div class="col-md-6">
							<c:out value="${selectedEditeur.ville.nom}" />
						</div>
					</div>

				</div>

				<div class="col-md-3">
					<p>
						<a
							href="<c:url value="/web/dref/editeur/update"><c:param name="idEditeur" value="${selectedEditeur.id}" /></a></c:url>"
							class="btn btn-block"><fmt:message key="Quiz.editeur.detail.bouton.update" /> </a>
					</p>
					<p>
						<a
							href="<c:url value="/web/dref/editeur/delete"><c:param name="idEditeur" value="${selectedEditeur.id}" /></a></c:url>"
							class="btn btn-block"><fmt:message key="Quiz.editeur.detail.bouton.delete" /> </a>
					</p>
					<p>
						<a href="<c:url value="/web/dref/editeur/search"/>" class="btn btn-block"><fmt:message key="Quiz.editeur.detail.bouton.back" /></a>
					</p>
				</div>


			</div><!--  fin row fluid -->

		</div>	<!--  fin box content  -->
	</div>	<!--  fin box -->
</div> <!--  fin row  -->


<div class="row">
	<div class="box col-md-12">
		<div class="box-header">
			<h2><span class="glyphicon glyphicon-list"></span>  <span class="break" ></span> <fmt:message key="Quiz.editeur.detail.ouvrage.title" /></h2>
		</div>
		<div class="box-content">

			<c:if test="${not empty selectedEditeur.ouvrages}">
				<table class="table table-bordered table-striped">
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
							<c:forEach var="unOuvrage" items="${selectedEditeur.ouvrages}" >
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
			</c:if>
		</div> <!-- fin box content  -->
	</div> <!-- fin box   -->
</div><!-- fin row fluid   -->
