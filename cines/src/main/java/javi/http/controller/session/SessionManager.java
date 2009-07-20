package javi.http.controller.session;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javi.model.adminfacade.delegate.AdminFacadeDelegate;
import javi.model.adminfacade.delegate.AdminFacadeDelegateFactory;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegateFactory;
import javi.model.userfacade.delegate.UserFacadeDelegate;
import javi.model.userfacade.delegate.UserFacadeDelegateFactory;
import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.vo.LoginResultVO;
import javi.model.userprofile.vo.UserProfileDetailsVO;

import org.apache.struts.Globals;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * A facade utility class to transparently manage session objects and cookies.
 * The following objects are mantained in the session:
 *
 * <ul>
 *
 * <li>The user's first name, under the key 
 * <code>FIRST_NAME_SESSION_ATTRIBUTE</code>. This attribute is
 * only present for authenticated users, and is only of interest to the view
 * (JSP pages). </li>
 *
 * <li>An instance of <code>Locale</code>. If the user has been authenticated, 
 * his/her value is consistent with his/her profile information. This attribute
 * must only be accessed by using 
 * <code>org.apache.struts.action.Action.getLocale</code> or
 * <code>es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm.getLocale</code>.
 * </li>
 *
 * <li>An instance of <code>UserFacadeDelegate</code>. This attribute
 * must only be accessed by using <code>getUserFacadeDelegate</code>.</li>
 *
 * </ul>
 *
 * <p>
 *
 * For users that select "remember my password" in the login wizard, the
 * following cookies are used:
 *
 * <ul>
 * <li><code>LOGIN_NAME_COOKIE</code>: to store the login name.</li>
 * <li><code>ENCRYPTED_PASSWORD_COOKIE</code>: to store the encrypted 
 * password.</li>
 * </ul>
 *
 * <p>
 *
 * In order to make transparent the management of session objects and cookies
 * to the implementation of controller actions, this class provides a number
 * of methods equivalent to some of the ones provided by 
 * <code>UserFacadeDelegate</code>, which manage session objects and cookies,
 * and call upon the corresponding <code>UserFacadeDelegate's</code> method.
 * These methods are as follows:
 *
 * <ul>
 * <li><code>login</code>.</li>
 * <li><code>registerUser</code>.</li>
 * <li><code>updateUserProfileDetails</code>.</li>
 * <li><code>changePassword</code>.</li>
 * </ul>
 *
 * It is important to remember that when needing to do some of the above
 * actions from the controller, the corresponding method of this class
 * (one of the previous list) must be called, and <b>not</b> the one in
 * <code>UserFacadeDelegate</code>. The rest of methods of
 * <code>UserFacadeDelegate</code> must be called directly. Currently, there
 * exists only one, <code>findUserProfileDetails</code>, but in a real portal
 * there would be more. For example, in a personalizable portal,
 * <code>UserFacadeDelegate</code> could include:
 * <code>findServicePreferences</code>, <code>updateServicePreferences</code>,
 * <code>findLayout</code>, <code>updateLayout</code>, etc. In a electronic
 * commerce shop, <code>UserFacadeDelegate</code> could include:
 * <code>getShoppingCart</code>, <code>addToShoppingCart</code>,
 * <code>removeFromShoppingCart</code>, <code>makeOrder</code>,
 * <code>findPendingOrders</code>, etc. When needing to invoke directly a
 * method of <code>UserFacadeDelegate</code>,
 * <code>SessionManager.getUserFacadeDelegate</code> must be invoked in order
 * to get the personal delegate (each user has his/her own delegate).
 *
 * <p>
 *
 * Same as <code>UserFacadeDelegate</code>, there exist some logical 
 * restrictions with regard to the order of method calling. In particular, 
 * <code>updateUserProfileDetails</code> and <code>changePassword</code>
 * can not be called if <code>login</code> or <code>registerUser</code> 
 * have not been previously called. After the user calls one of these two 
 * methods, the user is said to be authenticated.
 *
 */
public final class SessionManager {

    public final static String FIRST_NAME_SESSION_ATTRIBUTE = "firstName";
    public final static String LOGIN = "login";
    private final static String USER_FACADE_DELEGATE_SESSION_ATTRIBUTE ="userFacadeDelegate";
    private final static String ADMIN_FACADE_DELEGATE_SESSION_ATTRIBUTE ="adminFacadeDelegate";
    private final static String BUSQUEDA_FACADE_DELEGATE_SESSION_ATTRIBUTE ="busquedaFacadeDelegate";

    public static final String LOGIN_NAME_COOKIE = "loginName";
    public static final String ENCRYPTED_PASSWORD_COOKIE = "encryptedPassword"; 
    
    private static final int COOKIES_TIME_TO_LIVE_REMEMBER_MY_PASSWORD =
        30 * 24 * 3600; // 30 days
    private static final int COOKIES_TIME_TO_LIVE_REMOVE = 0;

    private SessionManager() {}
    
  
    public final static void login(HttpServletRequest request,
        HttpServletResponse response, String loginName,
        String clearPassword, boolean rememberMyPassword) 
        throws IncorrectPasswordException, InstanceNotFoundException,
            InternalErrorException {

        /* 
         * Try to login, and if successful, update session with the necessary 
         * objects for an authenticated user.
         */ 
        LoginResultVO loginResultVO = doLogin(request, loginName, 
            clearPassword, false, rememberMyPassword);
        
            
        /* Add cookies if requested. */
        if (rememberMyPassword) {
            leaveCookies(response, loginName, 
                loginResultVO.getEncryptedPassword(),
                COOKIES_TIME_TO_LIVE_REMEMBER_MY_PASSWORD);
        }
        
    }
    
  
    
    public final static void registerUser(HttpServletRequest request,
        String loginName, String clearPassword, Long cp, String ciudad, String direccion, Long numero, 
        UserProfileDetailsVO userProfileDetailsVO) 
        throws DuplicateInstanceException, InternalErrorException {
        
        /* Register user. */
        UserFacadeDelegate userFacadeDelegate =
            getUserFacadeDelegate(request);
            
        userFacadeDelegate.registerUser(loginName, clearPassword, cp, ciudad, direccion, numero, 
            userProfileDetailsVO);
            
        /* 
         * Update session with the necessary objects for an authenticated
         * user. 
         */
        Locale locale = new Locale(userProfileDetailsVO.getLanguage());
        updateSesssionForAuthenticatedUser(request, 
            userProfileDetailsVO.getNombre(), locale,loginName);
    }
    
   
    
    public final static void updateUserProfileDetails(
        HttpServletRequest request, UserProfileDetailsVO userProfileDetailsVO, Long cp, String ciudad, String direccion, Long numero) 
        throws InternalErrorException  {
        
        /* Update user's profile details. */
        UserFacadeDelegate userFacadeDelegate =
            getUserFacadeDelegate(request);
            
        userFacadeDelegate.updateUserProfileDetails(userProfileDetailsVO,cp,ciudad,direccion,numero);
        
        /* Update user's session objects.*/
        Locale locale = new Locale(userProfileDetailsVO.getLanguage());
        
        updateSesssionForAuthenticatedUser(request, 
            userProfileDetailsVO.getNombre(), locale, userFacadeDelegate.findUserProfile().getLoginName());
    }

    public final static void changePassword(HttpServletRequest request, 
        HttpServletResponse response, String oldClearPassword,
        String newClearPassword) throws IncorrectPasswordException,
        InternalErrorException  {
        
        /* Change user's password. */
        UserFacadeDelegate userFacadeDelegate =
            getUserFacadeDelegate(request);
            
        userFacadeDelegate.changePassword(oldClearPassword, newClearPassword);
            
        /* Remove cookies. */
        leaveCookies(response, "", "", COOKIES_TIME_TO_LIVE_REMOVE);
        
    }

    /**
     * Destroyes session, and removes cookies if the user had selected
     * "remember my password.
     */
    public final static void logout(HttpServletRequest request,
        HttpServletResponse response) throws InternalErrorException {
    
        /* Remove cookies. */
        leaveCookies(response, "", "", COOKIES_TIME_TO_LIVE_REMOVE);
    
        /* Invalidate session. */
        HttpSession session = request.getSession(true);
        session.invalidate();
    
    }

    public final static UserFacadeDelegate getUserFacadeDelegate(
        HttpServletRequest request) throws InternalErrorException {

        HttpSession session = request.getSession(true);
        UserFacadeDelegate userFacadeDelegate = 
            (UserFacadeDelegate) session.getAttribute(
                USER_FACADE_DELEGATE_SESSION_ATTRIBUTE);
                
        if (userFacadeDelegate == null) {
            userFacadeDelegate = UserFacadeDelegateFactory.getDelegate();
            session.setAttribute(USER_FACADE_DELEGATE_SESSION_ATTRIBUTE,
                userFacadeDelegate);
        }

        return userFacadeDelegate;
        
    }

    
    public final static AdminFacadeDelegate getAdminFacadeDelegate(
            HttpServletRequest request) throws InternalErrorException {

            HttpSession session = request.getSession(true);
            AdminFacadeDelegate adminFacadeDelegate = 
                (AdminFacadeDelegate) session.getAttribute(
                    ADMIN_FACADE_DELEGATE_SESSION_ATTRIBUTE);
                    
            if (adminFacadeDelegate == null) {
                adminFacadeDelegate = AdminFacadeDelegateFactory.getDelegate();
                session.setAttribute(ADMIN_FACADE_DELEGATE_SESSION_ATTRIBUTE,
                    adminFacadeDelegate);
            }

            return adminFacadeDelegate;
            
        }
    
    public final static BusquedaFacadeDelegate getBusquedaFacadeDelegate(
            HttpServletRequest request) throws InternalErrorException {

            HttpSession session = request.getSession(true);
            BusquedaFacadeDelegate busquedaFacadeDelegate = 
                (BusquedaFacadeDelegate) session.getAttribute(
                    BUSQUEDA_FACADE_DELEGATE_SESSION_ATTRIBUTE);
                    
            if (busquedaFacadeDelegate == null) {
                busquedaFacadeDelegate = BusquedaFacadeDelegateFactory.getDelegate();
                session.setAttribute(BUSQUEDA_FACADE_DELEGATE_SESSION_ATTRIBUTE,
                    busquedaFacadeDelegate);
            }

            return busquedaFacadeDelegate;
            
        }


    /**
     * Guarantees that the session will have the necessary objects if the user
     * has been authenticated or had selected "remember my password" in the 
     * past.
     */
    public final static void touchSession(HttpServletRequest request) 
        throws InternalErrorException {
    
        /* Check if "firstName" is in the session. */
        String firstName = null;
        HttpSession session = request.getSession(false);
        
        if (session != null) {
        
            firstName = (String) session.getAttribute(
                FIRST_NAME_SESSION_ATTRIBUTE);
                
            if (firstName != null) {
                return;
            }
            
        }
        
        /* 
         * The user had not been authenticated or his/her session has expired. 
         * We need to check if the user has selected "remember my password" in 
         * the last login (login name and password will come as cookies). If 
         * so, we reconstruct user's session objects.
         */    
        updateSessionFromCookies(request);
    
    }

    
    public final static boolean isUserAuthenticated(
        HttpServletRequest request) {
    
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            return false;
        } else {
            return session.getAttribute(FIRST_NAME_SESSION_ATTRIBUTE) != null;
        }
    
    }
    
    public final static boolean isAdmin(
            HttpServletRequest request) {
        	
    	
            HttpSession session = request.getSession(false);
            
            if (session == null) {
                return false;
            } else {
                return session.getAttribute(LOGIN).toString().equals("Administrator");               
            }
        
        }
    
    private final static void updateSesssionForAuthenticatedUser(
        HttpServletRequest request, String firstName,
        Locale locale, String login) throws InternalErrorException {
        
        /* Insert objects in session. */
        HttpSession session = request.getSession(true);
        session.setAttribute(FIRST_NAME_SESSION_ATTRIBUTE, firstName);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        session.setAttribute(LOGIN, login);
        

    }
    
    /**
     * Tries to login (inserting necessary objects in the session) by using 
     * cookies (if present).
     */
    private final static void updateSessionFromCookies(
        HttpServletRequest request) throws InternalErrorException {
        
        /* Are there cookies in the request ? */
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        /* 
         * Check if the login name and the encrypted password come as 
         * cookies. 
         */
        String loginName = null;
        String encryptedPassword = null;
        int foundCookies = 0;

        for (int i=0; (i<cookies.length) && (foundCookies < 2); i++) {
            if (cookies[i].getName().equals(LOGIN_NAME_COOKIE)) {
                loginName = cookies[i].getValue();
                foundCookies++;
            }
            if (cookies[i].getName().equals(ENCRYPTED_PASSWORD_COOKIE)) {
                encryptedPassword = cookies[i].getValue();
                foundCookies++;
            }
        }

        if (foundCookies != 2) {
            return;
        }
        
        /* Try to login, and if successful, update session with the necessary 
         * objects for an authenticated user.
         */
        try {
            doLogin(request, loginName, encryptedPassword, true, true);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) { // Incorrect loginName or encryptedPassword
            return;
        }

    }
    
    private final static void leaveCookies(HttpServletResponse response,
        String loginName, String encryptedPassword, int timeToLive) {
        
         /* Create cookies. */
        Cookie loginNameCookie = new Cookie(LOGIN_NAME_COOKIE, loginName);
        Cookie encryptedPasswordCookie = new Cookie(ENCRYPTED_PASSWORD_COOKIE, 
            encryptedPassword);

        /* Set maximum age to cookies. */
        loginNameCookie.setMaxAge(timeToLive);
        encryptedPasswordCookie.setMaxAge(timeToLive);

        /* Add cookies to response. */
        response.addCookie(loginNameCookie);
        response.addCookie(encryptedPasswordCookie);

    }
    
    /**
     * Tries to log in with the corresponding method of 
     * <code>UserFacadeDelegate</code>, and if successful, inserts in the
     * session the necessary objects for an authenticated user.
     */
    private final static LoginResultVO doLogin(HttpServletRequest request,
         String loginName, String password, boolean passwordIsEncrypted,
         boolean rememberMyPassword) throws IncorrectPasswordException, 
         InstanceNotFoundException, InternalErrorException {
         
         /* Check "loginName" and "clearPassword". */
        UserFacadeDelegate userFacadeDelegate =
            getUserFacadeDelegate(request);
        LoginResultVO loginResultVO = userFacadeDelegate.login(
            loginName, password, passwordIsEncrypted);
        
        /* Insert necessary objects in the session. */
        Locale locale = new Locale(loginResultVO.getLanguage());
        updateSesssionForAuthenticatedUser(request, 
            loginResultVO.getFirstName(), locale, loginName);
        
        /* Return "loginResultVO". */
        return loginResultVO;
         
    }
  

}
