package javi.model.lpedido.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLLPedidoDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLLPedidoDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLLPedidoDAOFactory() {}
    
    private static Class getDAOClass() {
    
        Class theClass = null;
    
        try {
        
            String daoClassName = 
                ConfigurationParametersManager.getParameter(
                    DAO_CLASS_NAME_PARAMETER);
            
            theClass = Class.forName(daoClassName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return theClass;
        
    }
    
 public static SQLLPedidoDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLLPedidoDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }

}
