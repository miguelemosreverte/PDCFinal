<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.*" %>
  
  <%if(request.getAttribute("listadoFechas")!=null){
    LinkedList <FechaHotelesBean> fechasHoteles = 
    		(LinkedList <FechaHotelesBean>)request.getAttribute("listadoFechas");
    LinkedList <String> hoteles = 
        	 (LinkedList <String>)request.getAttribute("listadoHoteles");%>
        	 
	<div class="col-md-12">
    <% for(String hotel:hoteles ){%>
    
 		<div class="col-md-6">
 		<table class="table table-bordered">
        <%= hotel %>
        	<tr>
 				<td class="col-md-2">Fecha</td> 	
 				<td class="col-md-2">Tipo </td> 	
 				<td class="col-md-2">Hab.</td> 	
 				<td class="col-md-4">Cant.</td> 	
 				<td class="col-md-2"></td> 	
	   		</tr> 		 
       <% for(FechaHotelesBean fh:fechasHoteles ){%>
       
       	
	   		
          <%if(fh.getHotel().equals(hotel)){ %>
			<tr >
 				<td>
	             <%=fh.getFecha() %>
	   			</td> 	
 				<td>
	             <%=fh.getTipo_habitacion() %>
	   			</td> 	
 				<td>
	             <%=fh.getHab_disponibles() %>
	   			</td> 	
 				<td>
	             	<input type="text"  name="cantidad_reservada" />
	   			</td> 	
 				<td>
	             	<button type="text" name="cantidad_reservada"> Reservar </button>
	   			</td> 	
	   		</tr> 	
          <%}%>
       <%}%> 
   		</table>
   		</div> 	
   	<%}%>
   	</div>
 
  <%}%>
    
  