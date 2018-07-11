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

	<div id="main">

		<h1 class="title">Reclamos</h1>
		<h3 class="title">Quiere hacer un reclamo?</h3>
		
		<div id="message"></div>
		
		<form class="form" name="reclamosForm" id="reclamosForm" action="javascript:void(0)">
		<label>Conoces tu nro de chasis?</label> 
		<input type="radio" name="conoce_chasis" value="S" checked> Si 
		<input type="radio" name="conoce_chasis" value="N" > No 
		
		<br/>
		<div class="col-md-6"> 
		    <div class="row" id="container_chasis">
		       <div class="col-md-3"> 
		        <div class="row">N de chasis *</div>
			    </div>
			    <div class="col-md-3"> 
			    <input type="text" name="nro_chasis"  value="" /> 
			    <div id="nro_chasis_validacion" ></div>
			    </div>
		    </div>
		    <div class="row" id="container_patente">
		       <div class="col-md-3" > 
		        	<div class="row">Patente</div>
			    </div>
			    <div class="col-md-3"> 
			    <input type="text" name="nro_patente"  value="" /> 
			    <div id="nro_patente_validacion" ></div>
			    </div>
		    </div>
		    <div class="row" id="container_kilometros">
		       <div class="col-md-3" > 
					<div class="row">Kilometros</div>
			    </div>
			    <div class="col-md-3"> 
			        <input type="text" name="kilometros"  value="" />
			    </div>
		    </div>
		    <div class="row">
		       <div class="col-md-3" > 
		         <div class="row">Apellido *</div>
			    </div>
			    <div class="col-md-3"> 
			     <input type="text" name="apellido" required value="" /> 
			    </div>
		    </div>
		    <div class="row">
		       <div class="col-md-3"> 
		         <div class="row">Nombre *</div>
			    </div>
			    <div class="col-md-3"> 
			    <input type="text" name="nombre" required value="" /> 
			    </div>
		    </div>
		    <div class="row">
		       <div class="col-md-3"> 
		        <div class="row">Email *	</div>
			    </div>
			    <div class="col-md-3"> 
			    <input type="email" name="email" required value="" /> 
			    </div>
		    </div>
		    <div class="row">
		       <div class="col-md-3"> 
		        <div class="row">Telefono</div>
			    </div>
			    <div class="col-md-3"> 
			    <input type="text" name="telefono"  value="" /> 
			    </div>
		    </div>
		    
		     <div class="row">
		       <div class="col-md-3"> 
		        	<div class="row">Desea ser contactado por el vendedor? *</div>
			    </div>
			    <div class="col-md-3"> 
				    <input type="radio" name="desea_ser_contactado" value="S" > Si 
					<input type="radio" name="desea_ser_contactado" value="N" checked> No 
			    </div>
		    </div>
		    
		     <div class="row">
		       <div class="col-md-7"> 
		        	<div class="row">Reclamo (4000 caracteres max.) *</div>
		        	
					<textarea rows="6" cols="" max=4000 name="reclamo" required></textarea>
			    </div>
			   
		    </div>
		    
		    
		     <div class="row" style="text-align:center">
		       <div class="col-md-7"> 
		       	<button id="boton_registrar" class="btn btn-danger" >Registrar</button>
			   </div>
		    </div>
		 </div>
		 
		</form>
	</div>
	
	<div id="aftermath">
	
	
	
	</div>
 </body>
