package javi.model.pelicula.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public final class SQLPeliculaDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLPeliculaDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLPeliculaDAOFactory() {}
    
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
    
    public static SQLPeliculaDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLPeliculaDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
}
