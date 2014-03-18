<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-md-12">
		<h1>
			<fmt:message key="Quiz.error.businessException" />
			:
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<c:out value="${exceptionMessage}"></c:out>

	</div>
</div>