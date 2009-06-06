package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class AnadirCineForm extends DefaultActionForm {

    private String loginName;
    private String nombre;
    private String numSalas;
    private Long numSalasAsLong;
    private String cp;
    private Long cpAsLong;
    private String ciudad;
    private String direccion;
    private String numero;
    private Long numeroAsLong;
    
    public AnadirCineForm() {
        reset();
    }
    
  


	public Long getCpAsLong() {
		return cpAsLong;
	}



	public void setCpAsLong(Long cpAsInt) {
		this.cpAsLong = cpAsInt;
	}



	public Long getNumeroAsLong() {
		return numeroAsLong;
	}



	public void setNumeroAsLong(Long numeroAsInt) {
		this.numeroAsLong = numeroAsInt;
	}



	public Long getNumSalasAsLong() {
		return numSalasAsLong;
	}



	public void setNumSalasAsLong(Long numSalasAsInt) {
		this.numSalasAsLong = numSalasAsInt;
	}



	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad.trim();
	}



	public String getCp() {
		return cp;	
		
	}



	public void setCp(String cp) {
		this.cp = cp.trim();
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion.trim();
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName.trim();
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero.trim();
	}



	public String getNumSalas() {
		return numSalas;
	}



	public void setNumSalas(String numSalas) {
		this.numSalas = numSalas.trim();
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();

        numSalasAsLong = new Long(PropertyValidator.validateLong(errors, "numSalas", numSalas, true, 1, Long.MAX_VALUE));
        cpAsLong = new Long(PropertyValidator.validateLong(errors, "cp", cp, true, 1, Long.MAX_VALUE));
        numeroAsLong = new Long(PropertyValidator.validateLong(errors, "numero", numero, true, 1, Long.MAX_VALUE));
        PropertyValidator.validateMandatory(errors, "nombre", nombre);
        PropertyValidator.validateMandatory(errors, "ciudad", ciudad);
        PropertyValidator.validateMandatory(errors, "direccion", direccion);
        
        return errors;
        
    }
    
    private void reset() {
        loginName = null;
    	nombre = null;
        numSalas = null;
        cp = null;
        ciudad = null;
        direccion = null;
        numero = null;
        
    }

}
