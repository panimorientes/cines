package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.UserProfileForm;
import javi.model.userprofile.vo.UserProfileDetailsVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class RegisterUserAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
        UserProfileForm userProfileForm = (UserProfileForm) form;
        String loginName = userProfileForm.getLogin();
        String clearPassword = userProfileForm.getPass();
        Long cp = userProfileForm.getCpAsLong();
        String ciudad = userProfileForm.getCiudad();
        String direccion = userProfileForm.getDireccion();
        Long numero = userProfileForm.getNumeroAsLong();
        UserProfileDetailsVO userProfileDetailsVO = new UserProfileDetailsVO(userProfileForm.getNombre(), userProfileForm.getApe1(),userProfileForm.getApe2(),
            userProfileForm.getEmail(), userProfileForm.getLenguaje(),
            userProfileForm.getPais());
                                                                    
        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        try {
            SessionManager.registerUser(request, loginName, clearPassword, cp, ciudad, direccion, numero,
                userProfileDetailsVO);

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
