package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;
import javi.model.userfacade.delegate.UserFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class LogoutAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, InternalErrorException {

		/* Do logout. */
		
		UserFacadeDelegate userFacadeDelegate = SessionManager.getUserFacadeDelegate(request);
		
		//TODO este try-catch tampoco se como va...
		try {
			
			userFacadeDelegate.liberarTickets();
			
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SessionManager.logout(request, response);

	
		/* Return ActionForward. */
		return mapping.findForward("MostrarCarteleraForm");

	}

}
