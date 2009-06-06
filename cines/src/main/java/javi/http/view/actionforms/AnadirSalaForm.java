package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class AnadirSalaForm extends DefaultActionForm {

    private String cine;
    private String idSala;
    private Long idSalasAsLong;
    private String numFilas;
    private Long numFilasAsLong;
    private String asientos;
    private Long asientosAsLong;
    private  boolean butacas[] = new boolean[401];

    
    
    public AnadirSalaForm() {
        reset();
    }
    
 

	public String getCine() {
		return cine;
	}



	public void setCine(String cine) {
		this.cine = cine;
	}





	public String getAsientos() {
		return asientos;
	}



	public void setAsientos(String asientos) {
		this.asientos = asientos;
	}



	public String getNumFilas() {
		return numFilas;
	}



	public void setNumFilas(String numFilas) {
		this.numFilas = numFilas;
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



	public Long getIdSalasAsLong() {
		return idSalasAsLong;
	}



	public void setIdSalasAsLong(Long idSalasAsLong) {
		this.idSalasAsLong = idSalasAsLong;
	}



	public boolean[] getButacas() {
		return butacas;
	}

	
	public void setButacas(int i,boolean b) {
		this.butacas[i] = b;
	}

	

	public Long getIdSalaAsLong() {
		return idSalasAsLong;
	}



	public void setIdSalaAsLong(Long idSalaAsLong) {
		this.idSalasAsLong = idSalaAsLong;
	}


	public String getIdSala() {
		return idSala;
	}


	public void setIdSala(String idSala) {
		this.idSala = idSala.trim();
	}




	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();

        idSalasAsLong = new Long(PropertyValidator.validateLong(errors, "idSala", idSala, true, 1, Long.MAX_VALUE));
        numFilasAsLong = new Long(PropertyValidator.validateLong(errors, "numFilas", numFilas, true, 1, Long.MAX_VALUE));
        asientosAsLong = new Long(PropertyValidator.validateLong(errors, "asientos", asientos, true, 1, Long.MAX_VALUE));
        PropertyValidator.validateMandatory(errors, "cine", cine);
       
        return errors;
        
    }
    
    private void reset() {
  
    	cine = null;
    	idSala = null;
    	asientos = null;
    	numFilas = null;
    	for (int i=0; i < butacas.length; i++) {
                butacas[i]=true;         
        }
    }



}
