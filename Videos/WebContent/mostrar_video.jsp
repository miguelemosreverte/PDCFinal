<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.VideoBean" %>
    
  <h4><%=request.getParameter("titulo")%></h4> <h4><%=request.getParameter("cantante")%></h4>
  <iframe src="<%=request.getParameter("link")%>" width=560 height=315></iframe> 
  <br></br>
  <a onclick="JVideos.volver()">Volver</a>    