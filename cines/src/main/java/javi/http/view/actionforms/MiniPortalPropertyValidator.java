package javi.http.view.actionforms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public final class MiniPortalPropertyValidator {

    private final static String INCORRECT_EMAIL_ADDRESS = 
        "ErrorMessages.emailAddress.incorrect";
        
    private MiniPortalPropertyValidator() {}

    public final static void validateEmailAddress(ActionErrors errors,
        String propertyName, String propertyValue) {

        if (PropertyValidator.validateMandatory(errors, propertyName,
            propertyValue)) {
            
            /**
             * Check that "propertyValue" contains the sign "@" surrounded by
             * at least one character.
             */
            int atSignIndex = propertyValue.indexOf("@");
            
            if ( (atSignIndex < 1) || 
                 (atSignIndex == propertyValue.length() - 1) ) {
                 errors.add(propertyName, 
                     new ActionMessage(INCORRECT_EMAIL_ADDRESS));
            }
            
        }
        
    }

}
