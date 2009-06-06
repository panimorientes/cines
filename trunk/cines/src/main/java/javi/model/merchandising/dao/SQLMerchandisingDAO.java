package javi.model.merchandising.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.merchandising.vo.MerchandisingVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLMerchandisingDAO {

    public void create(Connection connection, String descripcion, long referencia, String tallas, double precio, boolean disponibilidad)
    	throws  InternalErrorException,DuplicateInstanceException;
        
    public boolean exists(Connection connection, Long referencia) 
    	throws InternalErrorException;
     
    public List<MerchandisingVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException;
    
    public MerchandisingVO find(Connection connection,long referencia)
	throws InstanceNotFoundException, InternalErrorException;
    
    
    public void update(Connection connection, MerchandisingVO merchandising) 
    throws InstanceNotFoundException, InternalErrorException;
            
    public void remove(Connection connection,  Long idMerchandising) 
    	throws InstanceNotFoundException, InternalErrorException ;

}
