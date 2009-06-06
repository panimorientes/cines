
package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegateFactory;
import javi.model.carroCompra.CarroCompra;
import javi.model.carroCompra.CarroNotFoundException;
import javi.model.ticket.vo.TicketVO;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class ComprarTicketAction extends DefaultAction{

	public ActionForward doExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	throws IOException, ServletException, InternalErrorException {

		/*Obtenemos el identificador del ticket a anadir al carro*/
		long idTicket = new Long(new String(request.getParameter("idTicket"))).longValue();
		
		
		/*Obtenemos el carrito*/
		CarroCompra carro = null;
		try {
			carro = SessionManager.getUserFacadeDelegate(request).getCarroCompra();
		} catch (CarroNotFoundException e) {
			new InternalErrorException(e);
		}
		
		
		TicketVO item = BusquedaFacadeDelegateFactory.getDelegate().MarcarButaca(idTicket);
		
		carro.addProducto(item);
	
		/* Return ActionForward. */
		request.setAttribute("sesion",item.getIdSesion());
		return mapping.findForward("MostrarSesion");



	}

}
