<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="actionUrl"
	value="${pageContext.request.contextPath}/j_spring_security_check" />

<div class="row">
	<div class="col-md-3">&nbsp;</div>
	<div class="col-md-6">

			<div class="login-box">

				<form:form class="form-horizontal" name="login_form" action="${actionUrl}" method="POST">

					<h2><fmt:message key="Quiz.authentication.title" /></h2>

				 	<fieldset>

						<div class="form-group">
							<c:if test="${not empty param.login_error}">
								<div class="errorblock">
									<fmt:message key="Quiz.authentication.error.1" /><br /> <fmt:message key="Quiz.authentication.error.2" />
									${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
								</div>
							</c:if>
						</div>

						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
								<input class='form-control' id='username' type='text' name='j_username' value='' />
							</div>
						</div>

						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
								<input class="form-control" type='password' name='j_password'>
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary"><fmt:message key="Quiz.authentication.bouton.submit" /></button>
						</div>

					</fieldset>

				</form:form>

			</div>
	</div>
	<div class="col-md-3">&nbsp;</div>
</div>
