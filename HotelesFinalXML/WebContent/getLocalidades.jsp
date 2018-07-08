<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<!DOCTYPE html >

<%if(request.getParameterMap().size() > 0 && request.getParameter("provincias")!=null){%>
	<ct:Localidades codigoProvincia="<%= request.getParameter(\"provincias\") %>" nombreElementoHTML="localidades" />
<%}%>