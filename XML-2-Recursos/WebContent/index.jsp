<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap-theme.min.css">
 <script type="text/javascript" src="./js/jquery.js"></script>
 <script type="text/javascript" src="./bootstrap.3.3.7/Scripts/bootstrap.min.js"></script>
 <script type="text/javascript" src="./js/utils.js"></script>
 <script type="text/javascript" src="./js/scripts.js"></script>
 <link rel="stylesheet" type="text/css" href="./css/styles.css"/>
</head>
<body>
	<!-- 
	<ct:tipoRecursos nombreHTML="nombreHTML" opcionTodos="N" />    opcionTodos="N" NoDefault <br/> 
	<ct:tipoRecursos nombreHTML="nombreHTML" opcionTodos="S" />    opcionTodos="S" NoDefault <br/> 
	
	<ct:tipoRecursos nombreHTML="nombreHTML" opcionTodos="N" tipoRecurso="C"/> opcionTodos="N" WithDefault <br/> 
	<ct:tipoRecursos nombreHTML="nombreHTML" opcionTodos="S" tipoRecurso="C"/> opcionTodos="S" WithDefault <br/> 
	!-->
	
	<ct:tipoRecursos nombreHTML="tipoRecursos" opcionTodos="S" tipoRecurso="C"/> <br/>
	
	<ct:getListaPropietarios nombre_html="getListaPropietarios" tipo_propietario="A" area="2"/>
	
	<div id="recursos"></div>
</body>
