package ar.edu.ubp.pdc.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import ar.edu.ubp.pdc.classes.Prioridad;
import ar.edu.ubp.pdc.classes.Tarea;

public class AgendaTagHandler extends BodyTagSupport {

	private static final long serialVersionUID = 3154148448049573092L;
	private LinkedList<Tarea> agenda;
	private Iterator<Tarea>   iterator;

	public AgendaTagHandler() throws JspException {
		super();

		try {
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            
            this.agenda = new LinkedList<Tarea>();
            this.agenda.add(new Tarea("Cumpleaños de Pili", f.parse("07-03-2018"), Prioridad.ALTA));
            this.agenda.add(new Tarea("Cumpleaños de Silvana", f.parse("16-05-2018"), Prioridad.ALTA));
            this.agenda.add(new Tarea("Ir a comprar regalo para Silvana...", f.parse("07-05-2018"), Prioridad.MEDIA, "Mariela"));
            this.agenda.add(new Tarea("Clase de PDC", f.parse("18-04-2018"), Prioridad.ALTA, "Mariela"));
            
            Collections.sort(this.agenda, new Comparator<Tarea>() {
                @Override
                public int compare(Tarea o1, Tarea o2) {
                    return o1.getFechaTarea().compareTo(o2.getFechaTarea());
                }
            });

            this.iterator = this.agenda.iterator();
        } 
        catch (ParseException ex) {
        	throw new JspException(ex);
        }		
	}
	
	/*
	 * Se invoca cuando el motor JSP encuentra el tag de activación pero después de haber 
	 * inicializado los atributos de la clase
	 */
	@Override
	public int doStartTag() throws JspException {
        if(this.getProximaTarea()) {
            return EVAL_BODY_BUFFERED;
        } 
        return SKIP_BODY;
	}
	
	/*
	 * Se invoca cuando el motor JSP termina de procesar el cuerpo de contenidos del tag
	 */
	@Override
	public int doAfterBody() throws JspException {
		JspWriter out = this.bodyContent.getEnclosingWriter();
	    try {
	    	this.bodyContent.writeOut(out);
		    this.bodyContent.clearBody();
		    if(this.getProximaTarea()) {
		    	return EVAL_BODY_AGAIN;
		    }
		    return SKIP_BODY;
	    } 
	    catch (IOException ex) {
	    	throw new JspException(ex);
	    }	      
	}

	/*
	 * Se invoca cuando el motor JSP finaliza el procesamiento del tag
	 */
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	
    private boolean getProximaTarea() {
        if(this.iterator.hasNext()) {
            DateFormat df    = DateFormat.getDateInstance(DateFormat.SHORT, this.pageContext.getRequest().getLocale());
            Tarea      tarea = Tarea.class.cast(this.iterator.next());
            
            this.pageContext.setAttribute("tarea",       tarea.getNomTarea());
            this.pageContext.setAttribute("fecha",       df.format(tarea.getFechaTarea()));
            this.pageContext.setAttribute("prioridad",   tarea.getPrioridad().name());
            this.pageContext.setAttribute("responsable", tarea.getNomResponsable() == null ? "" : tarea.getNomResponsable());
            return true; //EVAL_BODY_TAG
        }
        return false; //SKIP_BODY
    }
    
}
