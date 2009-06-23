package javi.model.userprofile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLUserProfileDAO implements SQLUserProfileDAO {

    public void create(Connection connection, UserProfileVO userProfileVO)
        throws DuplicateInstanceException, InternalErrorException {
        
        /* Check if the user already exists. */
        if (exists(connection, userProfileVO.getLoginName())) {
            throw new DuplicateInstanceException(userProfileVO.getLoginName(),
                UserProfileVO.class.getName());
        }

        PreparedStatement preparedStatement = null;
    
        try {

            /* Create "preparedStatement". */
            String queryString = "INSERT INTO USUARIO" +
                " (login, pass, nombre, ape1, ape2, e_mail," +
                " lenguaje, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(queryString);    
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, userProfileVO.getLoginName());
            preparedStatement.setString(i++, 
                userProfileVO.getEncryptedPassword());
            preparedStatement.setString(i++, 
                userProfileVO.getUserProfileDetailsVO().getNombre());
            preparedStatement.setString(i++, 
                userProfileVO.getUserProfileDetailsVO().getApe1());
            preparedStatement.setString(i++, 
                    userProfileVO.getUserProfileDetailsVO().getApe2());
            preparedStatement.setString(i++, 
                userProfileVO.getUserProfileDetailsVO().getEmail());
            preparedStatement.setString(i++, 
                userProfileVO.getUserProfileDetailsVO().getLanguage());
            preparedStatement.setString(i++, 
                userProfileVO.getUserProfileDetailsVO().getCountry());
                        
            /* Execute query. */
            int insertedRows = preparedStatement.executeUpdate();
        
            if (insertedRows == 0) {
                throw new SQLException("Can not add row to table" +
                    " 'USUARIO'");
            }

            if (insertedRows > 1) {
                throw new SQLException("Duplicate row for login = '" + 
                    userProfileVO.getLoginName() + "' in table 'USUARIO'");
            }
        
        } catch (SQLException e) {
            throw new InternalErrorException(e);
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
        
    public boolean exists(Connection connection, String loginName)
        throws InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT login FROM USUARIO" +
                " WHERE login = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, loginName);
            
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
        
    public UserProfileVO find(Connection connection, String loginName)
        throws InstanceNotFoundException, InternalErrorException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "SELECT pass, nombre, ape1, ape2," +
                " e_mail, lenguaje, pais  FROM USUARIO WHERE" +
                " login = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, loginName);
            
            /* Execute query. */
            resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {     
            	throw new InstanceNotFoundException(loginName, UserProfileVO.class.getName());
            }
            else {
	            /* Get results. */
	            i = 1;
	            String pass = resultSet.getString(i++);
	            String nombre = resultSet.getString(i++);
	            String ape1 = resultSet.getString(i++);
	            String ape2 = resultSet.getString(i++);
	            String email = resultSet.getString(i++);
	            String lenguaje = resultSet.getString(i++);
	            String pais = resultSet.getString(i++);
	            UserProfileDetailsVO userProfileVODetails =
	                new UserProfileDetailsVO(nombre, ape1, ape2, email, lenguaje, pais);
	            
            	/* Return the value object. */
            	return new UserProfileVO(loginName, pass, 
            			userProfileVODetails);
            }
        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeResultSet(resultSet);
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
        
    public void update(Connection connection, UserProfileVO userProfileVO) 
        throws InstanceNotFoundException, InternalErrorException {
        
        PreparedStatement preparedStatement = null;

        try {

            /* Create "preparedStatement". */
            String queryString = "UPDATE USUARIO" +
                " SET pass = ?, nombre = ?, ape1 = ?, ape2 = ?," +
                " e_mail = ?, lenguaje = ?, pais = ?  WHERE login = ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, 
                userProfileVO.getEncryptedPassword());
            preparedStatement.setString(i++,
                userProfileVO.getUserProfileDetailsVO().getNombre());
            preparedStatement.setString(i++,
                userProfileVO.getUserProfileDetailsVO().getApe1());
            preparedStatement.setString(i++,
                    userProfileVO.getUserProfileDetailsVO().getApe2());
            preparedStatement.setString(i++,
                userProfileVO.getUserProfileDetailsVO().getEmail());
            preparedStatement.setString(i++,
                userProfileVO.getUserProfileDetailsVO().getLanguage());
            preparedStatement.setString(i++,
                userProfileVO.getUserProfileDetailsVO().getCountry());
            preparedStatement.setString(i++, userProfileVO.getLoginName());
            
            /* Execute query. */
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows == 0) {
                throw new InstanceNotFoundException(
                    userProfileVO.getLoginName(), 
                    UserProfileVO.class.getName());
            }

            if (updatedRows > 1) {
                throw new SQLException("Duplicate row for login = '" + 
                    userProfileVO.getLoginName() + "' in table 'USUARIO'");
            }        
            
        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }            
        
    }
        
    public void remove(Connection connection, String loginName) 
        throws InstanceNotFoundException, InternalErrorException {
    
        PreparedStatement preparedStatement = null;
        
        try {

            /* Create "preparedStatement". */
            String queryString = "DELETE FROM USUARIO WHERE" +
                " login LIKE ?";
            preparedStatement = connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, loginName);
            
            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
                throw new InstanceNotFoundException(loginName,
                    UserProfileVO.class.getName());
            }

        } catch (SQLException e) {
            throw new InternalErrorException(e);    
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
    
    }

}
