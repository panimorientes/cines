package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.dvd.dao.SQLdvdDAO;
import javi.model.dvd.dao.SQLdvdDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class EliminardvdAction implements TransactionalPlainAction {

    private long idDvd;
 
    public EliminardvdAction(long idDvd) {
        
        this.idDvd = idDvd;
       
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws InstanceNotFoundException 
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
    	//dvd
        SQLdvdDAO dvdDAO = SQLdvdDAOFactory.getDAO();

        dvdDAO.remove(connection, idDvd);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
