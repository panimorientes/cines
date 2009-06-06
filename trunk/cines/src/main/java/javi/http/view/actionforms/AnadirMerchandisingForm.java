package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class AnadirMerchandisingForm extends DefaultActionForm {

	private String descripcion;
    private Long referencia;
    private String talla;
    private double precio;
    private boolean disponibilidad;
    
    private FormFile file;
    
    public AnadirMerchandisingForm() {
        reset();
    }
    
    


	public FormFile getFile() {
		return file;
	}




	public void setFile(FormFile file) {
		this.file = file;
	}




	public boolean isDisponibilidad() {
		return disponibilidad;
	}




	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}




	public double getPrecio() {
		return precio;
	}




	public void setPrecio(double precio) {
		this.precio = precio;
	}




	public Long getReferencia() {
		return referencia;
	}




	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}




	public String getTalla() {
		return talla;
	}




	public void setTalla(String talla) {
		this.talla = talla;
	}




	public String getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}




	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        PropertyValidator.validateMandatory(errors, "descripcion", descripcion);

        
        return errors;
        
    }
    
    private void reset() {
        descripcion = null;
    	referencia = null;
        talla = null;
        precio = 0D;
        disponibilidad = false;
     
    }

}
