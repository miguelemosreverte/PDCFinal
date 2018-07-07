<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" 
          		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:x="http://www.w3schools.com" >
<xsl:output method="html"/>
<xsl:param  name="titulo" />
<xsl:template match="/">
        <html>
            	<head>
                	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
					<title>HotelesFinalXML</title>
 					<link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap.min.css"></link>
 					<link rel="stylesheet" type="text/css" href="./bootstrap.3.3.7/content/bootstrap-theme.min.css"></link>
 					<script type="text/javascript" src="./js/jquery.js"></script>
 					<script type="text/javascript" src="./bootstrap.3.3.7/Scripts/bootstrap.min.js"></script>
 					<script type="text/javascript" src="./js/utils.js"></script>
 					<script type="text/javascript" src="./js/scripts.js"></script>
 					<link rel="stylesheet" type="text/css" href="./css/style.css"/>
            	</head>
          		<body>
					<div id="message"></div>
	    			<div class="col-md-12" id="idContexto" >
	    				<div class="row"><xsl:value-of select="$titulo"/></div>
	    				<div class="row">
	    					<div class="col-md-2" >Provincia: </div>
	    					<div class="col-md-10" >
	    					    <xsl:element name="select">
	    					        <xsl:attribute name="id"> idProvincias </xsl:attribute>	
	    					         <xsl:attribute name="name"> provincias </xsl:attribute>	
	    					         <xsl:attribute name="onchange"> JHoteles.getLocalidades(this); </xsl:attribute>	     					       
	    					   	    <xsl:element name="option"> 
	    					    	    <xsl:attribute name="value"></xsl:attribute>
	    					    	    <xsl:attribute name="selected">selected</xsl:attribute>
	    					    		Debe seleccionar una provincia
	    					    	</xsl:element> 
									<xsl:for-each select="x:hoteles_config/x:provincias/x:provincia">
                    					<xsl:element name="option"> 
                    						<xsl:attribute name="value"> 
                    							<xsl:value-of select="@codigo"/>  
                    						</xsl:attribute>
                    						<xsl:value-of select="."></xsl:value-of>
                    					</xsl:element>
                    				</xsl:for-each>
                    			</xsl:element>
                    			
                    		</div>
	    				</div>
	    				<div class="row">
	    					<div class="col-md-2" >Localidad:</div>
	    					<div class="col-md-10" ><div id="result"> </div> </div>
	    				</div>
	    				<div class="row">
	    					<div class="col-md-2" >Fecha Desde: </div>
	    	    			<div class="col-md-2" ><input type="text" name="fecha-desde" ></input>
	    	    				 <br></br> 
	    	    				 <label>Ej: 16/05/2016</label></div>
	    	   				<div class="col-md-2" >Fecha Hasta: </div>
	    	    			<div class="col-md-2" ><input type="text" name="fecha-hasta" ></input>
	    	    				<br></br> 
	    	    				<label>Ej: 14/10/2016</label></div>
	    	    			<div class="col-md-2" >Tipo de habitacion: </div>
	    	    			<div class="col-md-2" >
								<select name="tipos_habitaciones"> 
								<xsl:for-each select="x:hoteles_config/x:tipos_habitaciones/x:tipo">
                    					<xsl:element name="option"> 
                    						
                    						<xsl:attribute name="value"> 
                    							<xsl:value-of select="@codigo"/>
                    						</xsl:attribute>
                    						<xsl:if test="@codigo = 'D'">
                    							<xsl:attribute name="selected">selected</xsl:attribute>
                    						</xsl:if>
                    						<xsl:value-of select="."></xsl:value-of>
                    					</xsl:element>
                    				</xsl:for-each>	
                    			</select>
							</div>
	    				</div>
	    				<div class="row">
	    	    			<button class="btn btn-primary" onClick="JHoteles.buscar()"> Buscar </button>
	    				</div>
	    			</div>
				</body>
        </html>  
    </xsl:template>
</xsl:stylesheet>