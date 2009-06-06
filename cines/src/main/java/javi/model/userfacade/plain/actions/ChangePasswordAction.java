package javi.model.userfacade.plain.actions;

import java.sql.Connection;

import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.util.PasswordEncrypter;
import javi.model.userprofile.dao.SQLUserProfileDAO;
import javi.model.userprofile.dao.SQLUserProfileDAOFactory;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class ChangePasswordAction implements TransactionalPlainAction {

    private String loginName;
    private String oldClearPassword;
    private String newClearPassword;

    public ChangePasswordAction(String loginName, String oldClearPassword,
        String newClearPassword) {
        
        this.loginName = loginName;
        this.oldClearPassword = oldClearPassword;
        this.newClearPassword = newClearPassword;
            
    }

    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, IncorrectPasswordException,
        InternalErrorException {
                
        SQLUserProfileDAO userProfileDAO = SQLUserProfileDAOFactory.getDAO();
        UserProfileVO userProfileVO = userProfileDAO.find(connection, 
            loginName);
        String storedPassword = userProfileVO.getEncryptedPassword();
            
        if (!PasswordEncrypter.isClearPasswordCorrect(oldClearPassword,
            storedPassword)) {
            throw new IncorrectPasswordException(loginName);
        }

        userProfileVO.setEncryptedPassword(
            PasswordEncrypter.crypt(newClearPassword));
        userProfileDAO.update(connection, userProfileVO);

        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
