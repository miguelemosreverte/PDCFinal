<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<!DOCTYPE html >

<%if(request.getParameterMap().size() > 0 && request.getParameter("codigoProvincia")!=null){%>
	<ct:Localidades 
	codigoProvincia="<%= request.getParameter(\"codigoProvincia\") %>" 
	
	nombreElementoHTML="localidades" />
<%}%>