
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

import ar.edu.ubp.pdc.beans.AreaBean;

public class CustomComponentTagHandler extends SimpleTagSupport {

	private String nombre_del_elemento_html;
    public String getNombre_del_elemento_html() {
		return nombre_del_elemento_html;
	}
	public void setNombre_del_elemento_html(String nombre_del_elemento_html) {
		this.nombre_del_elemento_html = nombre_del_elemento_html;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	private String tipo;
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();
	    String nombre;
	    String codigo;

		try {
			ServletContext servletContext = ((PageContext)this.getJspContext()).getServletContext();
			
			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xml"));
		    LinkedList<AreaBean> listaAreas = new LinkedList<AreaBean>();
		    AreaBean a;
			NodeList areas = NodeList.class.cast(xPath.compile("/areas/area").evaluate(document, XPathConstants.NODESET));
			for(int i = 0, len = areas.getLength(); i < len; i++) {
				a = new AreaBean();
				codigo = xPath.compile("codigo").evaluate(areas.item(i), XPathConstants.STRING).toString().toLowerCase();
				nombre = xPath.compile("nombre").evaluate(areas.item(i), XPathConstants.STRING).toString().toLowerCase();
				a.setCodigo(codigo);
				a.setNombre(nombre);
				listaAreas.add(a);
				
			}
			if(this.tipo.toLowerCase().equals("radio")) {
		     for(AreaBean ar :listaAreas) {
			  out.println("<label class=\"radio-inline\">");
			  out.println("<input type=\"radio\" name=\""+this.nombre_del_elemento_html +"\"value=\""+ar.getCodigo()+ "\">" + ar.getNombre());
		      out.println("</label>");
		     }
			}
			if(this.tipo.toLowerCase().equals("lista")) {
			  out.println("<select name=\""+this.nombre_del_elemento_html+"\">");
				for(AreaBean ar :listaAreas) {
				 out.println("<option value=\""+ar.getCodigo()+"\">" + ar.getNombre() + "</option>");
				}
			  out.println("<select/>");
				
			}
		
			
		}
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
        	out.println(ex.getMessage());
		}	
		
	}
  
 }
	

