package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.util.PasswordEncrypter;
import javi.model.userfacade.vo.LoginResultVO;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.NonTransactionalPlainAction;

public class LoginAction implements NonTransactionalPlainAction {

    private String loginName;
    private String password;
    private boolean passwordIsEncrypted;

    public LoginAction(String loginName, String password, 
        boolean passwordIsEncrypted) {    
        
        this.loginName = loginName;
        this.password = password;
        this.passwordIsEncrypted = passwordIsEncrypted;

    }
    
    /**
     *
     * @return a <code>LoginResultVO</code>
     */
    public Object execute(Connection connection) 
        throws IncorrectPasswordException, InstanceNotFoundException, 
            InternalErrorException {
                
        SQLUserProfileDAO userProfileDAO = SQLUserProfileDAOFactory.getDAO();
        UserProfileVO userProfileVO = userProfileDAO.find(connection, 
            loginName);
        String storedPassword = userProfileVO.getEncryptedPassword();

        if (passwordIsEncrypted) {
            if (!password.equals(storedPassword)) {
                throw new IncorrectPasswordException(loginName);
            }
        } else {
            if (!PasswordEncrypter.isClearPasswordCorrect(password,
                storedPassword)) {
                throw new IncorrectPasswordException(loginName);
            }
        } 

        return new LoginResultVO(
            userProfileVO.getUserProfileDetailsVO().getNombre(), 
            storedPassword,
            userProfileVO.getUserProfileDetailsVO().getLanguage(),
            userProfileVO.getUserProfileDetailsVO().getCountry());

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
