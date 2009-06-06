package javi.model.butaca.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLButacaDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLButacaDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLButacaDAOFactory() {}
    
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
    
    public static SQLButacaDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLButacaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
