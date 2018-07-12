<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.util.stream.Collectors,ar.edu.ubp.pdc.beans.*"  %>
 <%@ taglib uri="/WEB-INF/tld/customs.tld" prefix="ct" %>


<table class="table">
  <tr>
    <th>desc_recurso</th>
    <th>cod_tipo_recurso</th>
    <th>tipo_propietario</th>
    <th>seleccionar propietario</th>
    <th>vigente</th>
    <th>nro_recurso</th>
  </tr>
  
<%

final ArrayList<RecursoBean> recursos = (ArrayList<RecursoBean>)request.getAttribute("recursos");

%>

 <%		for(RecursoBean recurso : recursos){%>
  			<tr> 
  			
  			<td><%= recurso.getDesc_recurso() %></td>
  			
  			<td><%= recurso.getCod_tipo_recurso() %></td>
  			
  			<td><%= recurso.getTipo_propietario() %></td>
  			<td>
  			
  			<% 
  			
  			String tipoPropietario = recurso.getTipo_propietario();
  			String area = recurso.getNro_area();
  			String personal = recurso.getNro_leg_personal();

  			 if (area != null) {%>
  				

	<ct:getListaPropietarios nombre_html="getListaPropietarios" tipo_propietario="<%= tipoPropietario%>" area="<%= area%>"/>
	
  				
  				
  			 <%}else if (personal != null) {%>
  				
  				
	<ct:getListaPropietarios nombre_html="getListaPropietarios" tipo_propietario="<%= tipoPropietario%>" personal="<%= personal%>"/>
  			
  			 <%}%>
  			 
  			</td>
  			
  			<td><%= recurso.getVigente() %></td>
  			
  			<td><%= recurso.getNro_recurso() %></td>
  			
  			</tr>
 <% 		}%>
  	

  <tr>

  
    <td>Row 1: Col 1</td>
    <td>Row 1: Col 2</td>
  </tr>
</table>