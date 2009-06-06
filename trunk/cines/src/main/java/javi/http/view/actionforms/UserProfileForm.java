package javi.http.view.actionforms;


import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import javi.http.view.applicationobjects.Countries;
import javi.http.view.applicationobjects.Languages;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class UserProfileForm extends DefaultActionForm {

    public final static String REGISTER_ACTION = "REGISTER";
    public final static String UPDATE_ACTION = "UPDATE";
    
    private final static Collection ACTION_TYPES = Arrays.asList(
        new String[] {REGISTER_ACTION, UPDATE_ACTION});

    private String action;
    private String login;
    private String pass;
    private String retypePassword;
    private String nombre;
    private String ape1;
    private String ape2;
    private String cp;
    private Long cpAsLong;
    private String ciudad;
    private String direccion;
    private String numero;
    private Long numeroAsLong;
    private String email;
    private String lenguaje;
    private String pais;
    
    public UserProfileForm() {
        reset();
    }
    
    
    
    public Long getCpAsLong() {
		return cpAsLong;
	}



	public void setCpAsLong(Long cpAsLong) {
		this.cpAsLong = cpAsLong;
	}



	public Long getNumeroAsLong() {
		return numeroAsLong;
	}



	public void setNumeroAsLong(Long numeroAsLong) {
		this.numeroAsLong = numeroAsLong;
	}



	public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero.trim();
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1.trim();
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2.trim();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje.trim();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais.trim();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass.trim();
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword.trim();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }

    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        PropertyValidator.validateString(errors, "action", action, true, 
            ACTION_TYPES);

        if (REGISTER_ACTION.equals(action)) {
            PropertyValidator.validateMandatory(errors, "loginName", login);
            boolean validatePassword = PropertyValidator.validateMandatory(
                errors, "password", pass);
            validatePassword = validatePassword &&
                PropertyValidator.validateMandatory(errors, "retypePassword", 
                    retypePassword);
            if (validatePassword && !pass.equals(retypePassword)) {
                errors.add("password", 
                    new ActionMessage("ErrorMessages.password.doNotMatch"));
            }
        }
            
        PropertyValidator.validateMandatory(errors, "nombre", nombre);
        PropertyValidator.validateMandatory(errors, "ape1", ape1);
        PropertyValidator.validateMandatory(errors, "ape1", ape2);
        PropertyValidator.validateMandatory(errors, "direccion", direccion);
        MiniPortalPropertyValidator.validateEmailAddress(errors, "email",  email);
        PropertyValidator.validateString(errors, "lenguaje", lenguaje, true, Languages.getLanguageCodes());
        PropertyValidator.validateString(errors, "pais", pais, true, Countries.getCountryCodes());
        cpAsLong = new Long(PropertyValidator.validateLong(errors, "cp", cp, true, 1, Long.MAX_VALUE));
        numeroAsLong = new Long(PropertyValidator.validateLong(errors, "numero", numero, true, 1, Long.MAX_VALUE));
        
        return errors;
        
    }
    
    private void reset() {
        action = null;
        login = null;
        pass = null;
        retypePassword = null;
        nombre = null;
        ape1 = null;
        ape2 = null;
        direccion = null;
        cp = null;
        ciudad = null;
        numero = null;
        email = null;
        lenguaje = null;
        pais = null;
    }

}
