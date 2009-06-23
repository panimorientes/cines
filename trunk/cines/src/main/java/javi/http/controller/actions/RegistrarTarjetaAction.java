package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.RegistrarTarjetaForm;
import javi.model.userfacade.delegate.UserFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class RegistrarTarjetaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
       RegistrarTarjetaForm registrarTarjetaForm = (RegistrarTarjetaForm) form;
        String login = registrarTarjetaForm.getLogin();
        Long tarjeta = registrarTarjetaForm.getTarjetaAsLong();
                           
        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        
        	
             
             /* Registrar tarjeta. */
             UserFacadeDelegate userFacadeDelegate =
                 SessionManager.getUserFacadeDelegate(request);
                 
             userFacadeDelegate.registrarTarjeta(tarjeta);
           
         

                 
        
        /* Return ActionForward. */
        if (errors.isEmpty()) {
            return mapping.findForward("MainPage");
        } else {
            saveErrors(request, errors);            
            return new ActionForward(mapping.getInput());
        }
        
    }

}
