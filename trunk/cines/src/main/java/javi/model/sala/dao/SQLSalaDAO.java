package javi.model.sala.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.sala.vo.SalaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLSalaDAO {


	public void create(Connection connection, SalaVO adminSala)
	throws DuplicateInstanceException, InternalErrorException;

	public boolean exists(Connection connection, Long idSala, long idCine)
	throws InternalErrorException;

	public List<SalaVO> find(Connection connection, Long idCine)
	throws InstanceNotFoundException, InternalErrorException;
	
	/**
	 * Obtiene una sala por el identificador
	 * 
	 * @param connection
	 * @param nombre
	 * @param idSala
	 * @return SalaVO.
	 * @throws InstanceNotFoundException
	 * @throws InternalErrorException
	 */
	public SalaVO find(Connection connection, long idCine, long idSala)
	throws InstanceNotFoundException, InternalErrorException;

	public void update(Connection connection, SalaVO adminSala) 
	throws InstanceNotFoundException, InternalErrorException;

	public void remove(Connection connection, Long idSala, long idCine) 
	throws InstanceNotFoundException, InternalErrorException;

}