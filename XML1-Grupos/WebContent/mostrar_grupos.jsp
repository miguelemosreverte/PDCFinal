
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.*" %>
   
    <% if (request.getAttribute("grupos")!=null){
      LinkedList <GrupoRegistradoBean> grupos  = (LinkedList <GrupoRegistradoBean>)request.getAttribute("grupos");
    %>
    <table class="table" id="tabla" >
  		<tr>
    		
    		<th>nom_grupo</th>
    		<th>exclusivo</th>
    		<th>vigente</th>
    		<th></th>
    		<th></th>
  		</tr>
  		<tbody >
  			<%for(GrupoRegistradoBean g : grupos) {%>
  		<tr>
             <td>
              <input type="hidden" name="nro_grupo" value="<%=g.getNro_grupo() %>">
   			  <input type="hidden" name="cod_area" value="<%=g.getCod_area() %>">
   			  
   			  <input type="hidden" name="nom_grupo_historical" value="<%=g.getNom_grupo() %>">   			  
   			  <input type="text" name="nom_grupo" value="<%=g.getNom_grupo() %>">
             </td>
             
             
             <td>
             
   			  <input type="checkbox" name="exclusivo_historical" value="<%=g.getExclusivo()%>"
   			  		<%=((g.getExclusivo().toLowerCase().equals("s"))?"checked":"")%>
   			  		style="display:none">
   			  <input type="checkbox" name="exclusivo" value="<%=g.getExclusivo()%>" 
   			  		<%=((g.getExclusivo().toLowerCase().equals("s"))?"checked":"")%>>
   			  		
             </td>
             
             
             <td>
             
   			   <input type="checkbox" name="vigente_historical" value="<%=g.getVigente()%>"
   			   		<%=((g.getVigente().toLowerCase().equals("s"))?"checked":"")%>
   			   		style="display:none">  
   			   <input type="checkbox" name="vigente" value="<%=g.getVigente()%>" 
   			  		<%=((g.getVigente().toLowerCase().equals("s"))?"checked":"")%>>
   			  		
             </td>
             
             
             <td> <button  name="boton_cancelar" class="btn btn-danger">Cancelar</button> </td>
             
             
             <td> <button  name="boton_guardar"  class="btn btn-primary">Guardar</button> </td>
            
  		</tr>
  			<%}%>
  		<tr>
             <td>
              <input type="hidden" name="nro_grupo" value="">
   			  <input type="hidden" name="cod_area" value="">
   			  <input type="hidden" name="nom_grupo_historical" value="">   
   			  <input type="text" name="nom_grupo" value="">
             </td>
             <td>
   			   <input type="hidden" name="exclusivo_historical" value="">
   			  <input type="checkbox" name="exclusivo" value="" >
             </td>
             <td>
   			   <input type="hidden" name="vigente_historical" value="">
   			  <input type="checkbox" name="vigente" value="" >
             </td>
             <td> <button  name="boton_cancelar"  class="btn btn-danger">Cancelar</button> </td>
             <td> <button  name="boton_guardar"   class="btn btn-primary">Guardar</button> </td>
            
  		<tr>
  		</tbody>
</table>
	<%}%>

