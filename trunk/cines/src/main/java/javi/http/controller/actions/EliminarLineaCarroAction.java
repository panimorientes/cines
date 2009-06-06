package javi.http.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.carroCompra.CarroCompra;
import javi.model.carroCompra.CarroNotFoundException;
import javi.model.producto.vo.ProductoVO;
import javi.model.ticket.vo.TicketVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class EliminarLineaCarroAction extends DefaultAction{

	
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException, InternalErrorException {

		int idLinea = new Long(new String(request.getParameter("numLinea"))).intValue();
		
		CarroCompra carro = null;
		
		try {
			carro = SessionManager.getUserFacadeDelegate(request).getCarroCompra();
		} catch (CarroNotFoundException e) {
			throw new InternalErrorException(e);
		}
		/*
		 * Si la linea a tratar es un ticket entonces liberamos la butaca
		 */
		ProductoVO item = carro.getLineas().get(idLinea -1);
		
		if(item.getTipo() == 0)
			SessionManager.getUserFacadeDelegate(request).liberarTicket(((TicketVO)item).getIdTicket() );
		
		//Eliminamos la linea del carro.
		carro.eliminarProducto(idLinea -1);
		
		
		
		return mapping.findForward("MostrarCarro");
	}

}
