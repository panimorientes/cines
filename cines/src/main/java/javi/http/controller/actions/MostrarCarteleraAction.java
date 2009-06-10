package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.MostrarCarteleraForm;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class MostrarCarteleraAction extends DefaultAction {
	
	private String nombre;

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
    	  /* Fill "form". */     
        List<CineVO> cines= SessionManager.getBusquedaFacadeDelegate(request).buscaUserCine();
        

        if(cines != null && !cines.isEmpty()){
        	nombre = cines.get(0).getNombre();
        	request.setAttribute("listacines", cines);
        }

       
       
    	
    	if(form != null && cines != null && cines.size() > 0){
    	
    	 MostrarCarteleraForm mostrarCarteleraForm = (MostrarCarteleraForm) form;
         nombre = mostrarCarteleraForm.getNombre();
         
   
         
    	}
    	
    
    	
        /* Fill "form". */     
    	if(cines != null && !cines.isEmpty() && (form==null)){

    	List<SesionVO> sesion= SessionManager.getBusquedaFacadeDelegate(request).mostrarCartelera(cines.get(0).getNombre());
    	
    	List<PeliculaVO> pelicula= SessionManager.getBusquedaFacadeDelegate(request).buscaAdminPelicula();
        
    
    	request.setAttribute("listapeliculas", pelicula);
        request.setAttribute("listasesion", sesion);
        request.setAttribute("nombcine", nombre);
        

    	}
    	List<SesionVO> sesion = null;
    	List<PeliculaVO> pelicula = null;
    	if(nombre != null){
    	sesion= SessionManager.getBusquedaFacadeDelegate(request).mostrarCartelera(nombre);
    	pelicula= SessionManager.getBusquedaFacadeDelegate(request).buscaAdminPelicula();
    	}
        
    	request.setAttribute("listapeliculas", pelicula);
        request.setAttribute("listasesion", sesion);
        request.setAttribute("nombcine", nombre);
    	
    	
        /* Return ActionForward. */
    	request.setAttribute("nombcine", nombre);
    	return mapping.findForward("MostrarCarteleraForm");
    	
    }

}