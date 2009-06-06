package javi.model.cine.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLCineDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLCineDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLCineDAOFactory() {}
    
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
    
    public static SQLCineDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLCineDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
