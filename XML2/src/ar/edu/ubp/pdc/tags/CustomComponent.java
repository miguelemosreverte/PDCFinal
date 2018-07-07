package ar.edu.ubp.pdc.tags;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ar.edu.ubp.pdc.beans.TipoRecursoBean;

public class CustomComponent extends SimpleTagSupport {

  public String getNombreDelElementoHtml() {
		return nombreDelElementoHtml;
	}

	public void setNombreDelElementoHtml(String nombreDelElementoHtml) {
		this.nombreDelElementoHtml = nombreDelElementoHtml;
	}

	public String getIncluirOpcionTodos() {
		return incluirOpcionTodos;
	}

	public void setIncluirOpcionTodos(String incluirOpcionTodos) {
		this.incluirOpcionTodos = incluirOpcionTodos;
	}

	public String getTipoDeRecurso() {
		return tipoDeRecurso;
	}

	public void setTipoDeRecurso(String tipoDeRecurso) {
		this.tipoDeRecurso = tipoDeRecurso;
	}

  private String nombreDelElementoHtml="";
  private String incluirOpcionTodos = "N";
  private String tipoDeRecurso ="";
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
		try {
			ServletContext servletContext = ((PageContext)this.getJspContext()).getServletContext();
			
			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(servletContext.getResourceAsStream("/WEB-INF/xml/tipos_recursos.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(servletContext.getResourceAsStream("/WEB-INF/xml/tipos_recursos.xml"));
			
			
			
			NodeList tipos_recursos = NodeList.class.cast(xPath.compile("/tipos_recursos/tipo_recurso").evaluate(document, XPathConstants.NODESET));
			LinkedList<TipoRecursoBean> listadoRecursos= new LinkedList<TipoRecursoBean>();
			for (int i = 0, len = tipos_recursos.getLength(); i < len; i++) {
				TipoRecursoBean recurso = new TipoRecursoBean();
				recurso.setNom(xPath.compile(".").evaluate(tipos_recursos.item(i), XPathConstants.STRING).toString().toLowerCase());
				recurso.setCod(xPath.compile("./@codigo").evaluate(tipos_recursos.item(i), XPathConstants.STRING).toString().toLowerCase());
			    listadoRecursos.add(recurso);
			}
			if(incluirOpcionTodos.equals("S")) {
				out.println("<label class=\"radio-inline\">");
				out.println("<input type=\"radio\" value=\"\""+((this.tipoDeRecurso.equals(""))?"checked":"")+" name=\""+this.nombreDelElementoHtml+"\">Todos");
				out.println("</label>");
			}
			if(this.tipoDeRecurso.equals("")) {
				for(TipoRecursoBean r :  listadoRecursos) {
					out.println("<label class=\"radio-inline\">");
					out.println("<input type=\"radio\" value=\""+ r.getCod() +"\" name=\""+this.nombreDelElementoHtml+"\">" + r.getNom());
					out.println("</label>");
				}
			}
			else {
				for(TipoRecursoBean r :  listadoRecursos) {
					out.println("<label class=\"radio-inline\">");
					out.println("<input type=\"radio\"" + ((r.getCod().equals(this.tipoDeRecurso))?"checked ":"") + "value=\""+ r.getCod() +"\" name=\""+this.nombreDelElementoHtml+"\">" + r.getNom());
					out.println("</label>");
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException | XPathExpressionException ex) {
        	out.println(ex.getMessage());
		}
    }
	
}
