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

public class TerminarCarroAction extends DefaultAction{


	protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException, InternalErrorException {

		/*
		 * Ejecutamos la accion, terminar de la fachada de usuario,
		 * que recorre y trata todas las lineas del carro de la compra.
		 * 
		 */ 
		SessionManager.getUserFacadeDelegate(request).terminar();
		

		return mapping.findForward("MainPage");
	}

	
	
}
