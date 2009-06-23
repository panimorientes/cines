package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.tarjeta.dao.SQLTarjetaDAO;
import javi.model.tarjeta.dao.SQLTarjetaDAOFactory;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class FindUserTarjetaAction implements NonTransactionalPlainAction {

    private String loginName;
    
    public FindUserTarjetaAction(String loginName) {
        this.loginName = loginName;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLTarjetaDAO tarjetaDao = (SQLTarjetaDAO) SQLTarjetaDAOFactory.getDAO();
        
        
        return tarjetaDao.find(connection, loginName);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
