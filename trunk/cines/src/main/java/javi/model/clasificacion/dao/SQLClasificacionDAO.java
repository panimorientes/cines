package javi.model.clasificacion.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.cine.vo.CineVO;
import javi.model.clasificacion.vo.ClasificacionVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLClasificacionDAO {

	public List<ClasificacionVO> findAll(Connection connection)
	throws InternalErrorException;
	
	
}
