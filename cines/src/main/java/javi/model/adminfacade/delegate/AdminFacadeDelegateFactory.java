package javi.model.adminfacade.delegate;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.configuration.MissingConfigurationParameterException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.jndi.ServiceLocator;

public final class AdminFacadeDelegateFactory {

    private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "AdminFacadeDelegateFactory/delegateClassName";
    private final static String ADMIN_FACADE_JNDI_NAME_PARAMETER =
        "AdminFacadeDelegateFactory/userFacadeJNDIName";

    private static Class delegateClass;
    private static String adminFacadeJNDIName;

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

                adminFacadeJNDIName = 
                       ConfigurationParametersManager.getParameter(
                    ADMIN_FACADE_JNDI_NAME_PARAMETER);

            } catch (MissingConfigurationParameterException e2) {

                e.printStackTrace();
                e2.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }
    
    private AdminFacadeDelegateFactory() {}
    
    
    public static AdminFacadeDelegate getDelegate() 
        throws InternalErrorException {
        
        try {

            if (delegateClass != null) {
                return (AdminFacadeDelegate) delegateClass.newInstance();
            } else {
                return ServiceLocator.findService(AdminFacadeDelegate.class,
                    adminFacadeJNDIName);
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    

}
