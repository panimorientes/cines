package javi.model.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public class MysqlTicketDAO extends StandardSQLTicketDAO{

	public void create(Connection connection, long sesion, long fila, long asiento,double precio) throws InternalErrorException{
		
		PreparedStatement preparedStatement = null;
		
		try{
			
			String query = "INSERT INTO TICKET(sesion, fila, asiento, estado, precio)" +
						   " VALUES (?,?,?,0,?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			/* Fill prepared statement */
			int i = 1;
			preparedStatement.setLong(i++, sesion);
			preparedStatement.setLong(i++, fila);
			preparedStatement.setLong(i++, asiento);
			preparedStatement.setDouble(i++, precio);
			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeStatement(preparedStatement);
		}
		
		
	}
	
}
