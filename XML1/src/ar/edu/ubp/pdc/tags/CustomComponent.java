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
public class CustomComponent extends SimpleTagSupport {

	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormaDeMostrarse() {
		return formaDeMostrarse;
	}
	public void setFormaDeMostrarse(String formaDeMostrarse) {
		this.formaDeMostrarse = formaDeMostrarse;
	}
	private String formaDeMostrarse;
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
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(servletContext.getResourceAsStream("/WEB-INF/xml/areas.xml"));
			
			NodeList codigos = NodeList.class.cast(xPath.compile("/areas/area").evaluate(document, XPathConstants.NODESET));
			LinkedList<AreaBean> listaAreas = new LinkedList<AreaBean>();
			String codigo = "";
			for (int i = 0, len = codigos.getLength(); i < len; i++) {
				AreaBean area = new AreaBean();
				area.setNombre(xPath.compile("nombre").evaluate(codigos.item(i), XPathConstants.STRING).toString().toLowerCase());
				area.setCodigo(xPath.compile("codigo").evaluate(codigos.item(i), XPathConstants.STRING).toString().toLowerCase());
				listaAreas.add(area);
			}			
			if(this.formaDeMostrarse.toLowerCase().equals("radio")) {
				for(AreaBean a :  listaAreas) {
					out.println("<label class=\"radio-inline\">");
					out.println("<input type=\"radio\" value=\""+ a.getCodigo() +"\" name=\""+this.nombre+"\">" + a.getNombre());
					out.println("</label>");
				}
			}
			if(this.formaDeMostrarse.toLowerCase().equals("lista")) {
				out.println("<select name=\""+ this.nombre + "\">");
				for(AreaBean a :  listaAreas) {
					out.println("<option name=\""+ this.nombre +"\" value=\"" + a.getCodigo() + "\" >" + a.getNombre() + "</option>");
				}
				out.println("</select>");
			}
					
		}
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
        	out.println(ex.getMessage());
		}
    }
	
}
