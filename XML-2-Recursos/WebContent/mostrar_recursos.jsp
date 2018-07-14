<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.util.stream.Collectors,ar.edu.ubp.pdc.beans.*"  %>
 <%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>
<form> 
<table class="table">
  <tr>
    <th>tipo_recurso</th>
    <th>desc_recurso</th>
    <th>tipo_propietario</th>
    <th>seleccionar propietario</th>
    <th>vigente</th>
  </tr>
  
<%final LinkedList<RecursoBean> recursos = (LinkedList<RecursoBean>)request.getAttribute("recursos");%>

 <%for(RecursoBean recurso : recursos){%>
       <% String desabilitado = (recurso.getVigente().toLowerCase().equals("n")?"disabled":""); %>
  		<tr> 
  			<td>
  			<%String n = "tipo_recurso"+ recurso.getNro_recurso().toString();%>
  			<ct:tipoRecursos nombreHTML="<%=n%>" opcionTodos="N" tipoRecurso="<%=recurso.getCod_tipo_recurso()%>"/>
  			 <input type="hidden" name="tipo_recurso" value="<%=recurso.getCod_tipo_recurso()%>">  
  			</td>
  			<td> 
  			 	 <input type ="hidden" value="<%=recurso.getNro_recurso() %>" name="nro_recurso">
  			 	 <input type ="text" value="<%=recurso.getDesc_recurso() %>" name="desc_recurso">
  			</td>
  			<td>
  				 <label class="radio-inline">
  				 	<input type="radio" name="tipo_propietario<%=recurso.getNro_recurso()%>" 
  				 		   value="P" id="tipo_propietario<%=recurso.getNro_recurso()%>"
  				 		    <%=(recurso.getTipo_propietario().equals("P")?"checked":"")%>
  				 	/>
  				    Personal
  				 </label>
  				 <label class="radio-inline">
  				 	<input type="radio" name="tipo_propietario<%=recurso.getNro_recurso()%>" 
  				 		   value="A" id="tipo_propietario<%=recurso.getNro_recurso()%>"  
  				 		   <%=(recurso.getTipo_propietario().equals("A")?"checked":"")%>
  				    />
  				 	Area
  				 </label>
  	             <input type="hidden" name="tipo_propietario" value="<%=recurso.getTipo_propietario()%>"> 
  			</td>
  			<td> 
  				<div name="custom_div">
  			     <% String tipoPropietario = recurso.getTipo_propietario();
  			       if (recurso.getNro_area() != null) {%>
  						<ct:getListaPropietarios nombre_html="propietario"  tipo_propietario="<%= tipoPropietario%>" area="<%= recurso.getNro_area()%>"/>  				
  			      <%}else if (recurso.getNro_leg_personal() != null) {%>
  				        <ct:getListaPropietarios nombre_html="propietario" tipo_propietario="<%= tipoPropietario%>" personal="<%= recurso.getNro_leg_personal()%>"/>
  			      <%}%>
  			    </div>
  			</td>
  			<td>
  		         <input type="checkbox" 
  		         		name="vigente" 
  		         		id="vigente<%=recurso.getNro_recurso()%>" 
  		         		<%=(recurso.getVigente().toLowerCase().equals("s")?"checked":"") %> 
  		         />
  			</td>
  			
  		</tr>
 <%}%>
</table>
<button class="btn btn-primary" id="boton_agregar" name="boton_agregar">Agregar</button>
</form>



