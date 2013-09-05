<%@page language="java" %>



<%
  response.setHeader("pragma","no-cache");
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("expires","-1");
  response.setDateHeader("Expires",-1);
%>

<HTML>
<body>
Accés non autorisé.&nbsp;
<a href="<%=request.getContextPath()%>/index.jsp">
  Cliquez ici pour vous connecter à l'application.
</a>
</BODY>
</HTML>
