package javi.model.userfacade.plain.actions;

import java.sql.Connection;
import java.util.Calendar;

import javi.model.carroCompra.CarroCompra;
import javi.model.producto.vo.ProductoVO;
import javi.model.ticket.dao.SQLTicketDAO;
import javi.model.ticket.dao.SQLTicketDAOFactory;
import javi.model.ticket.vo.TicketVO;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class LiberarTicketsAction implements TransactionalPlainAction {

	private String loginName;
	private CarroCompra carroCompra;

	public LiberarTicketsAction(String loginName, CarroCompra carroCompra) {

		this.loginName = loginName;
		this.carroCompra = carroCompra;

	}

	public Object execute(Connection connection) throws ModelException,
			InternalErrorException {

		SQLTicketDAO ticketDAO = SQLTicketDAOFactory.getDAO();

		for (int i = 0; i < carroCompra.getLineas().size(); i++) {

			ProductoVO productoVO = carroCompra.getLineas().get(i);

			if (productoVO.getTipo() == 0) {

				TicketVO ticketVO = ticketDAO.find(connection, productoVO
						.getIdProducto());

				ticketVO.setEstado(0);

				ticketDAO.update(connection, ticketVO);

			}

		}

		return null;

	}

}
