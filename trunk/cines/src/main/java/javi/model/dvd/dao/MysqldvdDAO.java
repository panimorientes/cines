package javi.model.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqldvdDAO extends StandardSQLdvdDAO{
	


	public void create(Connection connection, String titulo, String director, String clasificacion, String descripcion,double precio, boolean disponibilidad) throws DuplicateInstanceException,InternalErrorException{
		
	
		PreparedStatement preparedStatement = null;
		
		try{
			
			String query = "INSERT INTO DVD" +
			" (titulo, director, clasificacion, descripcion, precio, disponibilidad) VALUES (?, ?, ?, ?, ?, ?)";;
			
			preparedStatement = connection.prepareStatement(query);
			
			/* Fill prepared statement */
			int i = 1;
			
			preparedStatement.setString(i++, titulo);
			preparedStatement.setString(i++, director);
			preparedStatement.setString(i++, clasificacion);
			preparedStatement.setString(i++, descripcion);
			preparedStatement.setDouble(i++, precio);
			preparedStatement.setBoolean(i++, disponibilidad);

			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeStatement(preparedStatement);
		}
		
		
	}
	
}
