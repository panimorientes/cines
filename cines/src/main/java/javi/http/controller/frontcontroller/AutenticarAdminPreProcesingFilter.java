package javi.http.controller.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * A filter to check if the action to be executed requires that the user had 
 * been authenticated. If the user has not been authenticated and the action 
 * requires it, <code>doProcess</code> returns the <code>ActionForward</code>
 * returned by <code>mapping.findForward("AuthenticationPage")</code>.
 */
public class AutenticarAdminPreProcesingFilter extends PreProcessingFilter {

    public AutenticarAdminPreProcesingFilter(PreProcessingFilter nextFilter) {
        super(nextFilter);
    }

    protected ActionForward doProcess(HttpServletRequest request, 
        HttpServletResponse response, Action action, ActionForm form,
        ActionMapping mapping) throws IOException, ServletException,
            InternalErrorException {
            
        MiniPortalActionMapping miniPortalActionMapping =
            (MiniPortalActionMapping) mapping;

        if (miniPortalActionMapping.isAdminRequired()) {
        
            if (SessionManager.isAdmin(request)) {
                return null;
            } else {
                return mapping.findForward("AuthenticationPage");
            }
        
        } else {
            return null;
        }
            
    }

}
