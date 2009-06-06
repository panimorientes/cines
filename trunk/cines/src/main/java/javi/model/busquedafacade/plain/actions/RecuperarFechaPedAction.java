package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;


import javi.model.pedido.dao.SQLPedidoDAO;
import javi.model.pedido.dao.SQLPedidoDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class RecuperarFechaPedAction implements NonTransactionalPlainAction {

    private String login;
	
    public RecuperarFechaPedAction(String login) {
        this.login = login;
    }
    
    /**
     *
     * @return the <code>LPedidoVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLPedidoDAO pedidoDAO = SQLPedidoDAOFactory.getDAO();
        
        return pedidoDAO.find(connection,login);
                     

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
