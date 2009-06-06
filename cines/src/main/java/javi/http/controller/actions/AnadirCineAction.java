package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirCineForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class AnadirCineAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
    	
        /* Get data. */
        AnadirCineForm anadirCineForm = (AnadirCineForm) form;

        String loginName = SessionManager.getUserFacadeDelegate(request).findUserProfile().getLoginName();
        String nombre = anadirCineForm.getNombre();
        Long numSalas = anadirCineForm.getNumSalasAsLong();
        Long cp = anadirCineForm.getCpAsLong();
        String ciudad = anadirCineForm.getCiudad();
        String direccion = anadirCineForm.getDireccion();
        Long numero = anadirCineForm.getNumeroAsLong();
                                                               
        
        /* Register cine. */            
        ActionMessages errors = new ActionMessages();
          
        try {
           
            AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirCine(loginName, nombre, numSalas, cp, ciudad, direccion, numero);
        	

        } catch (DuplicateInstanceException e) {
            errors.add("loginName",
                new ActionMessage("ErrorMessages.loginName.alreadyExists"));
        }            
        
        /* Return ActionForward. */
        if (errors.isEmpty()) {
            return mapping.findForward("MainPage");
        } else {
            saveErrors(request, errors);            
            return new ActionForward(mapping.getInput());
        }
        
    }

}
