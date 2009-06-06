package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.dvd.dao.SQLdvdDAO;
import javi.model.dvd.dao.SQLdvdDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class MostrardvdAction implements NonTransactionalPlainAction {

    //private String nombre;
	
    //public MostrardvdAction(String nombre) {
    	//this.nombre = nombre;
    //}
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLdvdDAO busquedadvdDAO = SQLdvdDAOFactory.getDAO();
        
        
        return busquedadvdDAO.find(connection/*, nombre*/);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
