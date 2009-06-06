/******************************************************
 *             Sitio Web de ines.                 *
 *          ----------------------------              *
 *                                                    *  
 *     - Proyecto de fin de Carrera,                  *
 *     - Departamento de Tecnologías de la Información* 
 *       y las Comunicaciones  www.tic.udc.es         *
 *     - Facultad de Informatica  www.fic.udc.es      *
 *     - Universidad de Coruña    www.udc.es          *
 *                                                    *
 *******************************************************/
package javi.model.testfacade.delegate;

import javi.model.pelicula.vo.PeliculaVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;


/**
 * Esta inteface proporciona una fachada para los metodos 
 * necesarios para el testeo de la aplicacion y que no 
 * conforman casos de uso procedentes del analisis.
 * */
public interface TestFacadeDelegate {

	
	public void eliminarUsuario(String login) 
		throws InternalErrorException;
	
	public void eliminarCine(long idCine) 
	throws InternalErrorException;
	
	public PeliculaVO buscarPelicula(long idPelicula) 
		throws InternalErrorException;
	
	public void eliminarPelicula(long idPelicula) 
	throws  DuplicateInstanceException,InternalErrorException;
	
	public void eliminarDireccion(long cp) 
	throws  DuplicateInstanceException,InternalErrorException;
	
	
	
}