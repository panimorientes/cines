package javi.model.direccion.dao;

import java.sql.Connection;

import javi.model.direccion.vo.DireccionVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLDireccionDAO {

    public void create(Connection connection, DireccionVO adminDireccion)
    	throws  InternalErrorException;
        
    public boolean exists(Connection connection, String login) 
    	throws InternalErrorException;
     
    public DireccionVO find(Connection connection, String login)
        throws InstanceNotFoundException, InternalErrorException;
    
    public void update(Connection connection, DireccionVO userDireccion) 
    throws InstanceNotFoundException, InternalErrorException;
            
    public void remove(Connection connection, Long cp ) 
    	throws InstanceNotFoundException, InternalErrorException ;

}
