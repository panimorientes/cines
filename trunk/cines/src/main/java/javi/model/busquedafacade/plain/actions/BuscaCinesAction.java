package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.cine.dao.SQLCineDAO;
import javi.model.cine.dao.SQLCineDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BuscaCinesAction implements NonTransactionalPlainAction {

	public BuscaCinesAction() {

	}

	/**
	 *
	 * @return the <code>UserProfileVO</code>
	 */
	public Object execute(Connection connection)
			throws InstanceNotFoundException, InternalErrorException {

		SQLCineDAO busquedaCineDAO = SQLCineDAOFactory.getDAO();

		return busquedaCineDAO.find(connection);

	}

}
