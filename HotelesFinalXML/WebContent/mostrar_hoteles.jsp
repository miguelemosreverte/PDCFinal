<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.*" %>
  
  <%if(request.getAttribute("listadoFechas")!=null){
    LinkedList <FechaHotelesBean> fechasHoteles = 
    		(LinkedList <FechaHotelesBean>)request.getAttribute("listadoFechas");
    LinkedList <String> hoteles = 
        	 (LinkedList <String>)request.getAttribute("listadoHoteles");%>
        	 
	<div class="col-md-12"   >
    <% for(String hotel:hoteles ){%>
    
 		<div class="col-md-6" name = "HotelNameContainer">
	    <div class="row" style="color:black"  name = "HotelName"> <%= hotel %>	</div>
 		<table class="table table-bordered">
    
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
			    <form name="row_form">
 				<td name="fecha">
	            	 <%=fh.getFecha() %>
	   			</td> 	
 				<td name="tipo_habitacion">
	             	<%=fh.getTipo_habitacion() %>
	   			</td> 	
 				<td name="habitaciones_disponibles"><%=fh.getHab_disponibles() %>
	   			</td> 	
 				<td>
	             	<input type="text" name="cantidad_reservada" />
	   			</td> 	
 				<td>
	             	<button onclick="JHoteles.guardarReserva(event);"> Reservar </button>
	   			</td>
	   			</form> 	
	   		</tr> 	
          <%}%>
       <%}%> 
   		</table>
   		</div> 	
   	<%}%>
   	</div>
 
  <%}%>
    
  