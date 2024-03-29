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

public class ComprarD extends DefaultAction{


	public  ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException, InternalErrorException {
		
		String id = new String(request.getParameter("id"));
		
		SessionManager.getUserFacadeDelegate(request).comprarD(id);
		
		
		return mapping.findForward("MainPage");
	}

	
	
}