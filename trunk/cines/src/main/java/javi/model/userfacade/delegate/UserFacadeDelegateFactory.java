package javi.model.userfacade.delegate;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.configuration.MissingConfigurationParameterException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.jndi.ServiceLocator;

// For testing.
//import java.sql.Connection;
//import javax.sql.DataSource;
//import es.udc.fbellas.j2ee.util.sql.DataSourceLocator;
//import es.udc.fbellas.j2ee.util.sql.SimpleDataSource;    
//import es.udc.fbellas.j2ee.miniportal.model.util.GlobalNames;
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.vo.UserProfileVO;
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.vo.UserProfileDetailsVO;
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.dao.SQLUserProfileDAO;
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.dao.
//    SQLUserProfileDAOFactory;
//import es.udc.fbellas.j2ee.miniportal.model.userfacade.vo.LoginResultVO;

/**
 * A factory to get <code>UserFacadeDelegate</code> objects.
 * <p>
 * Required configuration parameters:
 * <ul>
 * <li><code>UserFacadeDelegateFactory/delegateClassName</code>: it must 
 * specify the full class name of the class implementing 
 * <code>UserFacadeDelegate</code>.</li>
 * </ul>
 */
public final class UserFacadeDelegateFactory {

    private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "UserFacadeDelegateFactory/delegateClassName";
    private final static String USER_FACADE_JNDI_NAME_PARAMETER =
        "UserFacadeDelegateFactory/userFacadeJNDIName";

    private static Class delegateClass;
    private static String userFacadeJNDIName;

    static {

        try {

            /* DELEGATE_CLASS_NAME_PARAMETER is specified in configuration? */        
            String delegateClassName = 
                ConfigurationParametersManager.getParameter(
                    DELEGATE_CLASS_NAME_PARAMETER);
            delegateClass = Class.forName(delegateClassName);
       
        } catch (MissingConfigurationParameterException e) {

            /* 
             * USER_FACADE_JNDI_NAME_PARAMETER should be specificed in
             * configuration.
             */
            try {

                userFacadeJNDIName = 
                       ConfigurationParametersManager.getParameter(
                    USER_FACADE_JNDI_NAME_PARAMETER);

            } catch (MissingConfigurationParameterException e2) {

                e.printStackTrace();
                e2.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }
    
    private UserFacadeDelegateFactory() {}
    
    
    public static UserFacadeDelegate getDelegate() 
        throws InternalErrorException {
        
        try {

            if (delegateClass != null) {
                return (UserFacadeDelegate) delegateClass.newInstance();
            } else {
                return ServiceLocator.findService(UserFacadeDelegate.class,
                    userFacadeJNDIName);
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    /* 
     * Test code. Uncomment for testing. Test this class and any class
     * implementing "UserFacadeDelegate".
     */         
//    public static void main (String[] args) {
//    
//        try {
//
//            /* Create a simple data source. */
//            DataSource dataSource = new SimpleDataSource();
//
//            /*
//             * Add "dataSource" to "DataSourceLocator"
//             * (this is needed to test
//             * "es.udc.fbellas.j2ee.miniportal.model.userfacade.plain.
//             * PlainUserFacadeDelegate"). 
//             */
//            DataSourceLocator.addDataSource(GlobalNames.MINIPORTAL_DATA_SOURCE,
//                dataSource);
//                
//            /* Create an "UserFacadeDelegate". */
//            UserFacadeDelegate delegate =
//                UserFacadeDelegateFactory.getDelegate();
//
//            /* Test "UserFacadeDelegate::registerUser". */
//            System.out.println("Test for 'UserFacadeDelegate::registerUser'");
//            UserProfileDetailsVO userProfileDetailsVO = 
//                new UserProfileDetailsVO("Fernando", "Bellas Permuy",
//                    "fbellas@udc.es", "gl", "ES");
//            delegate.registerUser("fbellas", "myPassword", 
//                userProfileDetailsVO);
//            System.out.println("User registered");
//
//            /* Test "UserFacadeDelegate::login". */
//            System.out.println("Test for 'UserFacadeDelegate::login'");
//            LoginResultVO loginResultVO = delegate.login("fbellas",
//                "myPassword", false);
//            System.out.println("Login with clear password successful: " + 
//                loginResultVO);
//            loginResultVO = delegate.login("fbellas",
//                loginResultVO.getEncryptedPassword(), true);
//            System.out.println("Login with encrypted password successful: " +
//                loginResultVO);
//                                
//            /* Test "UserFacadeDelegate::findUserProfile". */
//            System.out.println("Test for " +
//                "'UserFacadeDelegate::findUserProfile'");
//            UserProfileVO userProfileVO = delegate.findUserProfile();
//            System.out.println("User profile: " + userProfileVO);
//            
//            /* Test "UserFacadeDelegate::updateUserProfileDetails". */
//            System.out.println(
//                "Test for 'UserFacadeDelegate::updateUserProfileDetails'");
//            userProfileDetailsVO.setFirstName("John");
//            userProfileDetailsVO.setSurname("Smith");
//            userProfileDetailsVO.setEmail("jsmith@acme.com");
//            userProfileDetailsVO.setLanguage("en");
//            userProfileDetailsVO.setCountry("US");
//            delegate.updateUserProfileDetails(userProfileDetailsVO);
//            System.out.println("Updated user profile details: " +
//                delegate.findUserProfile().getUserProfileDetailsVO());
//                
//            /* Test "UserFacadeDelegate::changePassword". */
//            System.out.println("Test for " +
//                "'UserFacadeDelegate::changePassword'");
//            delegate.changePassword("myPassword", "myNewPassword");
//            System.out.println("New encrypted password: " + 
//                delegate.findUserProfile().getEncryptedPassword());
//                
//            /* Remove user profile. */
//            System.out.println("Removing user profile");
//            SQLUserProfileDAO dao = SQLUserProfileDAOFactory.getDAO();
//            Connection connection = dataSource.getConnection();
//            dao.remove(connection, "fbellas");
//                                    
//            /* Tests OK. */
//            System.out.println("Tests OK !!!!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }

}
