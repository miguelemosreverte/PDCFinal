package ar.edu.ubp.pdc.tags;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CritBusqTagHandler extends SimpleTagSupport {

	private String funcionJS;

	public void setfuncionJS(String funcionJS) {
		this.funcionJS = funcionJS;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		
	    out.println("<form-group>");
	    out.println("<label for=\"istring_busqueda\">Solicitante|Asunto|Texto</label>");
	    out.println("<input type=\"text\" id=\"istring_busqueda\" name=\"string_busqueda\" value=\"\">");
	    out.println("</form-group>");
	    out.println("<form-group>");
	    out.println("<label >Ordenar Por</label>");
	    out.println("<input type=\"radio\"  name=\"ordenar_por\" value=\"S\">Solicitante");
	    out.println("<input type=\"radio\"  name=\"ordenar_por\" value=\"F\" checked>Fecha" );
	    out.println("<input type=\"radio\"  name=\"ordenar_por\" value=\"T\">Nro Ticket");
	    out.println("</form-group>");
	    out.println("<button type=\"submit\" name=\"botonBuscar\" onclick=\"" +this.funcionJS +"\" class=\"btn btn-primary\">Buscar</button>");
    }
	
}
