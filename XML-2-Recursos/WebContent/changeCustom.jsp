<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>

<%if(request.getParameter("tipo_propietario")!=null){
      String t = request.getParameter("tipo_propietario");%> 
      <ct:getListaPropietarios nombre_html="propietario"  tipo_propietario="<%=t%>"/>
<%}%>