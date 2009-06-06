package javi.model.userfacade.plain.actions;

import java.sql.Connection;
import java.util.Calendar;

import javi.model.pedido.dao.SQLPedidoDAO;
import javi.model.pedido.dao.SQLPedidoDAOFactory;
import javi.model.pedido.vo.PedidoVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class AnadirPedidoAction implements TransactionalPlainAction {

    private String loginName;
    private Long idPedido;
    private Calendar fecha; 

    public AnadirPedidoAction(Long idPedido, Calendar fecha, String loginName) {
        
        this.loginName = loginName;
        this.idPedido = idPedido;
        this.fecha = fecha;
        
    }
    
   
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
                
        
    	//SQLPedidoDAO userPedidoDAO = SQLPedidoDAOFactory.getDAO();
        //PedidoVO userPedidoVO = new PedidoVO(idPedido, fecha, loginName);

        
        //userPedidoDAO.create(connection, userPedidoVO);
        
        return null;            

    }
}
