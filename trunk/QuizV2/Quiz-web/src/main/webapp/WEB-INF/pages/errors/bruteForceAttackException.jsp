<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="row">
	<div class="col-md-12">
		<h1>
			<fmt:message key="Quiz.error.bfaException" />
			:
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<c:out value="${exception.message}"></c:out>

	</div>
</div>
<div class="control-group">
	<div class="errorblock">
		<fmt:message key="Quiz.authentication.error.1" /><br /> <fmt:message key="Quiz.authentication.error.2" />
		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</div>
