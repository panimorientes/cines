package javi.model.pedido.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.pedido.vo.PedidoVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLPedidoDAO {

    public PedidoVO create(Connection connection, PedidoVO userPedido)
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException;
    
    public List<PedidoVO> find(Connection connection, String login)
    	throws InternalErrorException, InstanceNotFoundException;
   
}
