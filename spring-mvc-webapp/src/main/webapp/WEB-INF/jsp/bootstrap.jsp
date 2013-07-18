<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>
<html>
    <head>
       <script src="http://code.jquery.com/jquery.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>
    <script>
$(function (){
    $('.carousel').carousel({
    interval: 2000 
    });
    $("#pause").click(function() {
    $('.carousel').carousel('pause');
    $('.label').text('Mode pause').removeClass("label-info").addClass("label-warning");
    });
    $("#reprise").click(function() {
    $('.carousel').carousel('cycle');
    $('.label').text('Mode cycle').removeClass("label-warning").addClass("label-info");
    });
}); 
</script>
        <title>Bootstrap arena</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <!-- <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
         <link href="/bootstrap/css/bootstrap.css" rel="stylesheet"> -->
         <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
         <link rel="STYLESHEET"  href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css" type="text/css">
         <link rel="STYLESHEET"  href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" type="text/css">
                <!-- Font-Awesome -->
      <!-- <link href="Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
        My style -->
     
        <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" media="screen"> 
    </head>
    <body>
    <spring:htmlEscape defaultHtmlEscape="true" /> 
        <h1>Hello Bootstrap !</h1>
        <div class="navbar">
  <div class="navbar-inner">
    <div class="container">
      <ul class="nav">
        <li> <a href="#">Accueil</a> </li>
        <li> <a href="#">Liens</a> </li>
        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Témoignages <b class="caret"></b> </a>
          <ul class="dropdown-menu">
            <li><a href="#">Dompteurs</a></li>
            <li><a href="#">Zoos</a></li>
            <li><a href="#">Chasseurs</a></li>
            <li class="divider"></li>
            <li><a href="#">Autres témoignages</a></li>
          </ul>
        </li>
        <li> <a href="#">Références</a> </li>
      </ul>
     
    </div>
  </div>
</div>

        <table class="table table-bordered table-striped table-condensed">
   <caption>
      <h4>Les menaces pour les tigres</h4>
   </caption>
   <thead>
      <tr>
            <th>id</th>
            <th>Code</th>
             <th>Name</th>
      </tr>
   </thead>
  <tbody>
<c:forEach var="stock" items="${listeStock}">
<tr>
<td><c:out value="${stock.stockId}"></c:out></td>	
<td><c:out value="${stock.stockCode}"></c:out></td>
<td><c:out value="${stock.stockName}"></c:out></td>	
</tr>											
</c:forEach>
        
    </tbody>
</table>
    </body>
   <div class="span5">

  <form:form method="post"  action="ajouterStock.html"  commandName="stockForm" modelAttribute="stockForm">
  	<form:errors path="*" cssClass="errorblock" element="div" />
  		<form:input path="stockName" cssClass="input-medium" htmlEscape="true"  />  
  		<form:input path="stockCode" htmlEscape="true" cssClass="input-medium"/> 
  	<!-- 	<input type="text" class="input-medium" autofocus required placeholder="code" name="stockCode">
     <input type="text" class="input-medium" autofocus required placeholder="nom" name="stockName">  --> 
    <div class="controls"> 
    <br/>
    
      <button type="submit" class="btn btn-primary btn-small pull-right"> <i class="icon-user icon-white"></i> Ajouter</button>
    </div>
 </form:form>
</div>
    <footer>
     
    </footer>
</html>
