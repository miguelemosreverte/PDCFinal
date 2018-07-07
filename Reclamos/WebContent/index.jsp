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
    <div id="finalizar"></div>
    <div id="main">
	<h3>Reclamos</h3>
	<br></br>
	<h4>¿Quiere hacer un reclamo?</h4>
	
  	<form id="form"  action="javascript:void(null)" method="post" class="col-md-12 order-md-1" onsubmit="JReclamos.registrar(event)">
       <div class=row >
       <label>¿Conoces tu numero de chasis?</label>
       <input type="radio" name="conoces" value="S" checked>Si
       <input type="radio" name="conoces" value="N" >No
       </div>
       <div class="row" name="rowdelchasis" >
       		<div class="col-md-6 mb-3">
             	<label for="ichasis">N de chasis</label>
				<input type="text" id="ichasis" name="chasis" class="form-control" required  placeholder=""  value=""  maxlength="100"><div id="resultchasis"></div>
            </div>
       </div>
       <div class="row">
       		<div class="col-md-6 mb-3"  name="rowdelapatente">
             	<label for="iapellido">Patente</label>
				<input type="text" id="ipatente" name="patente" class="form-control"   placeholder="" value=""  maxlength="100"><div id="resultpatente"></div>
            </div>
       </div>
         <div class="row">
       		<div class="col-md-6 mb-3" name="rowdelkm">
             	<label for="ikm">Kilometros</label>
				<input type="text" id="ikm" name="km" class="form-control"   placeholder="" value=""  maxlength="100">
            </div>
       </div>
         <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="iapellido">Apellido</label>
				<input type="text" id="ikm" name="apellido" class="form-control"  required placeholder="" value=""  maxlength="100">
            </div>
       </div>
          <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="inombre">Nombre</label>
				<input type="text" id="inombre" name="nombre" class="form-control"  required placeholder="" value=""  maxlength="100">
            </div>
       </div>
          <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="iemail">Email</label>
				<input type="email" id="iemail" name="email" class="form-control"  required placeholder="" value=""  maxlength="100">
            </div>
       </div>
          <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="itelefono">Telefono</label>
				<input type="text" id="itelefono" name="telefono" class="form-control"   placeholder="" value=""  maxlength="100">
            </div>
       </div>
     
            <div class=row>
       			<label>¿Desea ser contactado por un vendedor?</label>
       			<input type="radio" name="contactar" value="S" checked>Si
      			<input type="radio" name="contactar" value="N" >No
       		</div>
       
        <div class="row">
       		<div class="col-md-6 mb-3">
             	<label for="ireclamo">Reclamo (4000 caracteres max)</label>
				<input type="text" id="ireclamo" name="reclamo" class="form-control" required  placeholder="" value=""  maxlength="4000">
            </div>
       </div>
   	 	<div class="row">
			<div class="col-lg-12">
				<button type="submit" name="registrar" class="btn btn-primary">Registrar</button>
			</div>
    	</div>
	</form>
	</div>
	
</body>
