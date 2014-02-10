
<html>


<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<head>
<link type="text/css"
	href="<%=request.getContextPath()%>/css/formulaire-css3.css"
	media="projection, screen" rel="stylesheet">

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.7.2-dev.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui-1.8.21.custom.min.js"></script>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>


</head>
<body onload='document.accueilFormBean.j_username.focus();'>
<c:if test="${not empty param.error}">
    Login error.
    Reason ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
	<div id="BoxLogin">
		<h3>Authentification</h3>

		<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<form name='accueilFormBean' action="<c:url value='j_spring_security_check' />"
			method='POST' >

			<table>
				<tr>
					<td>Login:</td>
					<td><input type='text' name='j_username' value=''>
					</td>
				</tr>
				<tr>
					<td>Mot de pass:</td>
					<td><input type='password' name='j_password' />
					</td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" />
					</td>
				</tr>
				<tr>
					<td colspan='2'><input name="reset" type="reset" />
					</td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>