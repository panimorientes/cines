package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import javi.model.sesion.dao.SQLSesionDAO;
import javi.model.sesion.dao.SQLSesionDAOFactory;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class EliminarPeliculaAction implements TransactionalPlainAction {

    private Long idPelicula;
 
    public EliminarPeliculaAction(Long idPelicula) {
        
        this.idPelicula = idPelicula;
       
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws InstanceNotFoundException 
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException, InstanceNotFoundException {
        
    	//Pelicula
        SQLPeliculaDAO adminPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
        
        SQLSesionDAO adminSesionDAO = SQLSesionDAOFactory.getDAO();
        
        adminSesionDAO.removeP(connection, idPelicula);
        adminPeliculaDAO.remove(connection, idPelicula);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
