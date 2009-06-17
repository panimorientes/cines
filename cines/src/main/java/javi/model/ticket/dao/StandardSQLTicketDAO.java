package javi.model.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.vo.TicketVO;


/**
 * Clase asbstacta que implementa las operaciones comunes del DAO de acceso a 
 *  la entidad persistente Ticket.
 */

public abstract class StandardSQLTicketDAO implements SQLTicketDAO{
	
	public TicketVO find(Connection connection, long idSesion, long idFila, long idButaca)
		throws InternalErrorException{
		
		TicketVO ticket = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			String query = "SELECT idTicket, estado, precio " +
							"FROM TICKET " +
							"WHERE sesion = ? AND fila = ? AND asiento = ?";
			
			preparedStatement = connection.prepareStatement(query);
			
			int  i = 1 ;
			preparedStatement.setLong(i++, idSesion);
			preparedStatement.setLong(i++, idFila);
			preparedStatement.setLong(i++, idButaca);
			
			resultSet = preparedStatement.executeQuery();
			
			
			if(!resultSet.next())
				//si no existe devuleve un nulo
				return null;
			else{
				i = 1;
				long idTicket = resultSet.getLong(i++);
				long estado = resultSet.getLong(i++);
				double precio = resultSet.getDouble(i++);
				
				
				ticket = new TicketVO(idTicket, idSesion,idFila,idButaca, new Long(estado).intValue(), precio);
			}
		}catch (SQLException  e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
		return ticket;
		
	}
	
	public TicketVO find(Connection connection, long idTicket)
	throws InternalErrorException{
	
	TicketVO ticket = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try{
		String query = "SELECT sesion, fila, asiento, estado, precio " +
						"FROM TICKET " +
						"WHERE idTicket = ?";
		
		preparedStatement = connection.prepareStatement(query);
		
		int  i = 1 ;
		preparedStatement.setLong(i++, idTicket);
		
		
		resultSet = preparedStatement.executeQuery();
		
		
		if(!resultSet.next())
			//si no existe devuelve un nulo
			return null;
		else{
			i = 1;
			long idSesion = resultSet.getLong(i++);
			long idFila = resultSet.getLong(i++);
			long idButaca = resultSet.getLong(i++);
			long estado = resultSet.getLong(i++);
			double precio = resultSet.getDouble(i++);
			
			
			ticket = new TicketVO(idTicket, idSesion,idFila,idButaca, new Long(estado).intValue(), precio);
		}
	}catch (SQLException  e) {
		throw new InternalErrorException(e);
	}finally{
		GeneralOperations.closeResultSet(resultSet);
		GeneralOperations.closeStatement(preparedStatement);
	}
	return ticket;
	
}
	
	public List<TicketVO> find(Connection connection)
	throws  InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<TicketVO> tickets = new ArrayList<TicketVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idTicket,sesion,fila,asiento,estado, precio FROM TICKET";

			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			
			
			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				Long idTicket = resultSet.getLong(i++);
				Long sesion = resultSet.getLong(i++);
				int fila = resultSet.getInt(i++);
				int asiento = resultSet.getInt(i++);
				int estado = resultSet.getInt(i++);
				Long precio = resultSet.getLong(i++);

				tickets.add(new TicketVO(idTicket,sesion,fila,asiento,estado,precio));
			}



			/* Return the value object. */
			return tickets; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}


	
	public void update(Connection connection, TicketVO ticket)
	throws InstanceNotFoundException,InternalErrorException{
	
	PreparedStatement preparedStatement = null;
	
	try{
		String query = "UPDATE TICKET " +
						"SET  sesion = ?, fila = ?, asiento = ?, estado = ?, precio = ?" +
						"WHERE idTicket = ?";
		
		preparedStatement = connection.prepareStatement(query);
		
		int  i = 1 ;
		preparedStatement.setLong(i++, ticket.getIdSesion());
		preparedStatement.setLong(i++, ticket.getFila());
		preparedStatement.setLong(i++, ticket.getButaca());
		preparedStatement.setInt(i++, ticket.getEstado());
		preparedStatement.setDouble(i++, ticket.getPrecio());
		
		preparedStatement.setLong(i++, ticket.getIdTicket());
		
		/* Execute query. */
		int updatedRows = preparedStatement.executeUpdate();

		if (updatedRows == 0) {
			throw new InstanceNotFoundException(
					ticket.getIdTicket(), 
					PeliculaVO.class.getName());
		}

		if (updatedRows > 1) {
			throw new SQLException("Duplicate row for login = '" + 
					ticket.getIdTicket() + "' in table 'TICKET'");
		}        

	} catch (SQLException e) {
		throw new InternalErrorException(e);    
	} finally {
		GeneralOperations.closeStatement(preparedStatement);
	}            
	
}
	
	public void remove(Connection connection, long sesion)
	throws InstanceNotFoundException,InternalErrorException{
	
	PreparedStatement preparedStatement = null;
	
	try{
		String query = "DELETE FROM TICKET WHERE " +
						"sesion = ?";
		
		preparedStatement = connection.prepareStatement(query);
		
		/* Fill "preparedStatement". */
		int i = 1;
		preparedStatement.setLong(i++, sesion);

		/* Execute query. */
		int removedRows = preparedStatement.executeUpdate();

		if (removedRows == 0) {
			throw new InstanceNotFoundException(sesion, SesionVO.class.getName());
		}

	} catch (SQLException e) {
		throw new InternalErrorException(e);
	} finally {
		GeneralOperations.closeStatement(preparedStatement);
	}

}

	
}
