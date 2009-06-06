package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class AnadirdvdForm extends DefaultActionForm {

    private String titulo;
    private String director;
    private String clasificacion;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;
    
    private FormFile file;
    
    public AnadirdvdForm() {
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



	public String getClasificacion() {
		return clasificacion;
	}


	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion.trim();
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.trim();
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director.trim();
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo.trim();
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        PropertyValidator.validateMandatory(errors, "titulo", titulo);
        PropertyValidator.validateMandatory(errors, "director", director);
        PropertyValidator.validateMandatory(errors, "clasificacion", clasificacion);
        PropertyValidator.validateMandatory(errors, "descripcion", descripcion);
        
        return errors;
        
    }
    
    private void reset() {
      
    	titulo = null;
        director = null;
        clasificacion = null;
        descripcion = null;
        precio = 0D;
        disponibilidad=false;

    }

}
