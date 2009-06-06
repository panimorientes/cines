package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.dvd.dao.SQLdvdDAO;
import javi.model.dvd.dao.SQLdvdDAOFactory;
import javi.model.merchandising.dao.SQLMerchandisingDAO;
import javi.model.merchandising.dao.SQLMerchandisingDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class EliminarMerchandisingAction implements TransactionalPlainAction {

    private long idMerchandising;
 
    public EliminarMerchandisingAction(long idMerchandising) {
        
        this.idMerchandising = idMerchandising;
       
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws InstanceNotFoundException 
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
    	//Merchandising
        SQLMerchandisingDAO MerchandisingDAO = SQLMerchandisingDAOFactory.getDAO();

        MerchandisingDAO.remove(connection, idMerchandising);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
