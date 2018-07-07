package ar.edu.ubp.pdc.servlets;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.ubp.pdc.beans.TicketBean;

/**
 * Servlet implementation class TipoDocumentoServlet
 */
@WebServlet("/Tickets.jsp")
public class Tickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tickets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		ResultSet result;
		LinkedList<TicketBean> tickets;
		TicketBean ticket;
		PreparedStatement stmt;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(true);
			stmt = conn.prepareStatement("select ticket  = convert(varchar(4), t.año_ticket) + '-' + replicate('0', 5 - len(convert(varchar(5), t.nro_ticket))) + convert(varchar(5), t.nro_ticket)," + 
					"       fecha_ticket  = convert(varchar(10), t.fecha_ticket, 103) + ' ' + convert(varchar(5), t.fecha_ticket, 108), " + 
					"	   solicitante   = s.nom_solicitante," + 
					"	   asunto_ticket = t.asunto_ticket," + 
					"	   texto_ticket  = t.texto_ticket" + 
					"  from dbo.tickets t (nolock)" + 
					"       join dbo.solicitantes s (nolock)" + 
					"	     on t.nro_solicitante = s.nro_solicitante" + 
					" where (t.asunto_ticket   like '%' + isnull(ltrim(rtrim(?)), '') + '%'" + 
					"    or  t.texto_ticket    like '%' + isnull(ltrim(rtrim(?)), '') + '%'" + 
					"  	or  s.nom_solicitante like '%' + isnull(ltrim(rtrim(?)), '') + '%')" + 
					" order by case ?" + 
					"               when 'F'" + 
					"			   then convert(varchar(10), t.fecha_ticket, 112) + ' ' + convert(varchar(5), t.fecha_ticket, 108)" + 
					"			   when 'S'" + 
					"			   then s.nom_solicitante" + 
					"			   when 'T'" + 
					"			   then convert(varchar(4), t.año_ticket) + '-' + replicate('0', 5 - len(convert(varchar(5), t.nro_ticket))) + convert(varchar(5), t.nro_ticket)" + 
					"		 end");
	
			if(request.getParameter("string_busqueda").equals("")) {

				stmt.setNull(1,java.sql.Types.VARCHAR);
				stmt.setNull(2,java.sql.Types.VARCHAR);
				stmt.setNull(3,java.sql.Types.VARCHAR);
				stmt.setString(4, request.getParameter("ordenar_por"));
		
			}
			else {
				stmt.setString(1, request.getParameter("string_busqueda"));
				stmt.setString(2, request.getParameter("string_busqueda"));
				stmt.setString(3, request.getParameter("string_busqueda"));
				stmt.setString(4, request.getParameter("ordenar_por"));
			}
		

			
			result = stmt.executeQuery();
			tickets = new LinkedList<TicketBean>();

			while(result.next()) {
				ticket = new TicketBean();
                ticket.setNumero(result.getString("ticket"));
                ticket.setFecha(result.getString("fecha_ticket"));
                ticket.setSolicitante(result.getString("solicitante"));
                ticket.setAsunto(result.getString("asunto_ticket"));
                ticket.setTexto(result.getString("texto_ticket"));
				tickets.add(ticket);
			}
			stmt.close();
			conn.close();
			
			request.setAttribute("tickets", tickets);
			gotoPage("/mostrar_tickets.jsp", request, response);	
		} 
		catch (ClassNotFoundException | SQLException e) {
			response.setStatus(400);
			this.printError(e.getMessage(),request,response);
		
		}	
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn;
		CallableStatement stmt;		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=pdc", "sa", "98Thk7oo08L#F");
			conn.setAutoCommit(false);
			
			try {
				stmt = conn.prepareCall("{CALL dbo.ins_ticket(?,?,?,?)}");
				stmt.setString(1, request.getParameter("nasunto"));
				stmt.setString(2, request.getParameter("ntexto"));
				stmt.setString(3, request.getParameter("nnombre"));
				stmt.setString(4, request.getParameter("nemail"));
				
				stmt.execute();
				conn.commit();
				stmt.close();
			}
			catch(SQLException e) {
				conn.rollback();
				this.printError(e.getMessage(), request, response);
			}
			finally {
				conn.setAutoCommit(true);
				conn.close();
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			this.printError(e.getMessage(), request, response);
		}
	}
	
	private void printError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("error", error);
		this.gotoPage("/error.jsp", request, response);
	}

	private void gotoPage(String address, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
		                  dispatcher.forward(request, response);
	}
	
}


