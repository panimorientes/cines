package javi.model.userfacade.plain.actions;

import java.sql.Connection;


import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;
import javi.model.direccion.vo.DireccionVO;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;
import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class UpdateUserProfileDetailsAction 
    implements TransactionalPlainAction {

    private String loginName;
    private UserProfileDetailsVO userProfileDetailsVO;
    private Long cp;
    private String ciudad;
    private String direccion;
    private Long numero;
    
    public UpdateUserProfileDetailsAction(String loginName,
        UserProfileDetailsVO userProfileDetailsVO, Long cp, String ciudad, String direccion, Long numero) {
        
        this.loginName = loginName;
        this.userProfileDetailsVO = userProfileDetailsVO;
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
        throws InstanceNotFoundException, InternalErrorException {
                
        SQLUserProfileDAO userProfileDAO = SQLUserProfileDAOFactory.getDAO();  
        SQLDireccionDAO userDireccionDAO = SQLDireccionDAOFactory.getDAO();  
        
        UserProfileVO userProfileVO = userProfileDAO.find(connection, loginName);
        DireccionVO userDireccionVO = userDireccionDAO.find(connection, loginName);
        
            
        userProfileVO.setUserProfileDetailsVO(userProfileDetailsVO);
        userProfileDAO.update(connection, userProfileVO);
        
        userDireccionVO.setCp(cp);
        userDireccionVO.setCiudad(ciudad);
        userDireccionVO.setDireccion(direccion);
        userDireccionVO.setNumero(numero);
        userDireccionVO.setLogin(loginName);
        userDireccionDAO.update(connection, userDireccionVO);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
