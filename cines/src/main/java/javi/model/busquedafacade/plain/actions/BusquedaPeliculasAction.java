package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;
import java.util.Calendar;

import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BusquedaPeliculasAction implements NonTransactionalPlainAction {

    private String titulo;
    private String categoria;
    private boolean byDate;
    private Calendar fecha;
	
    public BusquedaPeliculasAction(String titulo, String categoria, boolean byDate, Calendar fecha) {
    	this.titulo = titulo;
    	this.categoria = categoria;
    	this.byDate = byDate;
    	this.fecha = fecha;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPeliculaDAO busquedaPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
        
        if (!byDate)
        	return busquedaPeliculaDAO.find(connection, titulo, categoria);
        else
        	return busquedaPeliculaDAO.find(connection, titulo, categoria, fecha);

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
