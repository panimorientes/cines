package javi.model.tarjeta.dao;

import java.sql.Connection;

import javi.model.tarjeta.vo.TarjetaVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLTarjetaDAO {

    public void create(Connection connection, TarjetaVO userTarjeta)
    	throws  InternalErrorException;
        
    public boolean exists(Connection connection, String login) 
    	throws InternalErrorException;
     
    public TarjetaVO find(Connection connection, String login)
        throws InstanceNotFoundException, InternalErrorException;
            
    public void remove(Connection connection,  String login) 
    	throws InstanceNotFoundException, InternalErrorException ;

}
