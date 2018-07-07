<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.GrupoBean" %>
    
    <table class="table table-striped">
	<thead>
		<tr>
			<th>Nombre Grupo</th>
			<th>Numero Grupo</th>
			<th>Exclusivo</th>
			<th>Vigente</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>	
	<%
	LinkedList<GrupoBean> listado = (LinkedList<GrupoBean>)request.getAttribute("listado");
	for(GrupoBean gb: listado) {
	%>
	<tr>
		
		<td>
		    <input type="text" name="nom_grupo" value="<%= gb.getNom_grupo()%>" >
		</td>
		<td>
			 <input type="hidden" name="nro_grupo" value="<%= gb.getNro_grupo()%>">
			 <%= gb.getNro_grupo()%>
        </td>
		<td>
			 <input type="checkbox" <%=(gb.getExclusivo().toLowerCase().equals("s")?"checked":"")%> name="exclusivo">
		</td>
		
		<td> 
		     <input type="checkbox" <%=(gb.getVigente().toLowerCase().equals("s")?"checked":"")%> name="vigente">
		</td>
		<td> 
		     <a name="guardar">Guardar</a>  
		</td>
		<td> 
		     <a name="cancelar">Cancelar</a>  
		</td>
	
	</tr>
	<%		
	}	
	%>
	<tr>
		
		<td>
		    <input type="text" name="nom_grupo" value="" >
		</td>
		<td>
		    <input type="hidden" name="nro_grupo" value="">
		</td>
		<td>
			 <input type="checkbox"  name="exclusivo">
		</td>
		<td> 
		     <input type="checkbox" name="vigente">
		</td>
	    <td> 
		     <a name="guardar">Guardar</a>  
		</td>
		<td> 
		     <a name="cancelar">Cancelar</a>  
		</td>
	</tr>
	</tbody>
</table>

    