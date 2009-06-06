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

public class EliminarDireccionAction implements NonTransactionalPlainAction {
	 private long cp;

		
	    public EliminarDireccionAction(long cp) {
	    	this.cp = cp;
	    	
	    }
   
    /**
     */
    public Object execute(Connection connection )
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLDireccionDAO usuarioDAO = SQLDireccionDAOFactory.getDAO();
      
    
        usuarioDAO.remove(connection, cp);
        return null;

    }


}
