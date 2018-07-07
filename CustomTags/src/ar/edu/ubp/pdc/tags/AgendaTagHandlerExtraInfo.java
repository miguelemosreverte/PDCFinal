package ar.edu.ubp.pdc.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class AgendaTagHandlerExtraInfo extends TagExtraInfo {

	/*
	 * El constructor de la clase VariableInfo recibe cuatro parámetros: 
	 * Nombre de la variable 
	 * Tipo de dato de la variable 
	 * Bandera que indica si la variable debería ser creada o no por el contenedor 
	 * Ámbito de la variable dentro de la página JSP:
	 *   - NESTED: la variable vale sólo en el cuerpo del tag
	 *   - AT_BEGIN: la variable vale en cualquier lugar del JSP luego de haber encontrado el tag de activación 
	 *   - AT_END: la variable puede ser usada en cualquier parte
	 */
	@Override
	public VariableInfo[] getVariableInfo(TagData data) {
        VariableInfo v1  = new VariableInfo("tarea",       "String", true, VariableInfo.NESTED);
        VariableInfo v2  = new VariableInfo("fecha",       "String", true, VariableInfo.NESTED);
        VariableInfo v3  = new VariableInfo("prioridad",   "String", true, VariableInfo.NESTED);
        VariableInfo v4  = new VariableInfo("responsable", "String", true, VariableInfo.NESTED);
        VariableInfo v[] = { v1, v2, v3, v4 };        
        return v;
	}
	
}
