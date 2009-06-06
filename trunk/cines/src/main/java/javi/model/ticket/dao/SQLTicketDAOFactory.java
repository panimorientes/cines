package javi.model.ticket.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLTicketDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLTicketDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLTicketDAOFactory() {}
    
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
    
 public static SQLTicketDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLTicketDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }


}
