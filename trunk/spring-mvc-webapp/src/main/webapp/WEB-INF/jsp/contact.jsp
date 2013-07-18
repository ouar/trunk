<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ckeditor/contents.css" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
 
 
   <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootswatch.css" rel="stylesheet">
     
      
<script type="text/javascript">      
$(document).ready(function() {  $("#input").cleditor();  });  
</script>
</head>
<body>

<jsp:include page="include/menu.jsp" />
<div class="wrapper">
	<div class="container">
<textarea class="ckeditor" name="editor1"></textarea>
</div>
</div>
<jsp:include page="include/piedpage.jsp" />
</body>
</html>