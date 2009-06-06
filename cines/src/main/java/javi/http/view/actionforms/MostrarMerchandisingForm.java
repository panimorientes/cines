package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;

public class MostrarMerchandisingForm extends DefaultActionForm {

	private String descripcion;
    private Long referencia;
    private String talla;
    private Long precio;
    private boolean disponibilidad;
    
    public MostrarMerchandisingForm() {
        reset();
    }
    
    
    
	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public boolean isDisponibilidad() {
		return disponibilidad;
	}



	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}



	public Long getPrecio() {
		return precio;
	}



	public void setPrecio(Long precio) {
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



	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
  
    private void reset() {
        referencia = null;
        talla = null;
        precio = null;
        disponibilidad = false;

    }

}
