<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-md-12">
		<h1>
			<fmt:message key="Quiz.error.list.title" />
		</h1>
	</div>
</div>
<div>

	<table id="xssErrorTable" class="table table-bordered">
		<thead>
			<tr>
				<th><fmt:message key="Quiz.error.list.field.title" />
				</th>
				<th><fmt:message key="Quiz.error.list.message.title" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${xssErrors}">
				<c:forEach var="xssError" items="${entry.value}">
					<tr>
						<td><c:out value="${entry.key}" /></td>
						<td><c:out value="${xssError}" /></td>
					</tr>
				</c:forEach>
			</c:forEach>

		</tbody>
	</table>

</div>
