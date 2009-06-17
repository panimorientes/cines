package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;
import java.util.List;

import javi.model.lpedido.dao.SQLLPedidoDAO;
import javi.model.lpedido.dao.SQLLPedidoDAOFactory;
import javi.model.pedido.dao.SQLPedidoDAO;
import javi.model.pedido.dao.SQLPedidoDAOFactory;
import javi.model.pedido.vo.PedidoVO;
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
        
        //recupera todos los pedidos que ha hecho esa persona
        List<PedidoVO> pedidos=pedidoDAO.find(connection,login);
        
        //devuelve una lista de lineas de pedidos de todos los pedidos que ha hecho
        //es decir,todas las lineas de pedido que ha hecho
        return lpedidoDAO.recuperarLPedido(connection, pedidos);
                     

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
