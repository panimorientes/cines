package javi.model.sala.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javi.model.sala.vo.SalaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLSalaDAO implements SQLSalaDAO {

   
	public void create(Connection connection, SalaVO salaVO)
    throws DuplicateInstanceException,InternalErrorException {
    
    /* Check if the sala already exists. */
    if (exists(connection, salaVO.getIdSala(), salaVO.getIdCine())) {
        throw new DuplicateInstanceException(salaVO.getIdSala(),
            SalaVO.class.getName());
    }

    PreparedStatement preparedStatement = null;

    try {

        /* Create "preparedStatement". */
        String queryString = "INSERT INTO SALA" +
            " (id_sala, filas, asientos, idCine) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(queryString);    
        
        /* Fill "preparedStatement". */
        int i = 1;
        preparedStatement.setLong(i++, salaVO.getIdSala());
        preparedStatement.setLong(i++, salaVO.getNumFilas());
        preparedStatement.setLong(i++, salaVO.getAsientos());
        preparedStatement.setLong(i++, salaVO.getIdCine());
        
   
        /* Execute query. */
        int insertedRows = preparedStatement.executeUpdate();
    
        if (insertedRows == 0) {
            throw new SQLException("Can not add row to table" +
                " 'SALA'");
        }

        if (insertedRows > 1) {
            throw new SQLException("Duplicate row for id sala = '" + 
                salaVO.getIdSala() + "' in table 'SALA'");
        }
    
    } catch (SQLException e) {
        throw new InternalErrorException(e);
    } finally {
        GeneralOperations.closeStatement(preparedStatement);
    }    
    
}
    
public boolean exists(Connection connection, Long idSala , long idCine)
    throws InternalErrorException {
    
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

        /* Create "preparedStatement". */
        String queryString = "SELECT id_sala, idCine FROM SALA" +
            " WHERE id_sala = ? AND idCine = ?";
        preparedStatement = connection.prepareStatement(queryString);
        
        /* Fill "preparedStatement". */
        int i = 1;
        preparedStatement.setLong(i++, idSala);
        preparedStatement.setLong(i++, idCine);
        
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
    
public List<SalaVO> find(Connection connection, Long idCine)
throws InstanceNotFoundException, InternalErrorException {

PreparedStatement preparedStatement = null;
ResultSet resultSet = null;
List<SalaVO> salas = new ArrayList<SalaVO>();

try {

    /* Create "preparedStatement". */
    String queryString = "SELECT id_sala, filas, asientos, idCine FROM SALA WHERE idCine = ?";
    
    preparedStatement = connection.prepareStatement(queryString);
    
    /* Fill "preparedStatement". */
    int i=1;
    preparedStatement.setLong(i++, idCine);
    
    /* Execute query. */
    resultSet = preparedStatement.executeQuery();
    
    while(resultSet.next()){
    	i=1;
    	Long idSala = resultSet.getLong(i++);
    	Long filas = resultSet.getLong(i++);
    	Long asientos = resultSet.getLong(i++);
    	
    	
    	salas.add(new SalaVO(idSala, filas, asientos, idCine));
    }     
    
    /* Return the value object. */
    return salas; 

    
} catch (SQLException e) {
    throw new InternalErrorException(e);    
} finally {
    GeneralOperations.closeResultSet(resultSet);
    GeneralOperations.closeStatement(preparedStatement);
}    

}
    
public void update(Connection connection, SalaVO salaVO) 
    throws InstanceNotFoundException, InternalErrorException {
    
    PreparedStatement preparedStatement = null;

    try {

        /* Create "preparedStatement". */
        String queryString = "UPDATE SALA" +
            " SET id_sala = ?, filas = ?, asientos = ?, idCine = ?" +
            " WHERE id_sala = ?";
        preparedStatement = connection.prepareStatement(queryString);
        
        /* Fill "preparedStatement". */
        int i = 1;
        preparedStatement.setLong(i++, salaVO.getIdSala());
        preparedStatement.setLong(i++, salaVO.getNumFilas());
        preparedStatement.setLong(i++, salaVO.getAsientos());
        preparedStatement.setLong(i++, salaVO.getIdCine());
        
        /* Execute query. */
        int updatedRows = preparedStatement.executeUpdate();

        if (updatedRows == 0) {
            throw new InstanceNotFoundException(
                salaVO.getIdSala(), 
                SalaVO.class.getName());
        }

        if (updatedRows > 1) {
            throw new SQLException("Duplicate row for sala = '" + 
                salaVO.getIdSala() + "' in table 'SALA'");
        }        
        
    } catch (SQLException e) {
        throw new InternalErrorException(e);    
    } finally {
        GeneralOperations.closeStatement(preparedStatement);
    }            
    
}
    
public void remove(Connection connection, Long idSala, long idCine) 
    throws InstanceNotFoundException, InternalErrorException {

    PreparedStatement preparedStatement = null;
    
    try {

        /* Create "preparedStatement". */
        String queryString = "DELETE FROM SALA WHERE" +
            " id_sala = ? AND idCine = ?";
        preparedStatement = connection.prepareStatement(queryString);
        
        /* Fill "preparedStatement". */
        int i = 1;
        preparedStatement.setLong(i++, idSala);
        preparedStatement.setLong(i++, idCine);
        
        /* Execute query. */
        int removedRows = preparedStatement.executeUpdate();

        if (removedRows == 0) {
            throw new InstanceNotFoundException(idSala,
                SalaVO.class.getName());
        }

    } catch (SQLException e) {
        throw new InternalErrorException(e);    
    } finally {
        GeneralOperations.closeStatement(preparedStatement);
    }    

}
public SalaVO find(Connection connection, long idCine, long idSala)
throws InstanceNotFoundException, InternalErrorException{
	
	PreparedStatement preparedStatement = null;
	SalaVO sala = null;
	ResultSet resultSet = null;
	
	try{
		
		String query = "SELECT filas, asientos " +
				       "FROM SALA " +
				       "WHERE id_sala = ? AND idCine LIKE ? ";
		
		preparedStatement = connection.prepareStatement(query);
		
		int i = 1;
		preparedStatement.setLong(i++, idSala);
		preparedStatement.setLong(i++, idCine);
		
		/*Execute statment*/
		resultSet = preparedStatement.executeQuery();
		
		if(!resultSet.next()){
			throw new InstanceNotFoundException(new String(idSala + "|" + idCine) , SalaVO.class.getName());
		}else{
			i = 1;
			
			Long numFilas = resultSet.getLong(i++);
			Long numAsientos = resultSet.getLong(i++);
			
			
			sala = new SalaVO(new Long(idSala),numFilas,numAsientos, idCine);
		}
	}catch (SQLException e) {
		throw new InternalErrorException(e);
	}finally{
		GeneralOperations.closeResultSet(resultSet);
		GeneralOperations.closeStatement(preparedStatement);
	}
	return sala;
}
        
   
}
