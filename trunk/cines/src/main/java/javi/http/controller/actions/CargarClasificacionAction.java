package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.clasificacion.vo.ClasificacionVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class CargarClasificacionAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
        
        
       BusquedaFacadeDelegate busquedaFacadeDelegate = SessionManager.getBusquedaFacadeDelegate(request);
       
       List<ClasificacionVO> clasificaciones = busquedaFacadeDelegate.recuperarClasificaciones(); 
     
       request.setAttribute("clasificaciones",clasificaciones);
            
       return mapping.findForward("AnadirPelicula2");
      
        
    }

}
