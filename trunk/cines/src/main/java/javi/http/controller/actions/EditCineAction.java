package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.FormaSalaForm;
import javi.model.cine.vo.CineVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class EditCineAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        
        /* Fill "form". */     
        List<CineVO> cines= SessionManager.getBusquedaFacadeDelegate(request).buscaUserCine();

        request.setAttribute("listacines", cines);
        
        FormaSalaForm formaSalaForm = (FormaSalaForm) form;
        
        int numFilas = Integer.parseInt(formaSalaForm.getNumFilas());
        request.setAttribute("numFilas", numFilas);
        int asientos =Integer.parseInt(formaSalaForm.getAsientos());
        request.setAttribute("asientos", asientos);
        
                      
        
        /* Return ActionForward. */
        
        return mapping.findForward("AnadirSalaForm");
                
    }

}
