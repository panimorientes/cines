package javi.model.adminfacade.delegate;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface AdminFacadeDelegate extends Serializable {

	 public void anadirPelicula(String titulo, String director,String clasificacion, String descripcion)
	    throws DuplicateInstanceException, InternalErrorException;
	 
    public void eliminarPelicula(Long idPelicula)
    	throws DuplicateInstanceException, InternalErrorException;

    public void anadirdvd(String titulo, String director,String clasificacion, String descripcion,double precio,boolean disponibilidad)
        throws DuplicateInstanceException, InternalErrorException;
    
    public void anadirMerchandising(String descripcion,Long referencia, String talla, double precio, boolean disponibilidad)
    	throws DuplicateInstanceException, InternalErrorException;
    
    public void anadirCine(String loginName, String nombre,Long numSalas, Long cp, String ciudad, String direccion, Long numero)
        throws DuplicateInstanceException, InternalErrorException;
    
    public void anadirSala(Long idSala,Long numFilas, Long asientos, String cine,boolean [] array)
    	throws DuplicateInstanceException, InternalErrorException;
    
    public void anadirSesion(Calendar fecha, Time hora, double precio,boolean numerada,String titulo, Long idSala, String cine)
    	throws DuplicateInstanceException, InternalErrorException;
    
    public void eliminarSesion(long sesion)
		throws DuplicateInstanceException, InternalErrorException;
    
    public void eliminardvd(long idDvd)
		throws DuplicateInstanceException, InternalErrorException;
    
    public void eliminarMerchandising(long idMerchandising)
	throws DuplicateInstanceException, InternalErrorException;
}
