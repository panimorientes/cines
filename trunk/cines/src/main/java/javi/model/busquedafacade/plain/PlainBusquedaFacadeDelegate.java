package javi.model.busquedafacade.plain;

import java.util.List;
import javax.sql.DataSource;


import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.busquedafacade.plain.actions.BuscaBusquedaCineAction;
import javi.model.busquedafacade.plain.actions.BuscaBusquedaPeliculaAction;
import javi.model.busquedafacade.plain.actions.BuscaBusquedaSalaAction;
import javi.model.busquedafacade.plain.actions.BuscaCineAction;
import javi.model.busquedafacade.plain.actions.BuscaDetallesPeliculaAction;
import javi.model.busquedafacade.plain.actions.BuscaPeliculaAction;
import javi.model.busquedafacade.plain.actions.BuscaSesionAction;
import javi.model.busquedafacade.plain.actions.BuscardvdAction;
import javi.model.busquedafacade.plain.actions.BusquedaPeliculasAction;
import javi.model.busquedafacade.plain.actions.ConsultarSesionAction;
import javi.model.busquedafacade.plain.actions.MarcarButacaAction;
import javi.model.busquedafacade.plain.actions.MostrarCarteleraAction;
import javi.model.busquedafacade.plain.actions.BuscaUserCineAction;
import javi.model.busquedafacade.plain.actions.MostrarMerchandisingAction;
import javi.model.busquedafacade.plain.actions.MostrarSesionesAction;
import javi.model.busquedafacade.plain.actions.MostrardvdAction;
import javi.model.busquedafacade.plain.actions.RecuperarFechaPedAction;
import javi.model.busquedafacade.plain.actions.RecuperarPedidosAction;
import javi.model.busquedafacade.plain.actions.TicketsAction;
//import javi.model.busquedafacade.plain.actions.ReservardvdAction;
import javi.model.busquedafacade.vo.EstadoSalaVO;
import javi.model.cine.vo.CineVO;
import javi.model.dvd.vo.dvdVO;
import javi.model.lpedido.vo.LPedidoVO;
import javi.model.merchandising.vo.MerchandisingVO;
import javi.model.pedido.vo.PedidoVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sala.vo.SalaVO;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.vo.TicketVO;
import javi.model.util.GlobalNames;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.DataSourceLocator;
import es.udc.fbellas.j2ee.util.sql.PlainActionProcessor;

public class PlainBusquedaFacadeDelegate implements BusquedaFacadeDelegate {

    
	public PlainBusquedaFacadeDelegate() {
       
    }
	
	public List<LPedidoVO> recuperarPedidos(String login) throws InternalErrorException {
        
        try {
        
            RecuperarPedidosAction action = new RecuperarPedidosAction(login);
                
            return (List<LPedidoVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }
	
	public List<PedidoVO> recuperarFechaPed(String login) throws InternalErrorException {
        
        try {
        
            RecuperarFechaPedAction action = new RecuperarFechaPedAction(login);
                
            return (List<PedidoVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }


    
    public List<CineVO> buscaUserCine() throws InternalErrorException {
        
        try {
        
            BuscaUserCineAction action = new BuscaUserCineAction();
                
            return (List<CineVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    
    }
    
 public List<TicketVO> tickets() throws InternalErrorException {
        
        try {
        
            TicketsAction action = new TicketsAction();
                
            return (List<TicketVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    
    }
    
  
    public PeliculaVO buscaDetallesPelicula(String nombre) throws InternalErrorException {
    
    try {
    
        BuscaDetallesPeliculaAction action = new BuscaDetallesPeliculaAction(nombre);
            
        return (PeliculaVO) PlainActionProcessor.process(getDataSource(),action);
        
    } catch (InstanceNotFoundException e) {
        throw new InternalErrorException(e);
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }

}
    public List<SesionVO> buscaSesion(long idSesion) throws InternalErrorException {
        
        try {
        
            BuscaSesionAction action = new BuscaSesionAction(idSesion);
                
            return (List<SesionVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }
    
  
    
    public PeliculaVO buscaPelicula() throws InternalErrorException {
        
        try {
        
            BuscaPeliculaAction action = new BuscaPeliculaAction();
                
            return (PeliculaVO) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }
    
    
    public CineVO buscaCine(String cine) throws InternalErrorException {
        
        try {
        
            BuscaCineAction action = new BuscaCineAction(cine);
                
            return (CineVO) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }
    public dvdVO buscardvd(String nombre) throws InternalErrorException {
        
        try {
        
            BuscardvdAction action = new BuscardvdAction(nombre);
                
            return (dvdVO) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }

    }
    
 public List<SesionVO> mostrarCartelera(String cine) throws InternalErrorException {
        
        try {
        
            MostrarCarteleraAction action = new MostrarCarteleraAction(cine);
                
            return (List<SesionVO>) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    
    }
 
 
 public List<dvdVO> mostrardvd() throws InternalErrorException {
     
     try {
     
         MostrardvdAction action = new MostrardvdAction();
             
         return (List<dvdVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }
 
 
 public List<MerchandisingVO> mostrarMerchandising() throws InternalErrorException {
     
     try {
     
         MostrarMerchandisingAction action = new MostrarMerchandisingAction();
             
         return (List<MerchandisingVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }
 
 public List<CineVO> buscaAdminCine() throws InternalErrorException {
     
     try {
     
         BuscaBusquedaCineAction action = new BuscaBusquedaCineAction();
             
         return (List<CineVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }
 
public List<SesionVO> mostrarSesiones() throws InternalErrorException {
     
     try {
     
         MostrarSesionesAction action = new MostrarSesionesAction();
             
         return (List<SesionVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }
 
 
public List<PeliculaVO> buscaAdminPelicula() throws InternalErrorException {
     
     try {
     
         BuscaBusquedaPeliculaAction action = new BuscaBusquedaPeliculaAction();
             
         return (List<PeliculaVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }

public List<PeliculaVO> busquedaPeliculas(String titulo, String categoria) throws InternalErrorException {
    
    try {
    
        BusquedaPeliculasAction action = new BusquedaPeliculasAction(titulo, categoria);
            
        return (List<PeliculaVO>) PlainActionProcessor.process(getDataSource(),action);
        
    } catch (InstanceNotFoundException e) {
        throw new InternalErrorException(e);
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }

}
 
	public List<SalaVO> buscaAdminSala(Long idCine) throws InternalErrorException {
     
     try {
     	
         BuscaBusquedaSalaAction action = new BuscaBusquedaSalaAction(idCine);
             
         return (List<SalaVO>) PlainActionProcessor.process(getDataSource(),action);
         
     } catch (InstanceNotFoundException e) {
         throw new InternalErrorException(e);
     } catch (InternalErrorException e) {
         throw e;
     } catch (Exception e) {
         throw new InternalErrorException(e);
     }
 
 }
	public EstadoSalaVO consultarSesion(long idSesion) throws InternalErrorException{
		
		try{
			ConsultarSesionAction action = new ConsultarSesionAction(idSesion);
			return (EstadoSalaVO) PlainActionProcessor.process(getDataSource(),action);
			
		}catch (ModelException e) {
			throw new InternalErrorException(e);
		}
		
	}
 
    private DataSource getDataSource() throws InternalErrorException {
        return DataSourceLocator.getDataSource(
            GlobalNames.CINES_DATA_SOURCE);
    }

    public TicketVO MarcarButaca(long idTicket) throws InternalErrorException{
    	
    	try{
    		MarcarButacaAction action = new MarcarButacaAction(idTicket);
    		return (TicketVO) PlainActionProcessor.process(getDataSource(), action);
    	}catch (ModelException e) {
			throw new InternalErrorException(e);
		}
    	
    }

	

	
 /*public dvdVO Reservardvd(String titulo) throws InternalErrorException{
    	
    	try{
    		ReservardvdAction action = new ReservardvdAction(titulo);
    		return (dvdVO) PlainActionProcessor.process(getDataSource(), action);
    	}catch (ModelException e) {
			throw new InternalErrorException(e);
		}
    	
    }*/
}