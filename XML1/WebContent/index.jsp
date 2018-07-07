<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<!DOCTYPE html>
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
 <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>

	<div name="message"></div>
	<div class="main" id="imain">
	<h3>Areas</h3>
	<ct:custom_component formaDeMostrarse="lista"  nombre="cod_area"></ct:custom_component>
		<div name="resultTabla" id="iresultTabla">
		
		</div>
	</div>
	<script type="text/javascript">JGrupos.cargarTabla()</script>
</body>
