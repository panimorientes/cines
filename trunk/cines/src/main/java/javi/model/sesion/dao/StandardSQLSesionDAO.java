package javi.model.sesion.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javi.model.sesion.vo.SesionVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public abstract class StandardSQLSesionDAO implements SQLSesionDAO {

	
	public boolean exists(Connection connection, Calendar fecha, Time hora,
			Long idSala, Long idCine) throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT fecha, hora FROM SESION"
				+ " WHERE fecha = ? AND hora = ? AND Sid_Sala = ? AND idCine = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			Timestamp date = new Timestamp(fecha.getTime().getTime());
			preparedStatement.setTimestamp(i++, date);
			preparedStatement.setTime(i++, hora);
			preparedStatement.setLong(i++, idSala);
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

	public List<SesionVO> find(Connection connection, Long idCine)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<SesionVO> sesion = new ArrayList<SesionVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idSesion, fecha, hora, precio, numerada, idPelicula, Sid_sala  FROM SESION WHERE idCine = ? AND fecha >= CURDATE()";
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, idCine);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				i = 1;
				
				Long idSession = resultSet.getLong(i++);
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(resultSet.getTimestamp(i++));
				Time hora = resultSet.getTime(i++);
				double precio = resultSet.getDouble(i++);
				boolean numerada = resultSet.getBoolean(i++);
				Long idPelicula = resultSet.getLong(i++);
				Long idSala = resultSet.getLong(i++);

				sesion.add(new SesionVO(idSession, fecha, hora, precio,
						numerada, idPelicula, idSala, idCine));
			}

			/* Return the value object. */
			return sesion;

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}
	
	//XXX no se supone que la clave primaria de Sesion es idSesion??
	//    entonces,hay varias sesiones con el mismo idSesion??
	public List<SesionVO> find1(Connection connection, Long idSesion)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<SesionVO> sesion = new ArrayList<SesionVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT fecha, hora, precio, numerada, idPelicula, Sid_sala, idCine  FROM SESION WHERE idSesion = ?";
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setLong(i++, idSesion);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				i = 1;
				
				
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(resultSet.getTimestamp(i++));
				Time hora = resultSet.getTime(i++);
				double precio = resultSet.getDouble(i++);
				boolean numerada = resultSet.getBoolean(i++);
				Long idPelicula = resultSet.getLong(i++);
				Long idSala = resultSet.getLong(i++);
				Long idCine = resultSet.getLong(i++);

				sesion.add(new SesionVO(idSesion, fecha, hora, precio,
						numerada, idPelicula, idSala, idCine));
			}

			/* Return the value object. */
			return sesion;

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}
	
	public List<SesionVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<SesionVO> sesion = new ArrayList<SesionVO>();

		try {
			//XXX mira en internet la sintaxis
			/* Create "preparedStatement". */
			String queryString = "SELECT idSesion, fecha, hora, precio, numerada, idPelicula, Sid_sala,idCine  FROM SESION WHERE fecha >= CURDATE()";
			preparedStatement = connection.prepareStatement(queryString);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int i = 1;
				
				Long idSession = resultSet.getLong(i++);
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(resultSet.getTimestamp(i++));
				Time hora = resultSet.getTime(i++);
				double precio = resultSet.getDouble(i++);
				boolean numerada = resultSet.getBoolean(i++);
				Long idPelicula = resultSet.getLong(i++);
				Long idSala = resultSet.getLong(i++);
				Long idCine = resultSet.getLong(i++);
				

				sesion.add(new SesionVO(idSession, fecha, hora, precio,
						numerada, idPelicula, idSala, idCine));
			}

			/* Return the value object. */
			return sesion;

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}


	public void update(Connection connection, SesionVO adminSesionVO)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "UPDATE SESION"
				+ " SET fecha = ?, hora = ?, precio = ?, numerada = ?, idPelicula = ?, Sid_sala = ?, idCine = ? "
				+ " WHERE fecha = ? AND hora = ? AND Sid_sala = ? AND idCine = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;

			Timestamp date = new Timestamp(adminSesionVO.getFecha().getTime().getTime());
			preparedStatement.setTimestamp(i++, date);
			preparedStatement.setTime(i++, adminSesionVO.getHora());
			preparedStatement.setDouble(i++, adminSesionVO.getPrecio());
			preparedStatement.setLong(i++, adminSesionVO.getIdPelicula());
			preparedStatement.setLong(i++, adminSesionVO.getIdSala());
			preparedStatement.setLong(i++, adminSesionVO.getIdCine());

			/* Execute query. */
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(adminSesionVO.getFecha(),
						SesionVO.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for fecha = '"
						+ adminSesionVO.getFecha() + "' in table 'SESION'");
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}

	}

	public void remove(Connection connection, Calendar fecha, Time hora,
			Long idSala, Long idCine) throws InstanceNotFoundException,
			InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM SESION WHERE"
				+ " fecha = ? AND hora = ? AND Sid_sala = ? AND idCine = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			Timestamp date = new Timestamp(fecha.getTime().getTime());
			preparedStatement.setTimestamp(i++, date);
			preparedStatement.setTime(i++, hora);
			preparedStatement.setLong(i++, idSala);
			preparedStatement.setLong(i++, idCine);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(fecha, SesionVO.class
						.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}

	}
	
	
	
	public void remove(Connection connection, long idSesion) throws InstanceNotFoundException,
			InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM SESION WHERE"
				+ " idSesion = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idSesion);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idSesion, SesionVO.class
						.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}

	}
	
	public void removeP(Connection connection, long idPelicula) throws InstanceNotFoundException,
	InternalErrorException {

PreparedStatement preparedStatement = null;

try {

	/* Create "preparedStatement". */
	String queryString = "DELETE FROM SESION WHERE"
		+ " idPelicula = ?";
	preparedStatement = connection.prepareStatement(queryString);

	/* Fill "preparedStatement". */
	int i = 1;
	preparedStatement.setLong(i++, idPelicula);

	/* Execute query. */
	int removedRows = preparedStatement.executeUpdate();

	if (removedRows == 0) {
		//throw new InstanceNotFoundException(idPelicula, SesionVO.class.getName());
	}

} catch (SQLException e) {
	throw new InternalErrorException(e);
} finally {
	GeneralOperations.closeStatement(preparedStatement);
}

}
	
	public SesionVO find(Connection connection, long idSesion) 
		throws InternalErrorException, InstanceNotFoundException{
		
		SesionVO sesionVO = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			String query = "SELECT fecha, hora, numerada, idPelicula, Sid_sala, idCine " +
					       "FROM SESION " +
					       "WHERE idSesion = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, idSesion);
			
			/*Execute statment*/
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()){
				//throw new InstanceNotFoundException(new Long(idSesion),SesionVO.class.getName());
			}else{
				
				int i = 1;
				
				//Long id = resultSet.getLong(i++);
				Date fecha = resultSet.getDate(i++);
				Time hora = resultSet.getTime(i++);
				boolean numerada = resultSet.getBoolean(i++);
				Long idPelicula = resultSet.getLong(i++);
				long sala = resultSet.getLong(i++);
				long idCine = resultSet.getLong(i++);
				
				//Paso de date a calendar
				Calendar fecha2 = Calendar.getInstance();
				fecha2.setTime(fecha);
				
				sesionVO = new SesionVO(idSesion, fecha2, hora, numerada, idPelicula, sala, idCine );
				
			}
					      
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		
		
		return sesionVO;
	} 

}
