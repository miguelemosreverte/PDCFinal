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
 					<link rel="stylesheet" type="text/css" href="./css/styles.css"/>
            	</head>
          		<body>
					<div id="message"></div>
					<form id="idForm" onsubmit="event.preventDefault(); JHoteles.buscar();">
	    			<table class="table table-bordered">
	    				<thead>
	    				 <tr>
      						<th colspan="6"> 
    							<xsl:value-of select="$titulo"/>
	    					</th>
    					</tr>	
	    				</thead>
	    				 <tbody>
	    				 	<tr>    
	    				 		<td>Provincias:</td>
	    				 		<td colspan="5">
	    				 		
										<select id="idProvincias" name="provincias" onchange="JHoteles.getLocalidades();">
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
		                    			</select>

	    				 		</td>
	    				 	</tr>
	    				 
	    				 	<tr>
	    				 	<td>Localidad:</td>
	    				 	<td colspan="5"><div id="result-localidades"></div></td>
	    				 	</tr>
	    				 
	    				 	<tr>
	    				 	<td>Fecha Desde</td>
	    				 	<td><input type="text" name="fecha-desde" ></input></td>
	    				 	<td>Fecha Hasta</td>
	    				 	<td><input type="text" name="fecha-hasta" ></input></td>	    				 	
	    				 	<td>Tipo Habitacion</td>
	    				 	<td>
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
	    				 	</td>
	    				 	</tr>
	    				 	
	    				 	<tr>
		    				 	<td colspan="6">
				    				<button> Buscar </button>
		    				 	</td>
	    				 	</tr>
	    				 </tbody>
	    			</table>
	    			</form>
	    			<div id="result-fecha-hoteles"></div>
				</body>
        </html>  
    </xsl:template>
</xsl:stylesheet>
