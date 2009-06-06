package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class ComprarM extends DefaultAction{


	public  ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException, InternalErrorException {
		
		long id = new Long(new String(request.getParameter("id")));
		
		SessionManager.getUserFacadeDelegate(request).comprarM(id);
		
		
		return mapping.findForward("MainPage");
	}

	
	
}
