package javi.model.producto.dao;

import java.sql.Connection;


import javi.model.dvd.dao.SQLdvdDAOFactory;
import javi.model.merchandising.dao.SQLMerchandisingDAOFactory;
import javi.model.producto.vo.ProductoVO;
import javi.model.ticket.dao.SQLTicketDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public class StandardSQLProductoDAO implements SQLProductoDAO {

    public ProductoVO find(Connection connection, int tipo, long item) 
	throws InternalErrorException, InstanceNotFoundException{
		
		switch (tipo) {
		case 0:
			return SQLTicketDAOFactory.getDAO().find(connection, item);
			
		case 1:
			return SQLMerchandisingDAOFactory.getDAO().find(connection, item);
			
		case 2:
			return SQLdvdDAOFactory.getDAO().find(connection, item);
			
			
		default:
			throw new InstanceNotFoundException(item,ProductoVO.class.getName());
		}
		
		
	}
	
	

}
