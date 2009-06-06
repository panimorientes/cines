package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class FindUserProfileAction implements NonTransactionalPlainAction {

    private String loginName;
    
    public FindUserProfileAction(String loginName) {
        this.loginName = loginName;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLUserProfileDAO userProfileDAO = SQLUserProfileDAOFactory.getDAO();
        
        
        return userProfileDAO.find(connection, loginName);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
