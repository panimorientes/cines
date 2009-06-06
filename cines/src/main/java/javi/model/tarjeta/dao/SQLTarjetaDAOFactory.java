package javi.model.tarjeta.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLTarjetaDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLTarjetaDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLTarjetaDAOFactory() {}
    
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
    
 public static SQLTarjetaDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLTarjetaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }

}
