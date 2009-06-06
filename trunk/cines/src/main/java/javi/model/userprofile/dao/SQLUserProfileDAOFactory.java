package javi.model.userprofile.dao;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

// For testing.
//import java.sql.Connection;
//import javax.sql.DataSource;
//import es.udc.fbellas.j2ee.util.sql.GeneralOperations;
//import es.udc.fbellas.j2ee.util.sql.SimpleDataSource;    
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.vo.UserProfileVO;
//import es.udc.fbellas.j2ee.miniportal.model.userprofile.vo.UserProfileDetailsVO;

/**
 * A factory to get <code>SQLUserProfileDAO</code> objects.
 * <p>
 * Required configuration parameters:
 * <ul>
 * <li><code>SQLUserProfileDAOFactory/daoClassName</code>: it must specify the 
 * full class name of the class implementing <code>SQLUserProfileDAO</code>.
 * </li>
 * </ul>
 */
public final class SQLUserProfileDAOFactory {

    private final static String DAO_CLASS_NAME_PARAMETER =
        "SQLUserProfileDAOFactory/daoClassName";

    private final static Class daoClass = getDAOClass();
    
    private SQLUserProfileDAOFactory() {}
    
    private static Class getDAOClass() {
    
        Class theClass = null;
    
        try {
        
            String daoClassName = 
                ConfigurationParametersManager.getParameter(
                    DAO_CLASS_NAME_PARAMETER);
            
            theClass = Class.forName(daoClassName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return theClass;
        
    }
    
    public static SQLUserProfileDAO getDAO() throws InternalErrorException {
        
        try {        
            return (SQLUserProfileDAO) daoClass.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    /* 
     * Test code. Uncomment for testing. Test this class and any class
     * implementing "SQLUserProfileDAO".
     */         
//    public static void main (String[] args) {    
//        
//        Connection connection = null;
//    
//        try {        
//            
//            /* Get a connection. */
//            DataSource dataSource = new SimpleDataSource();
//            connection = dataSource.getConnection();        
//                    
//            /* Get dao. */
//            SQLUserProfileDAO dao = SQLUserProfileDAOFactory.getDAO();
//
//            /* Test "SQLUserProfileDAO::create". */
//            System.out.println("Test for 'SQLUserProfileDAO::create'");
//            UserProfileDetailsVO userProfileDetailsVO = 
//                new UserProfileDetailsVO("Fernando", "Bellas Permuy",
//                    "fbellas@udc.es", "gl", "ES");
//            UserProfileVO userProfileVO = new UserProfileVO("fbellas",
//                "YYYYYYY", userProfileDetailsVO);
//            dao.create(connection, userProfileVO);
//            System.out.println("userProfileVO created => " + userProfileVO);
//            
//            /* Test "SQLUserProfileDAO::exists". */
//            System.out.println("Test for 'SQLUserProfileDAO::exists'");
//            if (!dao.exists(connection, userProfileVO.getLoginName())) {
//                throw new Exception("Can not find user profile with " +
//                    "loginName = " + 
//                    userProfileVO.getLoginName());
//            }
//            
//            /* Test "SQLUserProfileDAO::find". */
//            System.out.println("Test for 'SQLUserProfileDAO::find'");
//            System.out.println(dao.find(connection,
//                userProfileVO.getLoginName()));
//            
//            /* Test "SQLUserProfileDAO::update". */
//            System.out.println("Test for 'SQLUserProfileDAO::update'");
//            userProfileDetailsVO.setFirstName("John");
//            userProfileDetailsVO.setSurname("Smith");
//            userProfileDetailsVO.setEmail("jsmith@acme.com");
//            userProfileDetailsVO.setLanguage("en");
//            userProfileDetailsVO.setCountry("US");
//            userProfileVO.setEncryptedPassword("ZZZZZZZZ");
//            dao.update(connection, userProfileVO);
//            System.out.println(dao.find(connection,
//                userProfileVO.getLoginName()));
//                
//            /* Test "SQLUserProfileDAO::remove". */
//            System.out.println("Test for 'SQLUserProfileDAO::remove'");
//            dao.remove(connection, userProfileVO.getLoginName());
//            
//            if (dao.exists(connection, userProfileVO.getLoginName())) {
//                throw new Exception("SQLUserProfileDAO::remove failed");
//            }
//                
//            /* Tests OK. */
//            System.out.println("Tests OK !!!!");
//        
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                GeneralOperations.closeConnection(connection);
//            } catch (InternalErrorException e) {
//                e.printStackTrace();
//            }
//        }
//                
//    }    
    
}
