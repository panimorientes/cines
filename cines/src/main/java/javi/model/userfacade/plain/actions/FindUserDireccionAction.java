package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class FindUserDireccionAction implements NonTransactionalPlainAction {

    private String loginName;
    
    public FindUserDireccionAction(String loginName) {
        this.loginName = loginName;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLDireccionDAO userDireccionDAO = SQLDireccionDAOFactory.getDAO();
        
        
        return userDireccionDAO.find(connection, loginName);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
