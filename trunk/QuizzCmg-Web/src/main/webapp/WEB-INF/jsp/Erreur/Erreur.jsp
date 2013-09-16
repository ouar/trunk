<%@page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<HTML>
  <head>
	  <link rel="stylesheet" href="<util:WebPath/>/css/style.css">
    <title>Erreur Grave</title>
  </head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="2">

<br>

 
<SPAN style="color:red">--- ERREUR TECHNIQUE / FONCTIONNELLE ---</SPAN>
<BR>
<DIV style="color:red;font:16;font-weight:bolder;width:100%">
	ERREUR  - Veuillez contacter le support technique
</DIV>

<TABLE width=100% class="titre1" style="border:1px gray solid;color:black;font:14px">
	<COL  width=25% class="titre1">
	<COL>
	<TBODY>

		
	</TBODY>
</TABLE>

<BR>
<SPAN class="titre1">Message d'erreur à l'intention du technicien</SPAN>
<BR>
<c:out value="${message}"></c:out>
</DIV>
		

</BODY>
</HTML>