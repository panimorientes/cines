package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.cine.dao.SQLCineDAO;
import javi.model.cine.dao.SQLCineDAOFactory;
import javi.model.cine.vo.CineVO;
import javi.model.sesion.dao.SQLSesionDAO;
import javi.model.sesion.dao.SQLSesionDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class MostrarCarteleraAction implements NonTransactionalPlainAction {

    private String cine;
	
    public MostrarCarteleraAction(String cine) {
    	this.cine = cine;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLCineDAO busquedaCineDAO = SQLCineDAOFactory.getDAO();
        
        SQLSesionDAO busquedaSesionDAO = SQLSesionDAOFactory.getDAO();
        
        CineVO cinevo = busquedaCineDAO.find1(connection, cine); 
        
        //XXX devuelve las sesiones de este cine,hay que comprobar
        //	  que no aparezcan las sesiones pasadas
        return  busquedaSesionDAO.find(connection, cinevo.getIdCine());

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
