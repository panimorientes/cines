package javi.model.pedido.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javi.model.pedido.vo.PedidoVO;
import javi.model.sesion.vo.SesionVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLPedidoDAO implements SQLPedidoDAO {

    public PedidoVO create(Connection connection, PedidoVO userPedidoVO)
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        

       
        PreparedStatement preparedStatement = null;
    
        try {

            /* Create "preparedStatement". */
            String queryString = "INSERT INTO PEDIDO" +
                " (fecha, Ulogin) VALUES (?, ?)";
            
            preparedStatement = connection.prepareStatement(queryString);    
            
            /* Fill "preparedStatement". */
            int i = 1;
           
            preparedStatement.setDate(i++, userPedidoVO.getFechaPedido());
            preparedStatement.setString(i++, userPedidoVO.getLogin());
            
            
            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();
        
            if (insertedRows == 0) {
                throw new SQLException("Can not add row to table" +
                    " 'PEDIDO'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Duplicate row for idPedido = '" + 
                    userPedidoVO.getIdPedido() + "' in table 'PEDIDO'");
            }
            
            
            
            es.udc.fbellas.j2ee.util.sql.EntityIdentifierRetriever entityIdentifierRetriever = 
    			es.udc.fbellas.j2ee.util.sql.EntityIdentifierRetrieverFactory.getRetriever();
    	
    		long idpedido = entityIdentifierRetriever.getGeneratedIdentifier(connection);
    		
    		userPedidoVO.setIdPedido(new Long(idpedido));
    		

            
            
            return userPedidoVO;
        
        } catch (SQLException e) {
            throw new InternalErrorException(e);
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }   
    
    public List<PedidoVO> find(Connection connection, String login)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PedidoVO> pedido = new ArrayList<PedidoVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT id_pedido,fecha  FROM PEDIDO WHERE Ulogin LIKE ? ORDER BY fecha DESC";
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, login);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				i = 1;
				
				Long idPedido = resultSet.getLong(i++);
				Date fecha = resultSet.getDate(i++);
				
				pedido.add(new PedidoVO(idPedido, fecha, login));
			}

			/* Return the value object. */
			return pedido;

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}

	public List<PedidoVO> find(Connection connection)
			throws InternalErrorException, InstanceNotFoundException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PedidoVO> pedido = new ArrayList<PedidoVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT id_pedido,fecha,Ulogin  FROM PEDIDO ORDER BY fecha DESC";
			preparedStatement = connection.prepareStatement(queryString);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();
			int i;
			while (resultSet.next()) {
				i = 1;
				
				Long idPedido = resultSet.getLong(i++);
				Date fecha = resultSet.getDate(i++);
				String login = resultSet.getString(i++);
				
				pedido.add(new PedidoVO(idPedido, fecha, login));
			}

			/* Return the value object. */
			return pedido;

		} catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
	}
}