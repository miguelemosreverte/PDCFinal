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
<div id="main_container">
	<div id="message"> </div>
	<form name="name_form" id="id_form">
		<ct:tag tipo="radio" nombre_del_elemento_html="elem_tag"/>
		<div id="result"> </div>	
	</form>
</div>
</body>
