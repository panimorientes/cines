package javi.model.butaca.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.butaca.vo.ButacaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLButacaDAO {

    public void create(Connection connection, ButacaVO adminButaca, boolean [] array)
        throws DuplicateInstanceException, InternalErrorException;
    
    /**
     * Obtiene las butacas de una determinada sala.
     * 
     * @param connection
     * 
     * @param nombreCine >> nombre del cine 
     * @param idSala >> identificador de la sala
     * 
     * @return Lista de butacas en la sala
     * @throws InternalErrorException
     */
    public List<ButacaVO> butacasSalaCine(Connection connection, long idCine, long idSala ) 
    	throws InternalErrorException;
}