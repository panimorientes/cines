package javi.model.lpedido.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.lpedido.vo.LPedidoVO;
import javi.model.pedido.vo.PedidoVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLLPedidoDAO {

    public void create(Connection connection, LPedidoVO userLPedido)
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException;
    
    public List<LPedidoVO> recuperarLPedido(Connection connection, List<PedidoVO> pedidos)
    	throws InternalErrorException, InstanceNotFoundException;
    
 
}
