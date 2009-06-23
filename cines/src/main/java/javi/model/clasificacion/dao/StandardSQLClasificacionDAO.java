package javi.model.clasificacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javi.model.clasificacion.vo.ClasificacionVO;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLClasificacionDAO implements SQLClasificacionDAO {



	public List<ClasificacionVO> findAll(Connection connection)
	throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ClasificacionVO> clasificaciones = new ArrayList<ClasificacionVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT nombre FROM CLASIFICACION ORDER BY nombre";

			preparedStatement = connection.prepareStatement(queryString);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				String nombre = resultSet.getString(i++);

				clasificaciones.add(new ClasificacionVO(nombre));
			}

			/* Return the value object. */
			return clasificaciones; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}

}
