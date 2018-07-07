<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>

<%if(request.getParameter("cod")=="") { %> 
	    <h1>No se ha seleccionado ninguna carrera</h1>
<%} else {%>
<table class="table table-striped ">
	    <colgroup>
	        <col width="50px">
	        <col width="50px">
	        <col width="50px">
	
	    </colgroup>
	    <thead>
	        <tr>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>Legajo</th>
	        </tr>
	    </thead>
	    <tbody>		
	    
	    <ct:CustomTabla cod="<%=request.getParameter(\"cod\")%>">
	            <tr>
	                <td><%= nombre %>  </td>
	                <td><%= apellido%> </td>
	                <td><%= legajo %>  </td>
	            </tr>                    
	    </ct:CustomTabla>
	    
	    <%}%>
	        
	    </tbody>
	</table>      