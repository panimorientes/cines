package javi.http.controller.actions;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.AnadirMerchandisingForm;
import javi.model.adminfacade.delegate.AdminFacadeDelegate;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class AnadirMerchandisingAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
            
        /* Get data. */
        AnadirMerchandisingForm anadirMerchandisingForm = (AnadirMerchandisingForm) form;
        String descripcion = anadirMerchandisingForm.getDescripcion();
        Long referencia = anadirMerchandisingForm.getReferencia();
        String talla = anadirMerchandisingForm.getTalla();
        double precio = anadirMerchandisingForm.getPrecio();
        boolean disponibilidad = anadirMerchandisingForm.isDisponibilidad();
      
        String directorio = this.getServlet().getServletContext().getRealPath("/");
        
        /* Register user. */            
        ActionMessages errors = new ActionMessages();
          
        try {
            
        	/* Registrar merchandising. */
        	AdminFacadeDelegate adminFacadeDelegate = SessionManager.getAdminFacadeDelegate(request);
        	
        	adminFacadeDelegate.anadirMerchandising(descripcion,referencia,talla,precio,disponibilidad);
        	
        	/**Save a files*/
			FileOutputStream fout;
			
				fout = new FileOutputStream(directorio +"/img/merchandising/" + descripcion);
				
				try{
					fout.write(anadirMerchandisingForm.getFile().getFileData());
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
