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
 <link rel="stylesheet" type="text/css" href="./css/style.css"/>
</head>
<body>

<div id="message"></div>

<form action="javascript:void(null)" name="crit_busq" method="get">
<ct:crit_busq funcionJS="JTickets.buscar()"></ct:crit_busq>
</form>
	
<script type="text/javascript">
JTickets.buscar();
</script>	

<div id="result"></div>

<div class="row"></div>
  	<form name="regform" action="javascript:void(null)" method="post" class="col-md-12 order-md-1" onsubmit="JTickets.registrar(event)">
       <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="isolicitante">Solicitante</label>
				<input type="text" id="isolicitante" name="nnombre" class="form-control" required  placeholder="Ingrese nombre del solicitante" value=""  maxlength="100">
            </div>
            <div class="col-md-6 mb-3">
             	<label for="iemail">Email</label>
				<input type="email" id="iemail" name="nemail" class="form-control" required  placeholder="Ingrese email del solicitante" value=""  maxlength="100">
            </div>
             <div class="col-md-6 mb-3">
             	<label for="iasunto">Asunto</label>
				<input type="text" id="iasunto" name="nasunto" class="form-control" required  placeholder="Ingrese un asunto" value=""  maxlength="100">
            </div>
       </div>
       <div class="row">
       		<div class="col-md-6 mb-3">
       		  <label for="itexto">Texto</label>
				<input type="textarea" id="itexto" name="ntexto" class="form-control" required  placeholder="" value=""  maxlength="100">
            </div>
       </div>
   	 	<div class="row">
			<div class="col-lg-12">
				<button type="submit" name="boton1" class="btn btn-primary">Registrar</button>
				<button type="reset"  name="boton2" class="btn btn-danger">Cancelar</button>

			</div>
    	</div>
	</form>



</body>
