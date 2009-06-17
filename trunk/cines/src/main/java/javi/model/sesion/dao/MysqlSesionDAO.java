package javi.model.sesion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javi.model.sesion.vo.SesionVO;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqlSesionDAO extends StandardSQLSesionDAO{

	public SesionVO create(Connection connection, SesionVO sesionVO)
	throws InternalErrorException {

		/* Check if the pelicula already exists. */
		/*
		 * if (exists(connection, adminPeliculaVO.getTitulo())) { throw new
		 * DuplicateInstanceException(adminPeliculaVO.getTitulo(),
		 * AdminPeliculaVO.class.getName()); }
		 */

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "INSERT INTO SESION"
				+ " (fecha, hora, precio, numerada, idPelicula, Sid_sala, idCine) VALUES (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			
			//XXX que hay en "date"??
			Timestamp date = new Timestamp(sesionVO.getFecha().getTime()
					.getTime());

			preparedStatement.setTimestamp(i++, date);
			preparedStatement.setTime(i++, sesionVO.getHora());
			preparedStatement.setDouble(i++, sesionVO.getPrecio());
			preparedStatement.setBoolean(i++, sesionVO.isNumerada());
			preparedStatement.setLong(i++, sesionVO.getIdPelicula());
			preparedStatement.setLong(i++, sesionVO.getIdSala());
			preparedStatement.setLong(i++, sesionVO.getIdCine());

			/* Execute query. */
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table" + " 'SESION'");
			}

			if (insertedRows > 1) {
				throw new SQLException("Duplicate row for fecha = '"
						+ sesionVO.getFecha() + "' in table 'SESION'");
			}
			
			
			es.udc.fbellas.j2ee.util.sql.EntityIdentifierRetriever entityIdentifierRetriever = 
    			es.udc.fbellas.j2ee.util.sql.EntityIdentifierRetrieverFactory.getRetriever();
    	
    		Long idSesion = entityIdentifierRetriever.getGeneratedIdentifier(connection);
    		
    		sesionVO.setIdSesion(idSesion);
    		

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}

		return sesionVO;
	}

	
}
