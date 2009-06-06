package javi.model.pedido.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLPedidoDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLPedidoDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLPedidoDAOFactory() {}
    
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
    
 public static SQLPedidoDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLPedidoDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }

}
