<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"  import="java.util.*,ar.edu.ubp.pdc.beans.*" %>
  
  <%! 
    static String generatePrimaryKey (FechaHotelesBean fb){
		return (fb.getNro_hotel() +"_"+ fb.getFecha() +"_"+ fb.getTipo_habitacion());
		
	} 
  %>
	
  <%if(request.getAttribute("listadoFechas")!=null){
    LinkedList <FechaHotelesBean> fechasHoteles = (LinkedList <FechaHotelesBean>)request.getAttribute("listadoFechas");
    LinkedList <String> hoteles = (LinkedList <String>)request.getAttribute("listadoHoteles");
    String reservas = (String)request.getAttribute("reservas");
  %>
  
    <%= reservas %>
    <%= "Aca deberia mostrar reserva" %>
        	 
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
			<tr>
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
             		<input type="hidden" name="nro_hotel" value="<%=fh.getNro_hotel() %>"/>
	   			</td>
	   		</tr> 	
          <%}%>
       <%}%> 
   		</table>
   		</div> 	
   	<%}%>
   	</div>
 
  <%}%>
    
  