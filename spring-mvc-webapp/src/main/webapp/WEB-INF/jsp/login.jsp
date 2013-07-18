<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Connection</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="http://twitter.github.io/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
     <link rel="shortcut icon" href="http://twitter.github.io/bootstrap/assets/ico/favicon.png">
  </head>

  <body>

    <div class="container">

     <form:form method="post"  action="acceuil.html"  cssClass="form-signin" commandName="test">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input class="input-block-level" placeholder="Email address" type="text">
        <input class="input-block-level" placeholder="Password" type="password">
        <label class="checkbox">
          <input value="remember-me" type="checkbox"> Remember me
        </label>
        <button class="btn btn-large btn-primary" type="submit">Sign in</button>
     </form:form>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="http://code.jquery.com/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-transition.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-alert.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-modal.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-dropdown.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-scrollspy.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-tab.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-tooltip.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-popover.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-button.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-collapse.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-carousel.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap-typeahead.js"></script>

  

</body></html>