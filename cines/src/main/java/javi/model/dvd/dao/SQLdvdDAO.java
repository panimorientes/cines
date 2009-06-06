package javi.model.dvd.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.dvd.vo.dvdVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLdvdDAO {

    	public void create(Connection connection, String titulo, String director, String clasificacion, String descripcion,double precio, boolean disponibilidad)
    		throws DuplicateInstanceException, InternalErrorException;
    
    	public boolean exists(Connection connection, Long idDvd)
    		throws InternalErrorException;
    

		public void update(Connection connection, dvdVO dvdVO) 
			throws InstanceNotFoundException, InternalErrorException;
    
		public void remove(Connection connection, long idDvd) 
			throws InstanceNotFoundException, InternalErrorException;

		public dvdVO find(Connection connection, Long iddvd)
	        throws InstanceNotFoundException, InternalErrorException;
		
	    public dvdVO find(Connection connection, String titulo)
	        throws InstanceNotFoundException, InternalErrorException;
	    
	    public List<dvdVO> find(Connection connection)
        	throws InstanceNotFoundException, InternalErrorException;
	        

}
