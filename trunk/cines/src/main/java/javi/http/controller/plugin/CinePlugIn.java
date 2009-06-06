package javi.http.controller.plugin;

import javax.servlet.ServletException;

import javi.http.view.applicationobjects.DateRanges;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;


public class CinePlugIn implements PlugIn {

    private ActionServlet actionServlet;

    public void init(ActionServlet servlet, ModuleConfig config) 
        throws ServletException {
        
        actionServlet = servlet;
        registerApplicationObject("dateRanges", new DateRanges());
        
    }

    public void destroy() {
        removeApplicationObject("dateRanges");
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
