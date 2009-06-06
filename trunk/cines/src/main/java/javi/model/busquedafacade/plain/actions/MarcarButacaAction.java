package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.ticket.dao.SQLTicketDAO;
import javi.model.ticket.dao.SQLTicketDAOFactory;
import javi.model.ticket.vo.TicketVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class MarcarButacaAction implements NonTransactionalPlainAction {

    private long idTicket;
    
    public MarcarButacaAction(long idTicket) {
        this.idTicket = idTicket;
    }
    
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
        
    	SQLTicketDAO dao = SQLTicketDAOFactory.getDAO();
    	
    	TicketVO ticket = dao.find(connection, idTicket);
    	ticket.setEstado(1);
    	dao.update(connection, ticket);
    	
    	return ticket;
    }
    


}