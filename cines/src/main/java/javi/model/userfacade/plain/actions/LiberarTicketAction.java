package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.ticket.dao.SQLTicketDAOFactory;
import javi.model.ticket.vo.TicketVO;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class LiberarTicketAction implements TransactionalPlainAction{

	
	private long idTicket;
	
	public LiberarTicketAction(long idTicket) {
		this.idTicket = idTicket;
	}
	
	
	public Object execute(Connection connection) throws ModelException, InternalErrorException {
		
		//Actualizamos el ticket con estado Libre 0
		TicketVO ticket = SQLTicketDAOFactory.getDAO().find(connection, idTicket);
		ticket.setEstado(0);
		SQLTicketDAOFactory.getDAO().update(connection, ticket);
		
		return null;
	}

	
	
}
