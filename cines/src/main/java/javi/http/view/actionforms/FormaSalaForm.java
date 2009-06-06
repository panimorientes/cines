package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class FormaSalaForm extends DefaultActionForm {

    private String numFilas;
    private Long numFilasAsLong;
    private String asientos;
    private Long asientosAsLong;
    
    
    public FormaSalaForm() {
        reset();
    }
    
    
 

	public Long getAsientosAsLong() {
		return asientosAsLong;
	}




	public void setAsientosAsLong(Long asientosAsLong) {
		this.asientosAsLong = asientosAsLong;
	}




	public Long getNumFilasAsLong() {
		return numFilasAsLong;
	}




	public void setNumFilasAsLong(Long numFilasAsLong) {
		this.numFilasAsLong = numFilasAsLong;
	}




	public String getNumFilas() {
		return numFilas;
	}



	public void setNumFilas(String numFilas) {
		this.numFilas = numFilas;
	}



	public String getAsientos() {
		return asientos;
	}


	public void setAsientos(String asientos) {
		this.asientos = asientos;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        numFilasAsLong = new Long(PropertyValidator.validateLong(errors, "numFilas", numFilas, true, 1, Long.MAX_VALUE));
        asientosAsLong = new Long(PropertyValidator.validateLong(errors, "asientos", asientos, true, 1, Long.MAX_VALUE));
       
        
        return errors;
        
    }
    
    private void reset() {
    	asientos = null;
    	numFilas = null;
    }



}
