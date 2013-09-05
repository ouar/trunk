<%@page import="fr.gfi.cmg.QuizzCmg.presentation.gestion.GestionFormBean"%>
<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ page contentType="application/json"%>


<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONException;"%>




<%

GestionFormBean gestionFormBean = (GestionFormBean) request.getAttribute("gestionFormBean");


	JSONArray listQuizzGlobale = gestionFormBean.getJsonObject();

	out.print(listQuizzGlobale);
%>