package javi.model.cine.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.cine.vo.CineVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLCineDAO {



	public void create(Connection connection, String nombre, long numSalas, long cp)
		throws DuplicateInstanceException, InternalErrorException;

	public boolean exists(Connection connection, Long idCine)
		throws InternalErrorException;

	public void update(Connection connection, CineVO adminCine) 
		throws InstanceNotFoundException, InternalErrorException;

	public void remove(Connection connection, long idCine) 
			throws InstanceNotFoundException, InternalErrorException;

	public List<CineVO> find(Connection connection, String cine)
		throws InstanceNotFoundException, InternalErrorException;
	
	public CineVO find1(Connection connection, String cine)
		throws InstanceNotFoundException, InternalErrorException;
	
	public List<CineVO> find(Connection connection)
		throws InstanceNotFoundException, InternalErrorException;
	
	public CineVO find (Connection connection, Long idCine) 
		throws InstanceNotFoundException, InternalErrorException;


}
