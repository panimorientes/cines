package javi.http.controller.plugin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javi.http.controller.session.SessionManager;
import javi.http.view.applicationobjects.DateRanges;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.busquedafacade.plain.PlainBusquedaFacadeDelegate;
import javi.model.clasificacion.vo.ClasificacionVO;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;


public class CinePlugIn implements PlugIn {

    private ActionServlet actionServlet;

    public void init(ActionServlet servlet, ModuleConfig config) 
        throws ServletException {
        
        actionServlet = servlet;
        registerApplicationObject("dateRanges", new DateRanges());
        
        BusquedaFacadeDelegate busquedaFacadeDelegate = new PlainBusquedaFacadeDelegate();
        
        List<ClasificacionVO> clasificaciones = new ArrayList<ClasificacionVO>();
        clasificaciones.add(new ClasificacionVO(" "));
        try {
        	clasificaciones.addAll(busquedaFacadeDelegate.recuperarClasificaciones());
			registerApplicationObject("clasificaciones", clasificaciones);
		} catch (InternalErrorException e) {
			registerApplicationObject("clasificaciones", clasificaciones);
		}
        
    }

    public void destroy() {
        removeApplicationObject("dateRanges");
        removeApplicationObject("clasificaciones");
    }
      
    private void registerApplicationObject(String key, Object object) {
        
        actionServlet.getServletConfig().getServletContext().
            setAttribute(key, object);
        
    }
    
    private void removeApplicationObject(String key) {
        
        actionServlet.getServletConfig().getServletContext().
            removeAttribute(key);
        
    }

}
