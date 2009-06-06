package javi.http.view.actionforms;

import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;

import javi.http.view.applicationobjects.DateRanges;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class MostrarCarteleraForm extends DefaultActionForm {

	 private Date fecha;
	 private Time hora;
	 private String precio;
	 private Long precioAsLong;
	 private String numButacas;
	 private Long numButacasAsLong;
	 private boolean numerada;
	 private String titulo;
	 private String idSala;
	 private Long idSalaAsLong;
	 private String nombre;
    
    public MostrarCarteleraForm() {
        reset();
    }
    
    
    


	public Long getIdSalaAsLong() {
		return idSalaAsLong;
	}





	public void setIdSalaAsLong(Long idSalaAsLong) {
		this.idSalaAsLong = idSalaAsLong;
	}





	public Long getNumButacasAsLong() {
		return numButacasAsLong;
	}





	public void setNumButacasAsLong(Long numButacasAsLong) {
		this.numButacasAsLong = numButacasAsLong;
	}





	public Long getPrecioAsLong() {
		return precioAsLong;
	}





	public void setPrecioAsLong(Long precioAsLong) {
		this.precioAsLong = precioAsLong;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre.trim();
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public Time getHora() {
		return hora;
	}





	public void setHora(Time hora) {
		this.hora = hora;
	}





	public String getIdSala() {
		return idSala;
	}





	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}





	public String getNumButacas() {
		return numButacas;
	}





	public void setNumButacas(String numButacas) {
		this.numButacas = numButacas;
	}





	public boolean isNumerada() {
		return numerada;
	}





	public void setNumerada(boolean numerada) {
		this.numerada = numerada;
	}





	public String getPrecio() {
		return precio;
	}





	public void setPrecio(String precio) {
		this.precio = precio;
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
       
        precioAsLong = new Long(PropertyValidator.validateLong(errors, "precio", precio, true, 1, Long.MAX_VALUE));
        numButacasAsLong = new Long(PropertyValidator.validateLong(errors, "numButacas", numButacas, true, 1, Long.MAX_VALUE));
        idSalaAsLong = new Long(PropertyValidator.validateLong(errors, "idSala", idSala, true, 1, Long.MAX_VALUE));
        PropertyValidator.validateMandatory(errors, "titulo", titulo);
        PropertyValidator.validateMandatory(errors, "nombre", nombre);
        
        return errors;
        
    }
    
    private void reset() {
        fecha = new Date(new java.util.Date().getTime());
        hora = new Time(new java.util.Date().getTime());
        precio = null;
        numButacas = null;
        numerada = true;
        titulo = null;
        idSala = null;
        nombre = null;
    }

}
