package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.cine.dao.SQLCineDAO;
import javi.model.cine.dao.SQLCineDAOFactory;
import javi.model.cine.vo.CineVO;
import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;
import javi.model.direccion.vo.DireccionVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class AnadirCineAction 
    implements TransactionalPlainAction {

	
	private String loginName;
    private String nombre;
    private Long numSalas;
    private Long cp;
    private String ciudad;
    private String direccion;
    private Long numero;
    
    public AnadirCineAction(String loginName, String nombre,
    		Long numSalas, Long cp, String ciudad, String direccion, Long numero) {
        
 
    	this.loginName = loginName;
        this.nombre = nombre;
        this.numSalas = numSalas;
        this.cp = cp;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
    }
    
    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
    throws DuplicateInstanceException, InternalErrorException {
            
    SQLCineDAO adminCineDAO = SQLCineDAOFactory.getDAO();
    
    SQLDireccionDAO adminDireccionDAO = SQLDireccionDAOFactory.getDAO();
    DireccionVO adminDireccionVO = new DireccionVO(cp, ciudad, direccion, numero, loginName);

    adminCineDAO.create(connection, nombre,numSalas, cp);
    adminDireccionDAO.create(connection, adminDireccionVO);
    
    return null;            

}

/* 
 * This class is tested by
 * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
 * UserFacadeDelegateFactory".
 */    

}
