package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class BusquedaForm extends DefaultActionForm {

    private String clave;
    private String clasificacion;
    
    public BusquedaForm() {
        reset();
    }
    
    
   

	public String getClasificacion() {
		return clasificacion;
	}




	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}




	public String getClave() {
		return clave;
	}




	public void setClave(String clave) {
		this.clave = clave;
	}






	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();       
        
        return errors;
        
    }
    
    private void reset() {
    	clave = null;
       clasificacion = null;
     
    }

}
