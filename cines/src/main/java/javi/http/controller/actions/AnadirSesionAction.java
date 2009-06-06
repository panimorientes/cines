package javi.http.controller.actions;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirSesionForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class AnadirSesionAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
        AnadirSesionForm anadirSesionForm = (AnadirSesionForm) form;
        
        Calendar fecha = anadirSesionForm.getStartDate();
        Time hora = anadirSesionForm.getHora();
        double precio = anadirSesionForm.getPrecioAsLong();
        boolean numerada = anadirSesionForm.isNumerada();
        String titulo = anadirSesionForm.getTitulo();
     	String cine = anadirSesionForm.getNombre();
        Long idSala = anadirSesionForm.getIdSalaAsLong();
      
  
         
        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        try {
                
        	/* Registrar pelicula. */
        	AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirSesion(fecha, hora, precio, numerada, titulo, idSala,cine);
        	
     
        } catch (DuplicateInstanceException e) {
            errors.add("titulo",
                new ActionMessage("ErrorMessages.titulo.alreadyExists"));
        }            
        
        /* Return ActionForward. */
        if (errors.isEmpty()) {
            return mapping.findForward("MainPage");
        } else {
            saveErrors(request, errors);            
            return new ActionForward(mapping.getInput());
        }
        
    }

}
