package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.sesion.dao.SQLSesionDAO;
import javi.model.sesion.dao.SQLSesionDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BuscaSesionAction implements NonTransactionalPlainAction {

    private long idSesion;
	
    public BuscaSesionAction(long idSesion) {
        this.idSesion = idSesion;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLSesionDAO busquedaSesionDAO = SQLSesionDAOFactory.getDAO();
        
        
        return busquedaSesionDAO.find1(connection,idSesion);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
