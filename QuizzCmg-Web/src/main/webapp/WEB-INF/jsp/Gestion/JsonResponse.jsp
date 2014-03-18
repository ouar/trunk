<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="fr.gfi.cmg.QuizzCmg.presentation.beans.GestionFormBean"%>
<%GestionFormBean gestionFormBean = (GestionFormBean) request.getAttribute("gestionFormBean");out.print(gestionFormBean.getJson());%>