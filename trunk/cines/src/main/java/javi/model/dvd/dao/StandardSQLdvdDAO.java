package javi.model.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javi.model.dvd.vo.dvdVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public abstract class StandardSQLdvdDAO implements SQLdvdDAO {


	
	public boolean exists(Connection connection, Long idDvd)
	throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idDVd FROM DVD" +
			" WHERE idDvd = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idDvd);

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

	public dvdVO find(Connection connection, String nombre)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		dvdVO dvd;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT  idDvd, director, clasificacion, descripcion, precio, disponibilidad FROM DVD " +
			"WHERE titulo LIKE ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i=1;
			preparedStatement.setString(i++, nombre);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				i = 1;
				Long idDvd = resultSet.getLong(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);
				double precio = resultSet.getDouble(i++);
				boolean disponibilidad = resultSet.getBoolean(i++);

				dvd = new dvdVO(idDvd, nombre, director, clasificacion, descripcion,precio,disponibilidad);
			}else{
				throw new InstanceNotFoundException(dvdVO.class,dvdVO.class.getName());
			}



			/* Return the value object. */
			return dvd; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	public dvdVO find(Connection connection, Long idDvd)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		dvdVO dvd;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT  titulo, director, clasificacion, descripcion, precio, disponibilidad FROM DVD " +
			"WHERE idDvd = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i=1;
			preparedStatement.setLong(i++, idDvd);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				i = 1;
				String titulo = resultSet.getString(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);
				double precio = resultSet.getDouble(i++);
				boolean disponibilidad = resultSet.getBoolean(i++);

				dvd = new dvdVO(idDvd, titulo, director, clasificacion, descripcion,precio,disponibilidad);
			}else{
				throw new InstanceNotFoundException(dvdVO.class,dvdVO.class.getName());
			}



			/* Return the value object. */
			return dvd; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}


	public List<dvdVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<dvdVO> dvds = new ArrayList<dvdVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idDvd, titulo, director, clasificacion, descripcion,precio,disponibilidad FROM DVD";

			preparedStatement = connection.prepareStatement(queryString);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				Long idDvd = resultSet.getLong(i++);
				String titulo = resultSet.getString(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);
				double precio = resultSet.getDouble(i++);
				boolean disponibilidad = resultSet.getBoolean(i++);

				dvds.add(new dvdVO(idDvd, titulo, director, clasificacion, descripcion,precio,disponibilidad));
			}



			/* Return the value object. */
			return dvds; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}


	public void update(Connection connection, dvdVO dvdVO) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "UPDATE DVD" +
			" SET idDvd = ?, titulo = ?, director = ?, clasificacion = ?, descripcion = ?, precio = ?, disponibilidad = ?," +
			" WHERE titulo = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, dvdVO.getIdDvd());
			preparedStatement.setString(i++, dvdVO.getTitulo());
			preparedStatement.setString(i++, dvdVO.getDirector());
			preparedStatement.setString(i++, dvdVO.getClasificacion());
			preparedStatement.setString(i++, dvdVO.getDescripcion());
			preparedStatement.setDouble(i++, dvdVO.getPrecio());
			preparedStatement.setBoolean(i++, dvdVO.isDisponibilidad());
			//preparedStatement.setInt(i++, adminPeliculaVO.getPreferencia());

			/* Execute query. */
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(
						dvdVO.getTitulo(), 
						dvdVO.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for login = '" + 
						dvdVO.getTitulo() + "' in table 'DVD'");
			}        

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}            

	}

	public void remove(Connection connection, long idDvd) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM DVD WHERE" +
			" idDvd = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idDvd);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idDvd,
						dvdVO.class.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}


}
