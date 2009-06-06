package javi.model.merchandising.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLMerchandisingDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLMerchandisingDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLMerchandisingDAOFactory() {}
    
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
    
    public static SQLMerchandisingDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLMerchandisingDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
