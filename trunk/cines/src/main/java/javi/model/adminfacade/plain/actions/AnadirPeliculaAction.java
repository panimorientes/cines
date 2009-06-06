package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.pelicula.dao.SQLPeliculaDAO;
import javi.model.pelicula.dao.SQLPeliculaDAOFactory;
import javi.model.pelicula.vo.PeliculaVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class AnadirPeliculaAction implements TransactionalPlainAction {

   
	private String titulo;
    private String director;
    private String clasificacion;
    private String descripcion;
 
    
    public AnadirPeliculaAction(String titulo, String director,
        String clasificacion, String descripcion) {
        
        this.titulo = titulo;
        this.director = director;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
          
    }
    
    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException {
        
    	//Pelicula
        SQLPeliculaDAO adminPeliculaDAO = SQLPeliculaDAOFactory.getDAO();
      
        adminPeliculaDAO.create(connection, titulo,director, clasificacion, descripcion);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
