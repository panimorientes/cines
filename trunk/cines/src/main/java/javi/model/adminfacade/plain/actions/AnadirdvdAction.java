package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.dvd.dao.SQLdvdDAO;
import javi.model.dvd.dao.SQLdvdDAOFactory;
import javi.model.dvd.vo.dvdVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class AnadirdvdAction implements TransactionalPlainAction {

	private String titulo;
    private String director;
    private String clasificacion;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;
 
    
    public AnadirdvdAction( String titulo, String director,
        String clasificacion, String descripcion, double precio, boolean disponibilidad) {

    	this.titulo = titulo;
        this.director = director;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }
    
    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException {
        
    	//DVD
        SQLdvdDAO admindvdDAO = SQLdvdDAOFactory.getDAO();
       
        admindvdDAO.create(connection, titulo,director, clasificacion, descripcion,precio,disponibilidad);
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    
    
}
