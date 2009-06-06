package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.UserProfileForm;
import javi.http.view.applicationobjects.Countries;
import javi.http.view.applicationobjects.Languages;
import javi.model.direccion.vo.DireccionVO;
import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class EditUserProfileAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        /* 
         * Insert list of languages and countries (ordered by current language)
         * as attributes in the "request".
         */
        request.setAttribute("languages", 
            Languages.getLanguages(getLocale(request).getLanguage()));
        request.setAttribute("countries", 
            Countries.getCountries(getLocale(request).getLanguage()));
        
        /* Fill "form". */
        UserProfileForm userProfileForm = (UserProfileForm) form;
        
        /*
         * If the request is to allow the user to correct errors in the form,
         * "userProfileForm" must not be modified.
         */         
        String action = userProfileForm.getAction();
                
        if (request.getAttribute(Globals.ERROR_KEY) == null) {
            
            /* 
             * Set "language" and "country" to his/her current selection. 
             */
            userProfileForm.setLenguaje(getLocale(request).getLanguage());
            userProfileForm.setPais(getLocale(request).getCountry());

            /*
             * If the user is updating his/her profile, set the rest of
             * attributes.
             */           
            if ("UPDATE".equals(action)) {
            
                UserProfileVO userProfileVO = SessionManager.getUserFacadeDelegate(request).findUserProfile();
                UserProfileDetailsVO userProfileDetailsVO = userProfileVO.getUserProfileDetailsVO();

                userProfileForm.setNombre(userProfileDetailsVO.getNombre());
                userProfileForm.setApe1(userProfileDetailsVO.getApe1());
                userProfileForm.setApe2(userProfileDetailsVO.getApe2());
                userProfileForm.setEmail(userProfileDetailsVO.getEmail());
                

                DireccionVO userDireccionVO = SessionManager.getUserFacadeDelegate(request).findUserDireccion();
               
                userProfileForm.setCp(Long.toString(userDireccionVO.getCp()));
                userProfileForm.setCiudad(userDireccionVO.getCiudad());
                userProfileForm.setDireccion(userDireccionVO.getDireccion());
                userProfileForm.setNumero(Long.toString(userDireccionVO.getNumero()));
                                                                   
            }
            
        }
        
        /* Return ActionForward. */
        if ("UPDATE".equals(action)) {
            return mapping.findForward("UpdateUserProfileDetailsForm");
        } else {
            return mapping.findForward("RegisterUserForm");
        }
        
    }

}
