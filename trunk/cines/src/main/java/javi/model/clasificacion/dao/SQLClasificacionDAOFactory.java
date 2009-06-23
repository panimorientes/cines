package javi.model.clasificacion.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLClasificacionDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLClasificacionDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLClasificacionDAOFactory() {}
    
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
    
    public static SQLClasificacionDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLClasificacionDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
