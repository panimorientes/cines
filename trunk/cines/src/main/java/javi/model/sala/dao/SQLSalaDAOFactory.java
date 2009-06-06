package javi.model.sala.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLSalaDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLSalaDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLSalaDAOFactory() {}
    
    /**
     * @return
     */
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
    
    /**
     * @return
     * @throws InternalErrorException
     */
    public static SQLSalaDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLSalaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
