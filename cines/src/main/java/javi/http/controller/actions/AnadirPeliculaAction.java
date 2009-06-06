package javi.http.controller.actions;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirPeliculaForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class AnadirPeliculaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
        AnadirPeliculaForm anadirPeliculaForm = (AnadirPeliculaForm) form;
        
        String titulo = anadirPeliculaForm.getTitulo();
        String director = anadirPeliculaForm.getDirector();
        String clasificacion = anadirPeliculaForm.getClasificacion();
        String descripcion = anadirPeliculaForm.getDescripcion();
     
        String directorio = this.getServlet().getServletContext().getRealPath("/");


        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        try {
        	
            
        	/* Registrar pelicula. */
        	AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirPelicula(titulo, director, clasificacion, descripcion);
        	
        	/**Save a files*/
			FileOutputStream fout;
			
				fout = new FileOutputStream(directorio +"/img/pelicula/" + titulo);
				
				try{
					fout.write(anadirPeliculaForm.getFile().getFileData());
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
