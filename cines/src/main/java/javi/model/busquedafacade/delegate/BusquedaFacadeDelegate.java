package javi.model.busquedafacade.delegate;

import java.io.Serializable;
import java.util.List;

import javi.model.busquedafacade.vo.EstadoSalaVO;
import javi.model.busquedafacade.vo.PedidoCustomVO;
import javi.model.cine.vo.CineVO;
import javi.model.clasificacion.vo.ClasificacionVO;
import javi.model.dvd.vo.dvdVO;
import javi.model.lpedido.vo.LPedidoVO;
import javi.model.merchandising.vo.MerchandisingVO;
import javi.model.pedido.vo.PedidoVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sala.vo.SalaVO;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.vo.TicketVO;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * @author javi.model
 *
 */
public interface BusquedaFacadeDelegate extends Serializable {

	 public List<CineVO> buscaUserCine() throws InternalErrorException; 
	 
	 public List<TicketVO> tickets() throws InternalErrorException; 
	    
	 public PeliculaVO buscaDetallesPelicula(String nombre) throws InternalErrorException;
	 
	 public PeliculaVO buscaPelicula() throws InternalErrorException;
	 
	 public List<SesionVO> buscaSesion(long idSesion) throws InternalErrorException;
	  
	 public dvdVO buscardvd(String nombre) throws InternalErrorException;

	 public List<SesionVO> mostrarCartelera(String nombre) throws InternalErrorException;
	 
	 public List<SesionVO> mostrarSesiones() throws InternalErrorException;
	 
	 public List<CineVO> buscaAdminCine() throws InternalErrorException;
	 
	 public CineVO buscaCine(String cine) throws InternalErrorException;
	 
	 public CineVO buscaCine (Long idCine) throws InternalErrorException;
	 
	 public List<CineVO> buscaCines () throws InternalErrorException;
	    
	 public List<SalaVO> buscaAdminSala(Long idCine) throws InternalErrorException;
	    
	 public List<PeliculaVO> buscaAdminPelicula() throws InternalErrorException;
	 	 
	 public List<PeliculaVO> busquedaPeliculas(String titulo, String categoria) throws InternalErrorException;
	 
	 public List<dvdVO> mostrardvd() throws InternalErrorException;
	 
	 public List<PedidoCustomVO> recuperarPedidos(String login) throws InternalErrorException;
	 	 	 
	 public List<MerchandisingVO> mostrarMerchandising() throws InternalErrorException;
	 
	 public List<PedidoVO> recuperarFechaPed(String login) throws InternalErrorException;
	 
	 public List<ClasificacionVO> recuperarClasificaciones() throws InternalErrorException;
	 
	 /**
	  * Devulve el estado de la sala para una sesion determinada.
	  * 
	  * @param idSesion
	  * @return
	  * @throws InternalErrorException
	  */
	 public EstadoSalaVO consultarSesion(long idSesion) throws InternalErrorException;
	 
	 /**
	  * Marca una butaca para una sesion como pendiente de confirmar por que alguien
	  * la ha anadido a su carro de compra. 
	  * 
	  * @param idTicket
	  * @return Ticket a añadir al carro
	  * @throws InternalErrorException
	  */
	 public TicketVO MarcarButaca(long idTicket) throws InternalErrorException;
	 
	 //public dvdVO Reservardvd(String titulo) throws InternalErrorException;
	 
	 //public MerchandisingVO ReservarMerchandisng(double referencia) throws InternalErrorException;

}
