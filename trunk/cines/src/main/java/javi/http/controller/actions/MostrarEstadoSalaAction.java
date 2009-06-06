package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegateFactory;
import javi.model.busquedafacade.vo.EstadoSalaVO;
import javi.model.carroCompra.CarroNotFoundException;
import javi.model.sala.vo.SalaVO;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.vo.TicketVO;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class MostrarEstadoSalaAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
    	long idSesion = new Long(new String(request.getParameter("idSesion"))).longValue();
    	
    	EstadoSalaVO estado = BusquedaFacadeDelegateFactory.getDelegate().consultarSesion(idSesion);
    	
    	
        /* Fill "form". */     
    	request.setAttribute("numFilas", estado.getNumFilas());
        request.setAttribute("numButacas", estado.getNumButacas());

        request.setAttribute("butacas", estado.getButacas());
       
        /* Return ActionForward. */
        if(estado.getNumerada()){
            return mapping.findForward("MostrarEstadoSala");
        }else{
        	int filas = new Long(estado.getNumFilas()).intValue();
        	int butacas = new Long(estado.getNumButacas()).intValue();
        	int i= 0;
        	int j= 0;
        	TicketVO ticket = null;
        	
        	while(j < butacas && i < filas && ticket == null){
        		if(estado.getButaca(i, j) != null && estado.getButaca(i,j).getEstado() == 0) ticket = estado.getButaca(i, j);
        		
        		if(i == filas -1){ j++; i = 0;}i++;
        	}
        	if(ticket==null) return mapping.findForward("SalaLlena");
        	try {
        		BusquedaFacadeDelegateFactory.getDelegate().MarcarButaca(ticket.getIdTicket());
				SessionManager.getUserFacadeDelegate(request).getCarroCompra().addProducto(ticket);
			} catch (CarroNotFoundException e) {
				throw new InternalErrorException(e);
			}
			return mapping.findForward("MainPage");
        }
    }

}