package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.ticket.dao.SQLTicketDAO;
import javi.model.ticket.dao.SQLTicketDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class TicketsAction implements NonTransactionalPlainAction {

    
    public TicketsAction() {
        
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLTicketDAO ticketsDAO = SQLTicketDAOFactory.getDAO();
        
        
        return ticketsDAO.find(connection);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
