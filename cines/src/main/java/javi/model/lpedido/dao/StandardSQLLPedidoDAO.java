package javi.model.lpedido.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javi.model.lpedido.vo.LPedidoVO;
import javi.model.pedido.vo.PedidoVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLLPedidoDAO implements SQLLPedidoDAO {

    public void create(Connection connection, LPedidoVO userLPedidoVO)
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
        /* Check if the pelicula already exists. */
       
    	
        PreparedStatement preparedStatement = null;
    
        try {

            /* Create "preparedStatement". */
            String queryString = "INSERT INTO LPEDIDO" +
                " (id_producto, num_unidades, id_pedido, tipo) VALUES (?, ?, ?, ?)";
            
            preparedStatement = connection.prepareStatement(queryString);    
            
            /* Fill "preparedStatement". */
            int i = 1;
            
            preparedStatement.setLong(i++, userLPedidoVO.getIdProducto());
            preparedStatement.setLong(i++, userLPedidoVO.getNumUnidades());
            preparedStatement.setLong(i++, userLPedidoVO.getIdPedido());
            preparedStatement.setInt(i++, userLPedidoVO.getTipo());
            
            
            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();
        
            if (insertedRows == 0) {
                throw new SQLException("Can not add row to table" +
                    " 'LPEDIDO'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Duplicate row for idPedido = '" + 
                    userLPedidoVO.getIdPedido() + "' in table 'LPEDIDO'");
            }
        
        } catch (SQLException e) {
            throw new InternalErrorException(e);
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
    
    public List<LPedidoVO> recuperarLPedido(Connection connection, List<PedidoVO> pedidos)
	throws InternalErrorException, InstanceNotFoundException{
    	
    	PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<LPedidoVO> lpedidos = new ArrayList<LPedidoVO>();
		try {
			
			//HAY QUE RECORRER LA LISTA PEDIDOS RECUPERANDO IDPEDIDO Y BUSCANDO LAS LINEAS
			for(int j=0; j<pedidos.size(); j++){
				
				long idPedido=pedidos.get(j).getIdPedido();	
				
				/* Create "preparedStatement". */
				String queryString = "SELECT id_producto,num_linea,num_unidades, tipo  FROM LPEDIDO WHERE id_pedido = ?";
				
				preparedStatement = connection.prepareStatement(queryString);
	
				int i = 1;
				preparedStatement.setLong(i++, idPedido);
	
				/* Execute query. */
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					i = 1;
					
					long id_producto = resultSet.getLong(i++);
					long num_linea = resultSet.getLong(i++);
					long num_unidades = resultSet.getLong(i++);
					int tipo = resultSet.getInt(i++);
					
					lpedidos.add(new LPedidoVO(idPedido,id_producto,num_unidades,tipo, num_linea));
				}
			
			}

			/* Return the value object. */
			return lpedidos;

			
		}catch (SQLException e) {
			throw new InternalErrorException(e);
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

    	
    }
    
    
}
   
