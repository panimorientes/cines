package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;
import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BuscaAdminPeliculaAction implements NonTransactionalPlainAction {

   
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPeliculaDAO busquedaPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
        
        
        return busquedaPeliculaDAO.find(connection);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
