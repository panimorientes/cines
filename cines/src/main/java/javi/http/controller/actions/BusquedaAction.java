package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;

import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class BusquedaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
            
        BusquedaFacadeDelegate busquedaFacadeDelegate = SessionManager.getBusquedaFacadeDelegate(request);
		
		String clave=new String(request.getParameter("Pclave"));
		String clasificacion=new String(request.getParameter("clasificacion"));
		
		List<PeliculaVO> pelicula=busquedaFacadeDelegate.busquedaPeliculas(clave, clasificacion);
		List<SesionVO> sesion= busquedaFacadeDelegate.mostrarSesiones();
		
		request.setAttribute("peliculas", pelicula);
		request.setAttribute("listasesion", sesion);
	        
        
       
        return mapping.findForward("BusquedaForm");
        
    }

}