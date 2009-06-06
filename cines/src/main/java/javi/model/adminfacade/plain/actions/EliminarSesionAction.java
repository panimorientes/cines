package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.sesion.dao.SQLSesionDAO;
import javi.model.sesion.dao.SQLSesionDAOFactory;
import javi.model.ticket.dao.SQLTicketDAO;
import javi.model.ticket.dao.SQLTicketDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class EliminarSesionAction implements TransactionalPlainAction {

    private long sesion;
 
    public EliminarSesionAction(long sesion) {
        
        this.sesion = sesion;
       
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws InstanceNotFoundException 
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
    	//Pelicula
        SQLSesionDAO adminSesionDAO = SQLSesionDAOFactory.getDAO();
        SQLTicketDAO adminTicketDAO = SQLTicketDAOFactory.getDAO();

        adminTicketDAO.remove(connection, sesion);
        adminSesionDAO.remove(connection, sesion);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
