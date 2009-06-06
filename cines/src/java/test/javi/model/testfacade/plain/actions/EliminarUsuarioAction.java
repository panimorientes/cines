package javi.model.testfacade.plain.actions;

import java.sql.Connection;

import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;


import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class EliminarUsuarioAction implements NonTransactionalPlainAction {
	 private String loginName;

		
	    public EliminarUsuarioAction(String loginName) {
	    	this.loginName = loginName;
	    	
	    }
   
    /**
     */
    public Object execute(Connection connection )
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLUserProfileDAO usuarioDAO = SQLUserProfileDAOFactory.getDAO();
      
    
        usuarioDAO.remove(connection, loginName);
        return null;

    }


}
