<%@page import="fr.gfi.cmg.QuizzCmg.presentation.gestion.GestionFormBean"%>
<%@ page contentType="application/json"%>
<%GestionFormBean gestionFormBean = (GestionFormBean) request.getAttribute("gestionFormBean");out.print(gestionFormBean.getJson());%>