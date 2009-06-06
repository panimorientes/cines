package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.direccion.dao.SQLDireccionDAO;
import javi.model.direccion.dao.SQLDireccionDAOFactory;
import javi.model.direccion.vo.DireccionVO;
import javi.model.userfacade.util.PasswordEncrypter;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;
import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class RegisterUserAction implements TransactionalPlainAction {

    private String loginName;
    private String encryptedPassword;
    private UserProfileDetailsVO userDetailsProfileVO;
    private Long cp;
    private String ciudad;
    private String direccion;
    private Long numero;
    

    public RegisterUserAction(String loginName, String clearPassword, Long cp, String ciudad, String direccion, Long numero,
        UserProfileDetailsVO userDetailsProfileVO) {
        
        this.loginName = loginName;
        this.encryptedPassword = PasswordEncrypter.crypt(clearPassword);
        this.userDetailsProfileVO = userDetailsProfileVO;
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
                
        SQLUserProfileDAO userProfileDAO = SQLUserProfileDAOFactory.getDAO();
        UserProfileVO userProfileVO = new UserProfileVO(loginName,
            encryptedPassword, userDetailsProfileVO);
        SQLDireccionDAO userDireccionDAO = SQLDireccionDAOFactory.getDAO();
        DireccionVO userDireccionVO = new DireccionVO(cp, ciudad, direccion, numero, loginName);

        userProfileDAO.create(connection, userProfileVO);
        userDireccionDAO.create(connection, userDireccionVO);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
