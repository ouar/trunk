<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" 		prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		 	prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" 	prefix="form" %>

<!--

	Template pour les pages standards

 -->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="content-language" content="fr">

		<title><tiles:getAsString name="title" /></title>

		<!-- CSS Twitter Bootstrap  -->
		<link href="<c:url value="/resources/css/libs/bootstrap-3.0.css" />" rel="stylesheet"  />

		<!-- CSS plugins JQuery -->
		<link href="<c:url value="/resources/css/libs/jquery.dataTables-1.9.4.css" />" rel="stylesheet" >
		<link href="<c:url value="/resources/css/libs/ui/jquery-ui-1.10.2.custom.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/libs/DT_bootstrap-3.0.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/libs/select2-3.4.2.css" />" rel="stylesheet" />
		<!-- CSS projet -->
		<link href="<c:url value="/resources/css/Quiz.css" />" rel="stylesheet" />

		  <!-- All JavaScript at the bottom, except for Modernizr / Respond.
		       Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries
		       For optimal performance, use a custom Modernizr build: www.modernizr.com/download/ -->

		 <script src="<c:url value="/resources/js/libs/modernizr-2.6.1.min.js" />"></script>
	</head>

	<body>

		<div class="container" id="container">

			<!-- HEADER -->
			<div class="row">
				<tiles:insertAttribute name="header" />
			</div>

			<div class="row">
			    <div class="col-md-3">
					<!-- MENU -->
				    <tiles:insertAttribute name="menu" />
				</div>
				<div class="col-md-9">
						<!-- Message Panel -->
					<div class="row">
						<div id="messagePanel" style="display:none;" class="alert alert-error offset4 span4"></div>
					</div>
					<div class="row">
						<!-- CONTENU -->
				       <tiles:insertAttribute name="body" />
					</div>
				</div>
			</div>


			<hr />

			<div class="footer" id="footer">
		       <tiles:insertAttribute name="footer" />
			</div>
		</div>

	     <!-- JQuery -->
	     <script src="<c:url value="/resources/js/libs/jquery-1.10.2.min.js" />"></script>
	     <!-- JQuery Widget -->
		 <script src="<c:url value="/resources/js/libs/jquery-ui-1.10.2.custom.min.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.ui.datepicker-fr.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.metadata-2.0.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/jquery.validate-1.10.1.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/additional-methods-1.10.0.min.js" />"></script>
		 <script src="<c:url value="/resources/js/libs/select2-3.4.2.js" />"></script>
		 <!-- JQuery Widget Datatables -->
		 <script src="<c:url value="/resources/js/libs/jquery.dataTables-1.9.4.min.js" />"></script>
		 <!-- PURL, parse des parametres http en js -->
		 <script src="<c:url value="/resources/js/libs/purl-2.2.1.js" />"></script>

		 <!-- Bootstrap -->
		 <script src="<c:url value="/resources/js/libs/bootstrap-3.0.min.js" />"></script>

		 <!-- DEI CCMT -->
		 <script src="<c:url value="/resources/js/libs/socle-dei-commons-2.0.js" />"></script>

		 <!-- projet -->
		 <script src="<c:url value="/resources/js/scenes/commons-Quiz.js" />"></script>

		 <script src="${pageContext.request.contextPath}/resources/js/scenes/<tiles:insertAttribute name="script-domaine" />.js"></script>
	</body>
</html>
