<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>

<head>
<link type="text/css" href="<%=request.getContextPath()%>/css/formulaire-css3.css" media="projection, screen" rel="stylesheet">

<script type="text/javascript"	src="<%=request.getContextPath()%>/js/jquery-1.7.2-dev.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.21.custom.min.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	if($.browser.msie && parseInt($.browser.version, 10) < 7){
		$("#BoxLogin").html('<span class="titre">Navigateur Incompatible (IE6)</span><p>-A quoi sert Internet Explorer?<br/>'+
		'-A télécharger un vrai navigateur ...<br/></p><p>'+
		'<a href="http://www.mozilla.org/fr/firefox" target="_blank" class="boutonLarge"><img src="<%=request.getContextPath()%>/images/Firefox.gif" />Get Firefox</a>'+
		'<a href="http://www.google.com/chrome" target="_blank" class="boutonLarge"><img src="<%=request.getContextPath()%>/images/chrome.gif" />Get Chrome</a>'+
		'<a href="http://www.opera.com/download/" target="_blank" class="boutonLarge"><img src="<%=request.getContextPath()%>/images/opera.png" />Get Opera</a></p>')
	}
	  
});
</script>
</head>

<body>
 <spring:htmlEscape defaultHtmlEscape="true" /> 
<div id="BoxLogin">
	
	<form:form method="post"  action="Valider" name="Valider"  commandName="accueilFormBean" modelAttribute="accueilFormBean">	
	
  		<%-- <form:errors path="*"  element="div" /> --%>
		  <span class="titre">connexion</span>		
		  <p>
		  <label>Login</label> 
		
		  
		
				<form:select path="user">
					<c:forEach var="user" items="${ListeUsers}">
						<form:option value="${user.libNom}">${user.libPrenom} ${user.libNom}</form:option>
					</c:forEach>
				</form:select>

				<br/>
		  </p>
		  <p><label>Mot de passe</label>
		  <form:password path="pass"/>		
		  <br/>
		  </p>
		  <div>
		  	<input type="submit" value="GO"/>
		  </div>
	</form:form>
	</div>
</body>
</html>
