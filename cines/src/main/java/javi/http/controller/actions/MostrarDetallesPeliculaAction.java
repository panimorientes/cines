package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.model.busquedafacade.delegate.BusquedaFacadeDelegateFactory;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class MostrarDetallesPeliculaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
    	//TODO esto no lo hace bien.
    	if(request.getParameter("idCine") == null )
        	return new ActionForward("ShowMostrarCartelera.do");
    	
        //long idCine = new Long(new String(request.getParameter("idCine")));    
    	
        long idSesion=new Long(new String(request.getParameter("sesion")));
        
        List<PeliculaVO> peliculas= BusquedaFacadeDelegateFactory.getDelegate().buscaAdminPelicula();
        List<SesionVO> sesiones= BusquedaFacadeDelegateFactory.getDelegate().buscaSesion(idSesion);
        
        
        request.setAttribute("detallepelicula", peliculas);
        request.setAttribute("listasesiones", sesiones);
        request.setAttribute("idSesion", idSesion);
        
        /* Return ActionForward. */
    	 return mapping.findForward("MostrarDetallesPelicula");
                
    }

}
