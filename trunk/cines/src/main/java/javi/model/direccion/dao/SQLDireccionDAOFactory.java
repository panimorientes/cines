package javi.model.direccion.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLDireccionDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLDireccionDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLDireccionDAOFactory() {}
    
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
    
 public static SQLDireccionDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLDireccionDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }

}
