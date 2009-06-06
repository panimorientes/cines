package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.merchandising.dao.SQLMerchandisingDAO;
import javi.model.merchandising.dao.SQLMerchandisingDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class MostrarMerchandisingAction implements NonTransactionalPlainAction {

   
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLMerchandisingDAO busquedaMerchandisingDAO = SQLMerchandisingDAOFactory.getDAO();
        
        
        return busquedaMerchandisingDAO.find(connection);                

    }
    
   
}
