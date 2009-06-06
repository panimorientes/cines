package javi.model.sesion.dao;

import java.sql.Connection;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javi.model.sesion.vo.SesionVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLSesionDAO {

    public SesionVO create(Connection connection, SesionVO sesion)
    	throws  InternalErrorException;
        
    public boolean exists(Connection connection, Calendar fecha, Time hora, Long idSala, Long idCine) 
    	throws InternalErrorException;
     
    /**
     * Busca la sesion a traves de su identificador 
     **/
    public SesionVO find(Connection connection, long idSesion) 
    	throws InternalErrorException, InstanceNotFoundException;
        
    public List<SesionVO> find(Connection connection, Long idCine)
        throws InstanceNotFoundException, InternalErrorException;
    
    public List<SesionVO> find1(Connection connection, Long idSesion)
    throws InstanceNotFoundException, InternalErrorException;

    
    public List<SesionVO> find(Connection connection)
    	throws InstanceNotFoundException, InternalErrorException;

    public void update(Connection connection, SesionVO adminSesion) 
    throws InstanceNotFoundException, InternalErrorException;
            
    public void remove(Connection connection, Calendar fecha, Time hora, Long idSala, Long idCine) 
    	throws InstanceNotFoundException, InternalErrorException ;
    
    public void remove(Connection connection, long idSesion) 
		throws InstanceNotFoundException, InternalErrorException ;
    
    public void removeP(Connection connection, long idPelicula) 
	throws InstanceNotFoundException, InternalErrorException ;
}
