package javi.model.testfacade.delegate;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.configuration.MissingConfigurationParameterException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.jndi.ServiceLocator;

public final class TestFacadeDelegateFactory {

    private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "TestFacadeDelegateFactory/delegateClassName";
    private final static String TEST_FACADE_JNDI_NAME_PARAMETER =
        "TestFacadeDelegateFactory/userFacadeJNDIName";

    private static Class delegateClass;
    private static String testFacadeJNDIName;

    static {

        try {

             //DELEGATE_CLASS_NAME_PARAMETER is specified in configuration?         
            String delegateClassName = 
                ConfigurationParametersManager.getParameter(
                    DELEGATE_CLASS_NAME_PARAMETER);
            delegateClass = Class.forName(delegateClassName);
       
        } catch (MissingConfigurationParameterException e) {

             
             // USER_FACADE_JNDI_NAME_PARAMETER should be specificed in
              //configuration.
             
            try {

                testFacadeJNDIName = 
                       ConfigurationParametersManager.getParameter(
                    TEST_FACADE_JNDI_NAME_PARAMETER);

            } catch (MissingConfigurationParameterException e2) {

                e.printStackTrace();
                e2.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }
    
    private TestFacadeDelegateFactory() {}
    
    
    public static TestFacadeDelegate getDelegate() 
        throws InternalErrorException {
        
        try {

            if (delegateClass != null) {
                return (TestFacadeDelegate) delegateClass.newInstance();
            } else {
                return ServiceLocator.findService(TestFacadeDelegate.class,
                    testFacadeJNDIName);
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    

}
