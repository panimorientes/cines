package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;

import javi.model.sala.dao.SQLSalaDAO;
import javi.model.sala.dao.SQLSalaDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class BuscaBusquedaSalaAction implements NonTransactionalPlainAction {

    private Long idCine;
    
    public BuscaBusquedaSalaAction(Long idCine) {
        this.idCine = idCine;
    }
    
    /**
     *
     * @return the <code>UserProfileVO</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLSalaDAO busquedaSalaDAO = SQLSalaDAOFactory.getDAO();
        
        
        return busquedaSalaDAO.find(connection, idCine);                

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
