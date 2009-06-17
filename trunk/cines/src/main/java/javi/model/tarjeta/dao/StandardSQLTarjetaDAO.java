package javi.model.tarjeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javi.model.tarjeta.vo.TarjetaVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLTarjetaDAO implements SQLTarjetaDAO {

    public void create(Connection connection, TarjetaVO userTarjetaVO)
    throws  InternalErrorException{
        
        /* Check if the user already exists. */
        if (exists(connection, userTarjetaVO.getLogin())) {
        	
        		try {
					remove(connection,userTarjetaVO.getLogin());
				} catch (InstanceNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            		
        }
        
        PreparedStatement preparedStatement = null;
    
        try {

            /* Create "preparedStatement". */
            String queryString = "INSERT INTO TARJETA" +
                " (cod_c_c,Tlogin) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(queryString);    
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, userTarjetaVO.getNumTarjeta());
            preparedStatement.setString(i++, userTarjetaVO.getLogin());
            
                        
            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();
        
            if (insertedRows == 0) {
                throw new SQLException("Can not add row to table" +
                    " 'TARJETA'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Duplicate row for login = '" + 
                    userTarjetaVO.getLogin() + "' in table 'TARJETA'");
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
            String queryString = "SELECT Tlogin FROM TARJETA" +
                " WHERE Tlogin = ?";
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
        
    //XXX el metodo FIND creo que esta mal, no se cuando saca de la
    //    BD el numero de tarjeta para poder hacer TarjetaVO
    public TarjetaVO find(Connection connection, String login)
        throws InstanceNotFoundException, InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT Tlogin  FROM TARJETA WHERE" +
                " Tlogin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, login);
            
            /* Execute query. */
            resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                throw new InstanceNotFoundException(login,
                    TarjetaVO.class.getName());
            }

            /* Get results. */
            i = 1;
            Long tarjeta = resultSet.getLong(i++);
           
            
            /* Return the value object. */
            return new TarjetaVO(login, tarjeta);
            
        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeResultSet(resultSet);
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
        
   
        
    public void remove(Connection connection, String login) 
    	throws InstanceNotFoundException, InternalErrorException  {
    
        PreparedStatement preparedStatement = null;
        
        try {

            /* Create "preparedStatement". */
            String queryString = "DELETE FROM TARJETA WHERE" +
                " Tlogin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, login );
            
            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
            	throw new InstanceNotFoundException(login,
                        TarjetaVO.class.getName());
            }

        } catch (SQLException e) {
        	throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
    
    }

}
