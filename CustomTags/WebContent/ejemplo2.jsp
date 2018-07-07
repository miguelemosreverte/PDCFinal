<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Ejemplo 2</title>
	<link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>
	<%if(request.getParameterMap().size() == 0) { %>
		<form action="./ejemplo2.jsp" method="post">
			<table>
				<tr>
					<th colspan="2" align="left">Info</th>
				</tr>	
				<tr>
					<th>Apellido:</th>
					<td><input type="text" name="apellido" value="" maxlength="40" size="30"/></td>
				</tr>
				<tr>
					<th>Nombre:</th>
					<td><input type="text" name="nombre" value="" maxlength="255" size="30"/></td>
				</tr>
				<tr>
					<th colspan="2" align="right"><input type="submit" value="Mostrar mensaje para..."/></th>
				</tr>	
			</table>
		</form>
		<br/>
		<a href="./index.html">Volver al index principal</a>
	<%} 
	else {%>
		<h3><ct:mensaje nombre="<%= request.getParameter(\"nombre\") %>" apellido="<%= request.getParameter(\"apellido\") %>" /></h3>
		<a href="./ejemplo2.jsp">Volver</a>
	<%} %>			
</body>
</html>