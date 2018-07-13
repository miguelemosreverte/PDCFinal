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


public class TipoRecursosTagHandler extends SimpleTagSupport {

	private String nombreHTML;
	private String opcionTodos;
	private String tipoRecurso;
	private String sanitizedTipoRecurso = "";

	public TipoRecursosTagHandler() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		JspWriter out = this.getJspContext().getOut();		
		
		LinkedList<TipoRecursoBean> recursos = new LinkedList();
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
			
			
			NodeList tipos_recursos = 
					NodeList.class.cast(xPath.compile("/tipos_recursos/tipo_recurso")
							.evaluate(document, XPathConstants.NODESET));
			TipoRecursoBean trb;
			for (int i = 0, len = tipos_recursos.getLength(); i < len; i++) {
				trb = new TipoRecursoBean();
				 trb.setNombre(xPath.compile(".").evaluate(tipos_recursos.item(i), XPathConstants.STRING).toString().toLowerCase());
				 trb.setCodigo(xPath.compile("@codigo").evaluate(tipos_recursos.item(i), XPathConstants.STRING).toString().toLowerCase());
				 recursos.add(trb);
				 
			}
			
			boolean optionTodos = this.opcionTodos.toLowerCase().equals("s");			
			if (optionTodos)
				out.println(createRadioInput(true, "", "Todos"));
			

			boolean mustSelectOneAnyway = !optionTodos && this.sanitizedTipoRecurso.equals("");
            for(TipoRecursoBean t :recursos) {
                
    			boolean isChecked = this.sanitizedTipoRecurso.toLowerCase().equals(t.getCodigo());
    			
    			
    			if (mustSelectOneAnyway) {
    				isChecked = true;
    				mustSelectOneAnyway = false;    
    			}
    			out.println(createRadioInput(isChecked && !optionTodos, t.getCodigo(), t.getNombre()));
            		
		   }
         }
	
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
        	out.println(ex.getMessage());
		}	
		
		
	}
	
	private String createRadioInput(boolean checked, String value, String label) {
		return "<input type=\"radio\"  name=\""+this.nombreHTML+"\"  value=\""+value+"\" "
				+ (checked? "checked": "") + ">" 
				+ label ;
	}
	
	
	public String getNombreHTML() {
		return nombreHTML;
	}


	public void setNombreHTML(String nombreHTML) {
		this.nombreHTML = nombreHTML;
	}


	public String getOpcionTodos() {
		return opcionTodos;
	}


	public void setOpcionTodos(String opcionTodos) {
		this.opcionTodos = opcionTodos;
	}


	public String getTipoRecurso() {
		return tipoRecurso;
	}


	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
		this.sanitizedTipoRecurso = this.tipoRecurso == null ? "" : this.tipoRecurso; 
		
	}

}
