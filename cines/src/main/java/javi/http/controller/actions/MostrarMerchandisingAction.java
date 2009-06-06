package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.merchandising.vo.MerchandisingVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class MostrarMerchandisingAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        
        /* Fill "form". */     
        List<MerchandisingVO> listaMerchandising= SessionManager.getBusquedaFacadeDelegate(request).mostrarMerchandising();

        request.setAttribute("merchandising", listaMerchandising);
                      
        
        /* Return ActionForward. */
      
            return mapping.findForward("MostrarMerchandisingForm");
                
    }
}

