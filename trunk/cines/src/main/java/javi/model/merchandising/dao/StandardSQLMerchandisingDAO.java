package javi.model.merchandising.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javi.model.merchandising.vo.MerchandisingVO;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public abstract class StandardSQLMerchandisingDAO implements SQLMerchandisingDAO {

   
        
    public boolean exists(Connection connection, Long idMerchandising)
        throws InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT idMerchandising FROM MERCHANDISING" +
                " WHERE idMerchandising = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, idMerchandising);
            
            /* Execute query. */
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
            
        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeResultSet(resultSet);
            GeneralOperations.closeStatement(preparedStatement);
        }
        
    }
        
    public List<MerchandisingVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<MerchandisingVO> merchandising = new ArrayList<MerchandisingVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idMerchandising, descripcion, referencia, tallas, precio, disponibilidad FROM MERCHANDISING";

			preparedStatement = connection.prepareStatement(queryString);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				Long idMerchandising = resultSet.getLong(i++);
				String descripcion = resultSet.getString(i++);
				Long referencia = resultSet.getLong(i++);
				String tallas = resultSet.getString(i++);
				Long precio = resultSet.getLong(i++);
				boolean disponibilidad = resultSet.getBoolean(i++);

				merchandising.add(new MerchandisingVO(idMerchandising,descripcion,referencia,tallas,precio,disponibilidad));
			}


			/* Return the value object. */
			return merchandising; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
        
    
    public MerchandisingVO find(Connection connection,long referencia)
	throws InstanceNotFoundException, InternalErrorException {

    	PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MerchandisingVO merchandising;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idMerchandising,descripcion,tallas,precio,disponibilidad FROM MERCHANDISING " +
			"WHERE referencia = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			int i=1;
			preparedStatement.setDouble(i++, referencia);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				i = 1;
				Long idMerchandising = resultSet.getLong(i++);
				String descripcion = resultSet.getString(i++);
				String tallas = resultSet.getString(i++);
				double precio = resultSet.getDouble(i++);
				boolean disponibilidad = resultSet.getBoolean(i++);

				merchandising = new MerchandisingVO(idMerchandising,descripcion,referencia,tallas,precio,disponibilidad);
			}else{
				throw new InstanceNotFoundException(MerchandisingVO.class,MerchandisingVO.class.getName());
			}



			/* Return the value object. */
			return merchandising; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
        
    
    public void update(Connection connection, MerchandisingVO merchandisingVO) 
    throws InstanceNotFoundException, InternalErrorException {
    
    PreparedStatement preparedStatement = null;

    try {

        /* Create "preparedStatement". */
        String queryString = "UPDATE MERCHANDISING" +
            " SET idMerchandising = ?, descripcion = ? referencia = ?, tallas = ?, precio = ?, disponibilidad = ? " +
            "WHERE disponibilidad = ?";
        preparedStatement = connection.prepareStatement(queryString);
        
        /* Fill "preparedStatement". */
        
        int i = 1;
        preparedStatement.setLong(i++,merchandisingVO.getIdMerchandising());
        preparedStatement.setString(i++,merchandisingVO.getDescrip());
        preparedStatement.setLong(i++,merchandisingVO.getReferencia());
        preparedStatement.setString(i++,merchandisingVO.getTalla());
        preparedStatement.setDouble(i++,merchandisingVO.getPrecio());
        preparedStatement.setBoolean(i++,merchandisingVO.isDisponibilidad());
        
        
        /* Execute query. */
        int updatedRows = preparedStatement.executeUpdate();

        if (updatedRows == 0) {
            throw new InstanceNotFoundException(
            		merchandisingVO.getReferencia(), 
            		MerchandisingVO.class.getName());
        }

        if (updatedRows > 1) {
            throw new SQLException("Duplicate row for login = '" + 
            		merchandisingVO.getReferencia() + "' in table 'MERCHANDISING'");
        }        
        
    } catch (SQLException e) {
        throw new InternalErrorException(e);    
    } finally {
        GeneralOperations.closeStatement(preparedStatement);
    }            
    
}
   
        
    public void remove(Connection connection, Long idMerchandising) 
    	throws InstanceNotFoundException, InternalErrorException  {
    
        PreparedStatement preparedStatement = null;
        
        try {

            /* Create "preparedStatement". */
            String queryString = "DELETE FROM MERCHANDISING WHERE" +
                " idMerchandising = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, idMerchandising);
            
            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
            	throw new InstanceNotFoundException(idMerchandising,
                       MerchandisingVO.class.getName());
            }

        } catch (SQLException e) {
        	throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
    
    }

}
