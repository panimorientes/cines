package javi.http.controller.actions;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirdvdForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class AnadirdvdAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
        AnadirdvdForm anadirdvdForm = (AnadirdvdForm) form;
        String titulo = anadirdvdForm.getTitulo();
        String director = anadirdvdForm.getDirector();
        String clasificacion = anadirdvdForm.getClasificacion();
        String descripcion = anadirdvdForm.getDescripcion();
        double precio = anadirdvdForm.getPrecio();
        boolean disponibilidad = anadirdvdForm.isDisponibilidad();
     
        String directorio = this.getServlet().getServletContext().getRealPath("/");
        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        try {
            
        	/* Registrar pelicula. */
        	AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirdvd(titulo, director, clasificacion, descripcion,precio,disponibilidad);
        	
        	/**Save a files*/
			FileOutputStream fout;
			
				fout = new FileOutputStream(directorio +"/img/dvd/" + titulo);
				
				try{
					fout.write(anadirdvdForm.getFile().getFileData());
					fout.close();
				}catch (IOException e) {
					throw new InternalErrorException(e);
				}
			
		/** Save links*/
        	
     
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
