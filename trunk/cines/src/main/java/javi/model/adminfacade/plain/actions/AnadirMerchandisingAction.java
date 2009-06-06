package javi.model.adminfacade.plain.actions;

import java.sql.Connection;
import java.sql.SQLException;

import javi.model.merchandising.dao.SQLMerchandisingDAO;
import javi.model.merchandising.dao.SQLMerchandisingDAOFactory;
import javi.model.merchandising.vo.MerchandisingVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;


public class AnadirMerchandisingAction implements TransactionalPlainAction {

	private String descripcion;
	private Long referencia;
    private String talla;
    private double precio;
    private boolean disponibilidad;
 
    
    public AnadirMerchandisingAction(String descripcion,Long referencia, String talla, double precio,
    		boolean disponibilidad) {
        
        this.descripcion= descripcion;
    	this.referencia = referencia;
        this.talla = talla;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
          
    }
    
    /**
     *
     * @return <code>null</code>
     */
    public Object execute(Connection connection) 
        throws DuplicateInstanceException, InternalErrorException {
        
    	//Merchandising
        SQLMerchandisingDAO adminMerchandisingDAO = SQLMerchandisingDAOFactory.getDAO();
       
		adminMerchandisingDAO.create(connection, descripcion,referencia, talla, precio, disponibilidad);
		
        
        return null;            

    }
    
   
}
