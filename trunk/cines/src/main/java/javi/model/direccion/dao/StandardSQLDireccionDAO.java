package javi.model.direccion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javi.model.direccion.vo.DireccionVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLDireccionDAO implements SQLDireccionDAO {

    public void create(Connection connection, DireccionVO adminDireccionVO)
    throws  InternalErrorException{
        
        /* Check if the user already exists. */
       /* if (exists(connection, userDireccionVO.getLogin())) {
        	throw new SQLException("Duplicate row for login = '" + 
                    userDireccionVO.getLogin() + "' in table 'DIRPOSTAL'");
        			
        }*/
        
        PreparedStatement preparedStatement = null;
    
        try {

            /* Create "preparedStatement". */
            String queryString = "INSERT INTO DIRPOSTAL" +
                " (cp, ciudad, direccion, numero, Ulogin) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryString);    
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, adminDireccionVO.getCp());
            preparedStatement.setString(i++, adminDireccionVO.getCiudad());
            preparedStatement.setString(i++, adminDireccionVO.getDireccion());
            preparedStatement.setLong(i++, adminDireccionVO.getNumero());
            preparedStatement.setString(i++, adminDireccionVO.getLogin());
            
                        
            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();
        
            if (insertedRows == 0) {
                throw new SQLException("Can not add row to table" +
                    " 'TARJETA'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Duplicate row for login = '" + 
                    adminDireccionVO.getLogin() + "' in table 'DIRPOSTAL'");
            }
        
        } catch (SQLException e) {
            throw new InternalErrorException(e);
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
        
    public boolean exists(Connection connection, String login)
        throws InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT Ulogin FROM DIRPOSTAL" +
                " WHERE Ulogin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, login);
            
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
        
    public DireccionVO find(Connection connection, String login)
        throws InstanceNotFoundException, InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT cp, ciudad, direccion, numero, Ulogin  FROM DIRPOSTAL WHERE" +
                " Ulogin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, login);
            
            /* Execute query. */
            resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                throw new InstanceNotFoundException(login,
                    DireccionVO.class.getName());
            }

            /* Get results. */
            i = 1;
            Long cp = resultSet.getLong(i++);
            String ciudad = resultSet.getString(i++);
            String direccion = resultSet.getString(i++);
            Long numero = resultSet.getLong(i++);
            
            
            /* Return the value object. */
            return new DireccionVO(cp, ciudad, direccion, numero, login);
            
        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeResultSet(resultSet);
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
        
    
    public void update(Connection connection, DireccionVO adminDireccionVO) 
    throws InstanceNotFoundException, InternalErrorException {
    
    PreparedStatement preparedStatement = null;

    try {

        /* Create "preparedStatement". */
        String queryString = "UPDATE DIRPOSTAL" +
            " SET cp = ?, ciudad = ?, direccion = ?, numero = ? " +
            "WHERE Ulogin = ?";
        preparedStatement = connection.prepareStatement(queryString);
        
        /* Fill "preparedStatement". */
        
        int i = 1;
        preparedStatement.setLong(i++,adminDireccionVO.getCp());
        preparedStatement.setString(i++,adminDireccionVO.getCiudad());
        preparedStatement.setString(i++,adminDireccionVO.getDireccion());
        preparedStatement.setLong(i++,adminDireccionVO.getNumero());
        preparedStatement.setString(i++,adminDireccionVO.getLogin());
        
        
        /* Execute query. */
        int updatedRows = preparedStatement.executeUpdate();

        if (updatedRows == 0) {
            throw new InstanceNotFoundException(
                adminDireccionVO.getLogin(), 
                DireccionVO.class.getName());
        }

        if (updatedRows > 1) {
            throw new SQLException("Duplicate row for login = '" + 
               adminDireccionVO.getLogin() + "' in table 'DIRPOSTAL'");
        }        
        
    } catch (SQLException e) {
        throw new InternalErrorException(e);    
    } finally {
        GeneralOperations.closeStatement(preparedStatement);
    }            
    
}
   
        
    public void remove(Connection connection, Long cp) 
    	throws InstanceNotFoundException, InternalErrorException  {
    
        PreparedStatement preparedStatement = null;
        
        try {

            /* Create "preparedStatement". */
            String queryString = "DELETE FROM DIRPOSTAL WHERE" +
                " cp = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, cp );
            
            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
            	throw new InstanceNotFoundException(cp,
                       DireccionVO.class.getName());
            }

        } catch (SQLException e) {
        	throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
    
    }

}
