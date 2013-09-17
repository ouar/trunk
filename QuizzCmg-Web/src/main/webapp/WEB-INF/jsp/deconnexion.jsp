<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="CONNECTE" class="fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.User" scope="session" />

<c:if test="${CONNECTE != null}"> 
	<c:remove var="CONNECTE" scope="session"/>
	<%response.sendRedirect(request.getContextPath());%>	

</c:if>