<%@page language="java" %>



<%
  response.setHeader("pragma","no-cache");
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("expires","-1");
  response.setDateHeader("Expires",-1);
%>

<HTML>
<body>
Acc�s non autoris�.&nbsp;
<a href="<%=request.getContextPath()%>/index.jsp">
  Cliquez ici pour vous connecter � l'application.
</a>
</BODY>
</HTML>
