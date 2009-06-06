package javi.model.producto.dao;

import java.sql.Connection;

import javi.model.producto.vo.ProductoVO;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLProductoDAO {

    public ProductoVO find(Connection connection, int tipo, long item) 
		throws InternalErrorException, InstanceNotFoundException;
    
}
