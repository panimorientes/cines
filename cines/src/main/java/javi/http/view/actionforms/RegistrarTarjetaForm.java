package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class RegistrarTarjetaForm extends DefaultActionForm {

    private String tarjeta;
    private Long tarjetaAsLong;
    private String login;
        
    public RegistrarTarjetaForm() {
        reset();
    }
    
    
    
    public Long getTarjetaAsLong() {
		return tarjetaAsLong;
	}



	public void setTarjetaAsLong(Long tarjetaAsLong) {
		this.tarjetaAsLong = tarjetaAsLong;
	}



	public String getRegistrarTarjeta() {
        return tarjeta;
    }
    
    
    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim();
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta.trim();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        tarjetaAsLong = new Long(PropertyValidator.validateLong(
                errors, "tarjeta", tarjeta, true, 1, Long.MAX_VALUE));
        
        return errors;
        
    }

	private void reset() {
        tarjeta = null;
    	login = null;
      
    }

}
