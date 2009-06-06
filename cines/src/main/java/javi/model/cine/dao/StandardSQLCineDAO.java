package javi.model.cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javi.model.cine.vo.CineVO;
import javi.model.sesion.vo.SesionVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public abstract class StandardSQLCineDAO implements SQLCineDAO {



	public List<CineVO> find(Connection connection, String cine)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CineVO> cines = new ArrayList<CineVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idCine, num_salas, C_cp FROM CINE WHERE nombre LIKE ?";

			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, cine);

			//preparedStatement.setString(i++, nombre);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				i=1;
				Long idCine = resultSet.getLong(i++);
				Long numSalas = resultSet.getLong(i++);
				Long cp = resultSet.getLong(i++);

				cines.add(new CineVO(idCine,cine, numSalas, cp));
			}



			/* Return the value object. */
			return cines; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	public List<CineVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CineVO> cines = new ArrayList<CineVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idCine,nombre, num_salas, C_cp FROM CINE";

			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			
			//preparedStatement.setString(i++, nombre);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				Long idCine = resultSet.getLong(i++);
				String cine = resultSet.getString(i++);
				Long numSalas = resultSet.getLong(i++);
				Long cp = resultSet.getLong(i++);

				cines.add(new CineVO(idCine,cine, numSalas, cp));
			}



			/* Return the value object. */
			return cines; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}

	
	public boolean exists(Connection connection, Long idCine)
	throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idCine FROM CINE" +
			" WHERE idCine = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idCine);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}



	public void update(Connection connection, CineVO adminCineVO) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "UPDATE CINE" +
			" SET idCine = ?, nombre = ?, num_salas = ?, C_cp = ?" +
			" WHERE nombre = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, adminCineVO.getIdCine());
			preparedStatement.setString(i++, adminCineVO.getNombre());
			preparedStatement.setLong(i++, adminCineVO.getNumSalas());
			preparedStatement.setLong(i++, adminCineVO.getCp());

			/* Execute query. */
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(
						adminCineVO.getNombre(), 
						CineVO.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for nombre = '" + 
						adminCineVO.getNombre() + "' in table 'Cine'");
			}        

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}            

	}

	public void remove(Connection connection, long idCine) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM CINE WHERE" +
			" idCine = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idCine);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idCine,
						CineVO.class.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	public CineVO find1(Connection connection, String cine) 
	throws InternalErrorException, InstanceNotFoundException{
	
	CineVO cineVO = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try{
		String query = "SELECT idCine, num_salas, C_cp " +
				       "FROM CINE " +
				       "WHERE nombre = ?";
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, cine);
		
		/*Execute statment*/
		resultSet = preparedStatement.executeQuery();
		
		if(!resultSet.next()){
			throw new InstanceNotFoundException(new Long(cine),SesionVO.class.getName());
		}else{
			
			int i = 1;
			
			Long idCine = resultSet.getLong(i++);
			Long num_salas = resultSet.getLong(i++);
			Long cp = resultSet.getLong(i++);
			
			
			
			cineVO = new CineVO(idCine, cine, num_salas, cp);
			
		}
				      
	}catch (SQLException e) {
		throw new InternalErrorException(e);
	}finally{
		GeneralOperations.closeResultSet(resultSet);
		GeneralOperations.closeStatement(preparedStatement);
	}
	
	
	return cineVO;
} 




}
