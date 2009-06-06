package javi.model.butaca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javi.model.butaca.vo.ButacaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;

public class StandardSQLButacaDAO implements SQLButacaDAO {

    public void create(Connection connection, ButacaVO adminButacaVO, boolean [] array)
        throws DuplicateInstanceException, InternalErrorException {
        
        /* Check if the sala already exists. */
        /*if (exists(connection, adminButacaVO.getNombre())) {
            throw new DuplicateInstanceException(adminButacaVO.getNombre(),
                AdminButacaVO.class.getName());
        }   
        Hay que crear el metodo exist*/

        PreparedStatement preparedStatement = null;
    
        try {
        	

        			/* Create "preparedStatement". */
        			String queryString = "INSERT INTO BUTACA" +
                		" (num_fila, num_asiento, ocupado, Bid_sala, idCine) VALUES (?, ?, ?, ?, ?)";
        			
        			/* Fill "preparedStatement". */
        			preparedStatement = connection.prepareStatement(queryString);    
                  			
        			
        			for(int j=1; j<array.length; j++){
        				int i = 1;
        				
        				if(!array[j]){
        					
        					int c=1;
        					if((j%adminButacaVO.getNumAsiento())==0) c=0;
        					long p=(j/adminButacaVO.getNumAsiento()+c);
        					preparedStatement.setLong(i++,p);
        	
        					/*long asiento=0;
        					int aux=(j-(adminButacaVO.getNumFila().intValue()*(j/adminButacaVO.getNumFila().intValue())));
        					if(aux==0){
        						asiento=adminButacaVO.getNumAsiento();
        					}else{
        						asiento=aux;
        					}*/
        					long a=0;
        					if(c==0) a=adminButacaVO.getNumAsiento();
        					preparedStatement.setLong(i++, ((j%adminButacaVO.getNumAsiento())+a));
        					preparedStatement.setBoolean(i++, adminButacaVO.isEstado());
        					preparedStatement.setLong(i++, adminButacaVO.getIdSala());
        					preparedStatement.setLong(i++, adminButacaVO.getIdCine());
            
        					/* Execute query. */
            				int insertedRows = preparedStatement.executeUpdate();
            				
            				if (insertedRows == 0) {
            					throw new SQLException("Can not add row to table" +
            					" 'BUTACA'");
            				}
            				
            				if (insertedRows > 1) {
            					throw new SQLException("Duplicate row for login = '" + 
            						adminButacaVO.getIdSala() + "' in table 'BUTACA'");
            				}
        				}
        				
        			}
        
        
        } catch (SQLException e) {
            throw new InternalErrorException(e);
        } finally {
            GeneralOperations.closeStatement(preparedStatement);
        }    
        
    }
    
    public List<ButacaVO> butacasSalaCine(Connection connection, long idCine, long idSala ) 
	throws InternalErrorException{
    	
    	List<ButacaVO> butacas = new ArrayList<ButacaVO>();
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	
    	try{
    		
    		String query = " SELECT num_fila, num_asiento " +
    					   " FROM BUTACA " +
    					   " WHERE Bid_sala = ? " +
    				       " AND idCine = ?";
    		
    		preparedStatement = connection.prepareStatement(query);
    		
    		/*Fill preparedStatement*/
    		int i = 1;
    		preparedStatement.setLong(i++, idSala);
    		preparedStatement.setLong(i++, idCine);
    		
    		/*Execute query*/
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()){
    			i = 1;
    			Long num_fila = resultSet.getLong(i++);
    			Long num_asiento = resultSet.getLong(i++);
    			
    			butacas.add(new ButacaVO(num_fila,num_asiento));
    		}
    		
    	}catch (SQLException e) {
			throw new InternalErrorException(e);
		}finally{
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}
    	
    	return butacas;
    }
    
}