package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.dvd.dao.SQLdvdDAOFactory;
import javi.model.merchandising.dao.SQLMerchandisingDAO;
import javi.model.merchandising.dao.SQLMerchandisingDAOFactory;
import javi.model.merchandising.vo.MerchandisingVO;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class ComprarDAction implements TransactionalPlainAction{

	private String id;
	
	public ComprarDAction(String id) {
		this.id = id;
	}

	public Object execute(Connection connection) throws ModelException, InternalErrorException {
		
		return SQLdvdDAOFactory.getDAO().find(connection, id);
		
		
	}
	
	
	
	
	
}