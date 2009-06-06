package javi.model.dvd.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLdvdDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLdvdDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLdvdDAOFactory() {}
    
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
    
    public static SQLdvdDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLdvdDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
