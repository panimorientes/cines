package javi.model.merchandising.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqlMerchandisingDAO extends StandardSQLMerchandisingDAO{
	


	public void create(Connection connection, String descripcion, long referencia, String talla,double precio, boolean disponibilidad) throws DuplicateInstanceException,InternalErrorException{
		
	
		PreparedStatement preparedStatement = null;
		
		try{
			
			String query = "INSERT INTO MERCHANDISING" +
            " (descripcion,referencia, tallas, precio, disponibilidad) VALUES (?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			/* Fill prepared statement */
			int i = 1;
		
			preparedStatement.setString(i++, descripcion);
            preparedStatement.setLong(i++, referencia);
            preparedStatement.setString(i++, talla);
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
