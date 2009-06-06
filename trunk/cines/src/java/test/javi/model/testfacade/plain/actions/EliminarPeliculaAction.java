package javi.model.testfacade.plain.actions;

import java.sql.Connection;

import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;
import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class EliminarPeliculaAction implements NonTransactionalPlainAction {
	 private long idPelicula;

		
	    public EliminarPeliculaAction(long idPelicula) {
	    	this.idPelicula = idPelicula;
	    	
	    }
   
    /**
     */
    public Object execute(Connection connection )
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPeliculaDAO usuarioDAO = SQLPeliculaDAOFactory.getDAO();
      
    
        usuarioDAO.remove(connection, idPelicula);
        return null;

    }


}
