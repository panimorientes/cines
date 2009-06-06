package javi.model.busquedafacade.delegate;

import es.udc.fbellas.j2ee.util.configuration.ConfigurationParametersManager;
import es.udc.fbellas.j2ee.util.configuration.MissingConfigurationParameterException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.jndi.ServiceLocator;

public final class BusquedaFacadeDelegateFactory {

    private final static String DELEGATE_CLASS_NAME_PARAMETER =
        "BusquedaFacadeDelegateFactory/delegateClassName";
    private final static String BUSQUEDA_FACADE_JNDI_NAME_PARAMETER =
        "BusquedaFacadeDelegateFactory/userFacadeJNDIName";

    private static Class delegateClass;
    private static String busquedaFacadeJNDIName;

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

                busquedaFacadeJNDIName = 
                       ConfigurationParametersManager.getParameter(
                    BUSQUEDA_FACADE_JNDI_NAME_PARAMETER);

            } catch (MissingConfigurationParameterException e2) {

                e.printStackTrace();
                e2.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }
    
    private BusquedaFacadeDelegateFactory() {}
    
    
    public static BusquedaFacadeDelegate getDelegate() 
        throws InternalErrorException {
        
        try {

            if (delegateClass != null) {
                return (BusquedaFacadeDelegate) delegateClass.newInstance();
            } else {
                return ServiceLocator.findService(BusquedaFacadeDelegate.class,
                    busquedaFacadeJNDIName);
            }

        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    

}
