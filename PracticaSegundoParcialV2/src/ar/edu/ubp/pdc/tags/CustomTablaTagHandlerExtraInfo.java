package ar.edu.ubp.pdc.tags;
import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class CustomTablaTagHandlerExtraInfo extends TagExtraInfo {

	/*
	 * El constructor de la clase VariableInfo recibe cuatro par�metros: 
	 * Nombre de la variable 
	 * Tipo de dato de la variable 
	 * Bandera que indica si la variable deber�a ser creada o no por el contenedor 
	 * �mbito de la variable dentro de la p�gina JSP:
	 *   - NESTED: la variable vale s�lo en el cuerpo del tag
	 *   - AT_BEGIN: la variable vale en cualquier lugar del JSP luego de haber encontrado el tag de activaci�n 
	 *   - AT_END: la variable puede ser usada en cualquier parte
	 */
	@Override
	public VariableInfo[] getVariableInfo(TagData data) {
        VariableInfo v1  = new VariableInfo("nombre","String", true, VariableInfo.NESTED);
        VariableInfo v2  = new VariableInfo("apellido","String", true, VariableInfo.NESTED);
        VariableInfo v3  = new VariableInfo("legajo", "String", true, VariableInfo.NESTED);
        VariableInfo v[] = { v1, v2, v3 };        
        return v;
	}
	
}
