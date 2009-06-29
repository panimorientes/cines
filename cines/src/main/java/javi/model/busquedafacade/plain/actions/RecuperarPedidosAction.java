package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javi.http.controller.session.SessionManager;
import javi.model.busquedafacade.vo.LPedidoCustomVO;
import javi.model.busquedafacade.vo.PedidoCustomVO;
import javi.model.lpedido.dao.SQLLPedidoDAO;
import javi.model.lpedido.dao.SQLLPedidoDAOFactory;
import javi.model.lpedido.vo.LPedidoVO;
import javi.model.pedido.dao.SQLPedidoDAO;
import javi.model.pedido.dao.SQLPedidoDAOFactory;
import javi.model.pedido.vo.PedidoVO;
import javi.model.producto.dao.SQLProductoDAO;
import javi.model.producto.dao.SQLProductoDAOFactory;
import javi.model.producto.vo.ProductoVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class RecuperarPedidosAction implements NonTransactionalPlainAction {

    private String login;
	
    public RecuperarPedidosAction(String login) {
        this.login = login;
    }
    
    /**
     *
     * @return the <code>LPedidoVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPedidoDAO pedidoDAO = SQLPedidoDAOFactory.getDAO();
        SQLLPedidoDAO lpedidoDAO = SQLLPedidoDAOFactory.getDAO();
        SQLProductoDAO productoDAO = SQLProductoDAOFactory.getDAO();
        
        //recupera todos los pedidos que ha hecho esa persona
        List<PedidoVO> pedidos;
        if (login.equals("Administrator"))
        	pedidos=pedidoDAO.find(connection);
        else
        	pedidos=pedidoDAO.find(connection,login);
        
        List<PedidoCustomVO> pedidosCustom = new ArrayList<PedidoCustomVO>();
        List<LPedidoCustomVO> lPedidosCustom;
        ProductoVO producto;
        for (PedidoVO pedidoAux: pedidos) {
        	lPedidosCustom = new ArrayList<LPedidoCustomVO>();
        	for (LPedidoVO lPedidoAux: lpedidoDAO.recuperarLPedido(connection, pedidoAux)) {
        		producto = productoDAO.find(connection, lPedidoAux.getTipo(), lPedidoAux.getIdProducto());
        		lPedidosCustom.add(new LPedidoCustomVO(lPedidoAux.getIdProducto(), lPedidoAux.getNumUnidades(), producto.getDescripcion(), producto.getPrecio()));
        	}
        	pedidosCustom.add(new PedidoCustomVO(pedidoAux.getIdPedido(), pedidoAux.getFechaPedido(), pedidoAux.getLogin(), lPedidosCustom));
        }
        
        //devuelve una lista de lineas de pedidos de todos los pedidos que ha hecho
        //es decir,todas las lineas de pedido que ha hecho
        return pedidosCustom;
                     

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
