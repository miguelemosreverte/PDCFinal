<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


    <%if (request.getAttribute("responseFromDB")!=null && 
          request.getAttribute("responseFromDB").equals("S") ){%>
    		<img src="resources/images/icon_ok.png" />
    <%}else{%>
    		<img src="resources/images/icon_wrong.png" />
    <%}%>