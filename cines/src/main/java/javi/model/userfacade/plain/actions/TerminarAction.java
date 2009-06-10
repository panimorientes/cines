package javi.model.userfacade.plain.actions;

import java.sql.Connection;
import java.sql.Date;

//import com.sun.org.apache.xerces.internal.util.LocatorProxy;

import javi.model.carroCompra.CarroCompra;
import javi.model.dvd.vo.dvdVO;
import javi.model.lpedido.dao.SQLLPedidoDAOFactory;
import javi.model.lpedido.vo.LPedidoVO;
import javi.model.merchandising.vo.MerchandisingVO;
import javi.model.pedido.dao.SQLPedidoDAO;
import javi.model.pedido.dao.SQLPedidoDAOFactory;
import javi.model.pedido.vo.PedidoVO;
import javi.model.producto.vo.ProductoVO;
import javi.model.ticket.dao.SQLTicketDAOFactory;
import javi.model.ticket.vo.TicketVO;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class TerminarAction implements TransactionalPlainAction{

	private CarroCompra carro;
	private String loginName;
	
	
	public TerminarAction(String loginName, CarroCompra carro) {
		this.carro = carro;
		this.loginName = loginName;
	}
	
	
	
	public Object execute(Connection connection) throws ModelException, InternalErrorException {
		
		
		//Comprobamos que el carro tenga elemtos.
		if(!carro.getLineas().isEmpty()){
			
			//Damos de alta un pedido.
			PedidoVO pedido = new PedidoVO(new Date(new java.util.Date().getTime()),loginName);
			pedido = SQLPedidoDAOFactory.getDAO().create(connection, pedido);
			
			//Recorremos las lineas del pedido.
			for(ProductoVO item : carro.getLineas()){
				
				//Damos de alta una linea de pedido.
				LPedidoVO lp = null;
				switch (item.getTipo()) {
				case 0:
					  TicketVO ticket = (TicketVO) item;
					  lp = new LPedidoVO(pedido.getIdPedido(),new Long(ticket.getIdTicket()),new Long(1L),0);
					  
					break;
				case 1:
					MerchandisingVO mer = (MerchandisingVO) item;
					lp = new LPedidoVO(pedido.getIdPedido(), new Long(mer.getIdMerchandising()),new Long(1L),1);
					
				case 2:
					dvdVO dvd = (dvdVO) item;
					lp = new LPedidoVO(pedido.getIdPedido(), new Long(dvd.getIdDvd()), new Long(1L),2);
					break;
				default:
					break;
				}
				SQLLPedidoDAOFactory.getDAO().create(connection, lp);
				
				//Si se trata de un ticket lo reservamos
				if(item.getTipo() == 0){
					TicketVO ticket = (TicketVO) item;
					ticket.setEstado(2);
					SQLTicketDAOFactory.getDAO().update(connection, ticket);
				}
			
			}
		}
		//Damos de baja las lineas que hay en el carro
		carro.getLineas().clear();
		carro.borrarTotal();
		
		
		
		
		return null;
	}

	
	
	
}
