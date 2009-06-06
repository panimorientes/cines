package javi.model.pelicula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqlPeliculaDAO extends StandardSQLPeliculaDAO{
	


	public void create(Connection connection, String titulo, String director, String clasificacion, String descripcion) throws DuplicateInstanceException,InternalErrorException{
		
		/* Check if the pelicula already exists. 
		if (exists(connection, idPelicula)) {
			
				throw new DuplicateInstanceException(idPelicula,
						PeliculaVO.class.getName());
		
			 	}*/
		
		PreparedStatement preparedStatement = null;
		
		try{
			
			String query = "INSERT INTO PELICULA" +
			" (titulo, director, clasificacion, descripcion) VALUES (?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			/* Fill prepared statement */
			int i = 1;
			//preparedStatement.setLong(i++, idPelicula);
			preparedStatement.setString(i++, titulo);
			preparedStatement.setString(i++, director);
			preparedStatement.setString(i++, clasificacion);
			preparedStatement.setString(i++, descripcion);
			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeStatement(preparedStatement);
		}
		
		
	}
	
}
