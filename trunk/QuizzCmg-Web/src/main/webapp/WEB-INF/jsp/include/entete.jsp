<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="bandeau">
<span style="float:left;padding-left:20px">Bonjour <c:out value="${sessionScope['CONNECTE'].libPrenom}"/></span>
<a class="submit" href="<%=request.getContextPath()%>/QuizzCmg-Web/deconnexion" style="float:right">Déconnexion</a>
</div>
