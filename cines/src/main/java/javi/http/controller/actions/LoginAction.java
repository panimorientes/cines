package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.LoginForm;
import javi.model.userfacade.exceptions.IncorrectPasswordException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class LoginAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        /* Get data. */
        LoginForm loginForm = (LoginForm) form;
        String loginName = loginForm.getLogin();
        String password = loginForm.getPass();
        boolean rememberMyPassword = loginForm.getRememberMyPassword();

        /* Do login. */
        ActionMessages errors = new ActionMessages();
        
        try {

            SessionManager.login(request, response, loginName, password,
                rememberMyPassword);
                
        } catch (InstanceNotFoundException e) {
            errors.add("loginName", new ActionMessage(
                "ErrorMessages.loginName.notFound"));
        } catch (IncorrectPasswordException e) {
            errors.add("password", new ActionMessage(
                "ErrorMessages.password.incorrect"));
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
