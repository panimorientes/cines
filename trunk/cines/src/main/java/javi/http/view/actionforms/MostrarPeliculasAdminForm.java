package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class MostrarPeliculasAdminForm extends DefaultActionForm {

    private Long idPelicula;
    private String titulo;
    private String director;
    private String clasificacion;
    private String descripcion;
    
    public MostrarPeliculasAdminForm() {
        reset();
    }
    
    
   

	public String getClasificacion() {
		return clasificacion;
	}




	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}




	public String getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}




	public String getDirector() {
		return director;
	}




	public void setDirector(String director) {
		this.director = director;
	}




	public Long getIdPelicula() {
		return idPelicula;
	}




	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}




	public String getTitulo() {
		return titulo;
	}




	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
       idPelicula = null;
       titulo = null;
       director = null;
       clasificacion = null;
       descripcion = null;
     
    }

}
