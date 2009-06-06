package javi.model.testfacade.plain;


import javax.sql.DataSource;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.DataSourceLocator;
import es.udc.fbellas.j2ee.util.sql.PlainActionProcessor;


import javi.model.pelicula.vo.PeliculaVO;
import javi.model.testfacade.delegate.TestFacadeDelegate;
import javi.model.testfacade.plain.actions.BuscarPeliculaAction;
import javi.model.testfacade.plain.actions.EliminarCineAction;
import javi.model.testfacade.plain.actions.EliminarDireccionAction;
import javi.model.testfacade.plain.actions.EliminarPeliculaAction;
import javi.model.testfacade.plain.actions.EliminarUsuarioAction;
import javi.model.util.GlobalNames;

public class PlainTestFacadeDelegate implements TestFacadeDelegate {

   
    
    
    public void eliminarUsuario(String loginName)
        throws InternalErrorException {
        
        try {
        
           EliminarUsuarioAction action = new EliminarUsuarioAction(loginName);
                
            PlainActionProcessor.process(getDataSource(), action);
            
       
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    

    
    public void eliminarCine(long idCine)
    throws InternalErrorException {
    
    try {
    
       EliminarCineAction action = new EliminarCineAction(idCine);
            
        PlainActionProcessor.process(getDataSource(), action);
        
   
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}

    public void eliminarDireccion(long cp)
    throws InternalErrorException {
    
    try {
    
       EliminarDireccionAction action = new EliminarDireccionAction(cp);
            
        PlainActionProcessor.process(getDataSource(), action);
        
   
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}
    
    public PeliculaVO buscarPelicula(long idPelicula)
    throws InternalErrorException {
    
    try {
    
       BuscarPeliculaAction action = new BuscarPeliculaAction(idPelicula);
            
        return (PeliculaVO) PlainActionProcessor.process(getDataSource(), action);
        
   
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

	public void eliminarPelicula(long idPelicula) throws DuplicateInstanceException, InternalErrorException {
		
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
    
}
