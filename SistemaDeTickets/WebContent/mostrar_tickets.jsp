<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.LinkedList,ar.edu.ubp.pdc.beans.TicketBean" %>
    
    <table class="table table-striped">
	<thead>

		<tr>
			<th>Ticket N°</th>
			<th>Fecha</th>
			<th>Solicitante</th>
			<th>Asunto</th>
			<th>Texto</th>
		</tr>
	</thead>
	<tbody>	
	<%
	LinkedList<TicketBean> tickets = (LinkedList<TicketBean>)request.getAttribute("tickets");
	for(TicketBean t: tickets) {
	%>
	<tr>
		<td><%= t.getNumero()%></td>
		<td><%= t.getFecha() %></td>
		<td><%= t.getSolicitante() %></td>
		<td><%= t.getAsunto() %></td>
		<td><%= t.getTexto() %></td>
	</tr>
	<%		
	}	
	%>
	</tbody>
</table>
    