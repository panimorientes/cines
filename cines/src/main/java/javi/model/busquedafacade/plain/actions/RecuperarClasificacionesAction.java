package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.clasificacion.dao.SQLClasificacionDAO;
import javi.model.clasificacion.dao.SQLClasificacionDAOFactory;
import javi.model.sala.dao.SQLSalaDAO;
import javi.model.sala.dao.SQLSalaDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class RecuperarClasificacionesAction implements NonTransactionalPlainAction {


	  public RecuperarClasificacionesAction() {
	        
	    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLClasificacionDAO clasificacionDAO = SQLClasificacionDAOFactory.getDAO();
        
        
        return clasificacionDAO.findAll(connection);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
