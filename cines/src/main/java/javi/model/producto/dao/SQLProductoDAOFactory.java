package javi.model.producto.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLProductoDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLProductoDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLProductoDAOFactory() {}
    
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
    
    public static SQLProductoDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLProductoDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
