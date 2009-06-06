package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirSalaForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class AnadirSalaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
    	
        /* Get data. */
        AnadirSalaForm anadirSalaForm = (AnadirSalaForm) form;

        String cine = anadirSalaForm.getCine();
        Long idSala = anadirSalaForm.getIdSalaAsLong();
        boolean [] array = anadirSalaForm.getButacas();
        Long asientos = anadirSalaForm.getAsientosAsLong();
        Long numFilas = anadirSalaForm.getNumFilasAsLong();
                                                                           
        /* Register sala. */            
        ActionMessages errors = new ActionMessages();
          
        try {
           
            AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirSala(idSala, numFilas, asientos, cine, array);
        	

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
