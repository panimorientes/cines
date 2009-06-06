package javi.http.controller.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.model.dvd.vo.dvdVO;
import javi.model.lpedido.vo.LPedidoVO;
import javi.model.merchandising.vo.MerchandisingVO;
import javi.model.pedido.vo.PedidoVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.vo.TicketVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;
    
public class HistorialPedidosAction extends DefaultAction {

    public ActionForward doExecute(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException, InternalErrorException {
        
    	 String login = SessionManager.getUserFacadeDelegate(request).findUserProfile().getLoginName();
        
        /* Fill "form". */     
        List<LPedidoVO> pedidos= SessionManager.getBusquedaFacadeDelegate(request).recuperarPedidos(login);
        List<PedidoVO>  fped= SessionManager.getBusquedaFacadeDelegate(request).recuperarFechaPed(login);
        List<PeliculaVO> peliculas = SessionManager.getBusquedaFacadeDelegate(request).buscaAdminPelicula();
        List<dvdVO> dvds = SessionManager.getBusquedaFacadeDelegate(request).mostrardvd();
        List<MerchandisingVO> mer = SessionManager.getBusquedaFacadeDelegate(request).mostrarMerchandising();
        List<TicketVO> tickets = SessionManager.getBusquedaFacadeDelegate(request).tickets();
        List<SesionVO> sesiones = SessionManager.getBusquedaFacadeDelegate(request).mostrarSesiones();
        
        request.setAttribute("lpedidos", pedidos);
        request.setAttribute("fped", fped);
        request.setAttribute("peliculas", peliculas);
        request.setAttribute("dvds", dvds);
        request.setAttribute("mer", mer);
        request.setAttribute("tickets", tickets);
        request.setAttribute("sesiones", sesiones);

        /* Return ActionForward. */
        //return new ActionForward(mapping.getInput());
            return mapping.findForward("HistorialPedidos");
                
    }

}