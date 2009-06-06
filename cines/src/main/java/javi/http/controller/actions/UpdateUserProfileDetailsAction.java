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

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class UpdateUserProfileDetailsAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        /* Get data. */
        UserProfileForm userProfileForm = (UserProfileForm) form;
        UserProfileDetailsVO userProfileDetailsVO = new UserProfileDetailsVO(
            userProfileForm.getNombre(), userProfileForm.getApe1(),userProfileForm.getApe2(),
            userProfileForm.getEmail(), userProfileForm.getLenguaje(),
            userProfileForm.getPais());  
            
            
        /* Update user profile details. */                    
        SessionManager.updateUserProfileDetails(request, userProfileDetailsVO, userProfileForm.getCpAsLong(), userProfileForm.getCiudad(), userProfileForm.getDireccion(),
        		userProfileForm.getNumeroAsLong());
        
        /* Return ActionForward. */
        return mapping.findForward("MainPage");
        
    }

}
