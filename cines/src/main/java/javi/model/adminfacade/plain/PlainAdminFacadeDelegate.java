package javi.model.adminfacade.plain;

import java.sql.Time;
import java.util.Calendar;

import javax.sql.DataSource;

import javi.model.adminfacade.delegate.AdminFacadeDelegate;
import javi.model.adminfacade.plain.actions.AnadirCineAction;
import javi.model.adminfacade.plain.actions.AnadirMerchandisingAction;
import javi.model.adminfacade.plain.actions.AnadirPeliculaAction;
import javi.model.adminfacade.plain.actions.AnadirSalaAction;
import javi.model.adminfacade.plain.actions.AnadirSesionAction;
import javi.model.adminfacade.plain.actions.AnadirdvdAction;
import javi.model.adminfacade.plain.actions.EliminarMerchandisingAction;
import javi.model.adminfacade.plain.actions.EliminarPeliculaAction;
import javi.model.adminfacade.plain.actions.EliminarSesionAction;
import javi.model.adminfacade.plain.actions.EliminardvdAction;
import javi.model.util.GlobalNames;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.DataSourceLocator;
import es.udc.fbellas.j2ee.util.sql.PlainActionProcessor;

public class PlainAdminFacadeDelegate implements AdminFacadeDelegate {

    public PlainAdminFacadeDelegate() {
       
    }
    
    public void anadirPelicula(String titulo, String director,
            String clasificacion, String descripcion)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                AnadirPeliculaAction action = new AnadirPeliculaAction(titulo, director, clasificacion, descripcion);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
        }
    
    public void eliminarPelicula(Long idPelicula)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                EliminarPeliculaAction action = new EliminarPeliculaAction(idPelicula);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
        }
    
    public void anadirdvd(String titulo, String director,
            String clasificacion, String descripcion,double precio,boolean disponibilidad)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                AnadirdvdAction action = new AnadirdvdAction(titulo, director, clasificacion, descripcion,precio,disponibilidad);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
        }
    
    public void anadirMerchandising(String descripcion,Long referencia, String talla, double precio, boolean disponibilidad)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                AnadirMerchandisingAction action = new AnadirMerchandisingAction(descripcion,referencia,talla, precio,disponibilidad);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
        }
    
    
    public void anadirSesion(Calendar fecha, Time hora, double precio, boolean numerada,
    		String titulo, Long idSala, String cine)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                AnadirSesionAction action = new AnadirSesionAction(fecha, hora, precio, numerada, titulo, idSala, cine);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
        }
    
    public void eliminarSesion(long sesion)
    throws DuplicateInstanceException, InternalErrorException {
    
    try {
    
        EliminarSesionAction action = new EliminarSesionAction(sesion);
            
        PlainActionProcessor.process(getDataSource(), action);
        
    } catch (DuplicateInstanceException e) {
        throw e;
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}
    
    public void eliminardvd(long idDvd)
    throws DuplicateInstanceException, InternalErrorException {
    
    try {
    
        EliminardvdAction action = new EliminardvdAction(idDvd);
            
        PlainActionProcessor.process(getDataSource(), action);
        
    } catch (DuplicateInstanceException e) {
        throw e;
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}
    
    public void eliminarMerchandising(long idMerchandising)
    throws DuplicateInstanceException, InternalErrorException {
    
    try {
    
        EliminarMerchandisingAction action = new EliminarMerchandisingAction(idMerchandising);
            
        PlainActionProcessor.process(getDataSource(), action);
        
    } catch (DuplicateInstanceException e) {
        throw e;
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}
    
    
    public void anadirCine(String loginName,String nombre,
    		Long numSalas, Long cp, String ciudad, String direccion, Long numero)
            throws DuplicateInstanceException, InternalErrorException {
            
            try {
            
                AnadirCineAction action = new AnadirCineAction(loginName, nombre, numSalas, cp, ciudad, direccion, numero);
                    
                PlainActionProcessor.process(getDataSource(), action);
                
                
            } catch (DuplicateInstanceException e) {
                throw e;
            } catch (InternalErrorException e) {
                throw e;
            } catch (Exception e) {
                throw new InternalErrorException(e);
            }
            
    }
    
    
    
 
    public void anadirSala(Long idSala,Long numFilas, Long asientos, String cine, boolean [] array)
    throws DuplicateInstanceException, InternalErrorException {
    
    try {
    
        AnadirSalaAction action = new AnadirSalaAction(idSala, numFilas, asientos, cine, array);
            
        PlainActionProcessor.process(getDataSource(), action);
        
    } catch (DuplicateInstanceException e) {
        throw e;
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    }
    
   
 
    private DataSource getDataSource() throws InternalErrorException {
        return DataSourceLocator.getDataSource(
            GlobalNames.CINES_DATA_SOURCE);
    }


    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * AdminFacadeDelegateFactory".
     */    
    
}
