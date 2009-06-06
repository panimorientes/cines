package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BusquedaPeliculasAction implements NonTransactionalPlainAction {

    private String titulo;
    private String categoria;
	
    public BusquedaPeliculasAction(String titulo, String categoria) {
    	this.titulo = titulo;
    	this.categoria = categoria;
        
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPeliculaDAO busquedaPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
        
        
        return busquedaPeliculaDAO.find(connection, titulo, categoria);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
