package javi.model.adminfacade.plain.actions;

import java.sql.Connection;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javi.model.butaca.dao.SQLButacaDAOFactory;
import javi.model.butaca.vo.ButacaVO;
import javi.model.cine.dao.SQLCineDAO;
import javi.model.cine.dao.SQLCineDAOFactory;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.dao.SQLSesionDAO;
import javi.model.sesion.dao.SQLSesionDAOFactory;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.dao.SQLTicketDAO;
import javi.model.ticket.dao.SQLTicketDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class AnadirSesionAction implements TransactionalPlainAction {

    private Calendar fecha;
    private Time hora;
    private double precio;
    private boolean numerada;
    private String titulo;
    private Long idSala;
    private String cine;
     
    
    public AnadirSesionAction(Calendar fecha, Time hora, double precio, boolean numerada, String titulo, Long idSala,String cine) {
        
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.numerada = numerada;
    	this.titulo = titulo;
    	this.idSala = idSala;
    	this.cine = cine;
          
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws InstanceNotFoundException 
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
    	//Pelicula
        SQLSesionDAO adminSesionDAO = SQLSesionDAOFactory.getDAO();
        SQLCineDAO adminCineDAO = SQLCineDAOFactory.getDAO();
        SQLPeliculaDAO adminPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
        
        CineVO cineVO = adminCineDAO.find1(connection,cine);
        PeliculaVO peliculaVO = adminPeliculaDAO.find(connection,titulo);
                
        SesionVO adminSesionVO = new SesionVO(fecha, hora, precio, numerada, peliculaVO.getIdPelicula(), idSala, cineVO.getIdCine());

        adminSesionVO = adminSesionDAO.create(connection, adminSesionVO);
        
        
        /*Creamos los ticket de la sesion dada de alta
         * -------------------------------------------
         * 
         * Se obtien las butacas que tiene la sala del cine donde se crea la sesion.
         * Se creara un ticket por cada una de las butacas que existan en la sala.
         * */
        
        long idSesion = adminSesionVO.getIdSesion().longValue();
        long idSala = adminSesionVO.getIdSala();
        long idCine = adminSesionVO.getIdCine();
        
        //Se obtienen las butacas que tiene la sala del cine. 
        List<ButacaVO> butacas = SQLButacaDAOFactory.getDAO().butacasSalaCine(connection, idCine, idSala);
        SQLTicketDAO ticketDAO = SQLTicketDAOFactory.getDAO();
        
        //Se crea un ticket para cada butaca
        for(ButacaVO butaca : butacas){
        	ticketDAO.create(connection, idSesion, butaca.getNumFila(), butaca.getNumAsiento(), precio);
        }
        return null;

    }
    
    
}
