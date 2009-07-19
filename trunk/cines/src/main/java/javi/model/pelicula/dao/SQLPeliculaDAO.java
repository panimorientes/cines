package javi.model.pelicula.dao;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import javi.model.pelicula.vo.PeliculaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * @author javi.model
 *
 */
public interface SQLPeliculaDAO {

    	public void create(Connection connection, String titulo, String director, String clasificacion, String descripcion)
    		throws  InternalErrorException, DuplicateInstanceException;
    
    	public boolean exists(Connection connection, Long idPelicula)
    		throws InternalErrorException;
    
		public void update(Connection connection, PeliculaVO userPeliculaVO) 
			throws InstanceNotFoundException, InternalErrorException;
    
		public void remove(Connection connection, Long idPelicula) 
			throws InstanceNotFoundException, InternalErrorException;
		
		public void remove(Connection connection, String titulo) 
			throws InstanceNotFoundException, InternalErrorException;

	    public PeliculaVO find(Connection connection, String nombre)
	        throws InstanceNotFoundException, InternalErrorException;
	    
	    public PeliculaVO find(Connection connection, long idPelicula)
        throws InstanceNotFoundException, InternalErrorException;
    
	    
	    
	    public PeliculaVO find1(Connection connection)
	    	throws InstanceNotFoundException, InternalErrorException;
	    
	    /**
	     * Se utiliza este metodo para la busqueda de pelicuals por titulo y por categoria
	     * 
	     * @param connection
	     * @param titulo
	     * @param categoria
	     * @return
	     * @throws InstanceNotFoundException
	     * @throws InternalErrorException
	     */
	    public List<PeliculaVO> find(Connection connection, String titulo, String categoria)
    		throws InstanceNotFoundException, InternalErrorException;
	    
	    public List<PeliculaVO> find(Connection connection, String titulo, String categoria, Calendar fecha) throws InstanceNotFoundException, InternalErrorException;
	    
	    public List<PeliculaVO> find(Connection connection)
        	throws InstanceNotFoundException, InternalErrorException;
	        

}
