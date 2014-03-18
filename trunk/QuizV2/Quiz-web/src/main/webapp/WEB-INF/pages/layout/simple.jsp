<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" 		prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		 	prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" 	prefix="form" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="content-language" content="fr">

		<title><tiles:getAsString name="title" /></title>
		<style>
			.errorblock {
				color: #ff0000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>

		<!-- Twitter Bootstrap CSS framework -->
		<link href="<c:url value="/resources/css/libs/bootstrap-3.0.css" />" rel="stylesheet" />

		<!-- projet -->
		<link rel="stylesheet" href="<c:url value="/resources/css/Quiz.css" />" type="text/css" />

		  <!-- All JavaScript at the bottom, except for Modernizr / Respond.
		       Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries
		       For optimal performance, use a custom Modernizr build: www.modernizr.com/download/ -->

		 <script src=" <c:url value="/resources/js/libs/modernizr-2.6.1.min.js" />"></script>
	</head>

	<body>
		<!--  -->
		<div class="container" id="container">

			<!-- HEADER -->
			<div class="row">
	       		<tiles:insertAttribute name="header" />
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<!-- CONTENU -->
				       <tiles:insertAttribute name="body" />
					</div>
				</div>
			</div>

			<hr />

			<div id="footer">
		       <tiles:insertAttribute name="footer" />
			</div>

		</div>

	    <script src="<c:url value="/resources/js/libs/jquery-1.10.2.min.js" />"></script>
	    <!-- Bootstrap -->
		 <script src="<c:url value="/resources/js/libs/bootstrap-3.0.min.js" />"></script>
	</body>
</html>
