package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.tarjeta.dao.SQLTarjetaDAO;
import javi.model.tarjeta.dao.SQLTarjetaDAOFactory;
import javi.model.tarjeta.vo.TarjetaVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class RegistrarTarjetaAction implements TransactionalPlainAction {

    private String login;
    private Long tarjeta;
    
    public RegistrarTarjetaAction(String login, Long tarjeta) {
        
    	this.tarjeta = tarjeta;
    	this.login = login;
        
    }
    
    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
    throws InstanceNotFoundException, InternalErrorException {
            
    SQLTarjetaDAO userTarjetaDAO = SQLTarjetaDAOFactory.getDAO();        
    TarjetaVO userTarjetaVO = new TarjetaVO(login, tarjeta);
        
    userTarjetaDAO.create(connection, userTarjetaVO);
    
    return null;            

}
    
       
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
