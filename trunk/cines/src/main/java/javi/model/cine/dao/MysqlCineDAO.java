package javi.model.cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqlCineDAO extends StandardSQLCineDAO{
	


	public void create(Connection connection,String nombre, long numSalas, long cp) throws DuplicateInstanceException,InternalErrorException{
		

		PreparedStatement preparedStatement = null;
		
		try{
			
			String query = "INSERT INTO CINE" +
			" (nombre, num_salas, C_cp) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			
			/* Fill prepared statement */
			int i = 1;
			preparedStatement.setString(i++, nombre);
			preparedStatement.setLong(i++, numSalas);
			preparedStatement.setLong(i++, cp);
			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeStatement(preparedStatement);
		}
		
		
	}
	
}
