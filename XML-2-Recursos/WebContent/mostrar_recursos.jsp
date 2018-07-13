<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.util.stream.Collectors,ar.edu.ubp.pdc.beans.*"  %>
 <%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>

<table class="table">
  <tr>
    <th>tipo_recurso</th>
    <th>desc_recurso</th>
    <th>tipo_propietario</th>
    <th>seleccionar propietario</th>
    <th>vigente</th>
  </tr>
  
<%final LinkedList<RecursoBean> recursos = (LinkedList<RecursoBean>)request.getAttribute("recursos");%>
<%! int i = 0; %>
 <%for(RecursoBean recurso : recursos){%>
  		<tr> 
  			<td>
  			    <input  type ="hidden" value="<%= recurso.getCod_tipo_recurso() %>" name="cod_tipo_recurso">
  			    <%if(recurso.getCod_tipo_recurso().toLowerCase().equals("c")){ %>
  			     <label>Computadora</label>
  			    <%}else if(recurso.getCod_tipo_recurso().toLowerCase().equals("i")){%>
  			      <label>Impresora</label>
  			    <%}%>
  			</td>
  			<td> 
  			 	 <input type ="hidden" value="<%= recurso.getNro_recurso() %>" name="nro_recurso">
  			 	 <input type ="text" value="<%= recurso.getDesc_recurso() %>" name="desc_recurso">
  				 
  			</td>
  			<td>
  			 <%if(recurso.getTipo_propietario().toLowerCase().equals("p")){%>
  				 <label class="radio-inline"><input type="radio" name="tipo_propietario<%=i%>" value="p" checked >Personal</label>
  				 <label class="radio-inline"><input type="radio" name="tipo_propietario<%=i%>"value="a">Area</label>
  		     <%}else if(recurso.getTipo_propietario().toLowerCase().equals("a")){%>
  		         <label class="radio-inline"><input type="radio" name="tipo_propietario<%=i%>"  value="p">Personal</label>
  				 <label class="radio-inline"><input type="radio" name="tipo_propietario<%=i%> " value="a"checked >Area</label>
  		     <%}%>
  			</td>
  			<td> 
  				<div name="custom_div">
  			    <% String tipoPropietario = recurso.getTipo_propietario();
  			       String area = recurso.getNro_area();
  			       String personal = recurso.getNro_leg_personal();
  			       if (area != null) {%>
  						<ct:getListaPropietarios nombre_html="getListaPropietarios" tipo_propietario="<%= tipoPropietario%>" area="<%= area%>"/>  				
  			      <%}else if (personal != null) {%>
  				        <ct:getListaPropietarios nombre_html="getListaPropietarios" tipo_propietario="<%= tipoPropietario%>" personal="<%= personal%>"/>
  			      <%}%>
  			    </div>
  			</td>
  			<td>
  			<%if(recurso.getVigente().toLowerCase().equals("s")){ %>
  		        <input type="checkbox" name="vigente" checked />
  			<%}else{%>
  			    <input type="checkbox" name="vigente"/>
  			<%}%>
  			</td>
  			<% i++; %>
  		</tr>
 <%}%>
</table>