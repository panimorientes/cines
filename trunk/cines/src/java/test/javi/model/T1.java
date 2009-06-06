package javi.model;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import javi.model.adminfacade.delegate.AdminFacadeDelegate;
import javi.model.adminfacade.delegate.AdminFacadeDelegateFactory;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.busquedafacade.delegate.BusquedaFacadeDelegateFactory;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.testfacade.delegate.TestFacadeDelegate;
import javi.model.testfacade.delegate.TestFacadeDelegateFactory;
import javi.model.userfacade.delegate.UserFacadeDelegate;
import javi.model.userfacade.delegate.UserFacadeDelegateFactory;
import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.vo.LoginResultVO;
import javi.model.userprofile.vo.UserProfileDetailsVO;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public class T1 {

	private static final String USER_TO_TEST1 = "prueba123";

	private static final String USER_TO_TEST2 = "prueba124";
	
	private static final String USER_TO_TEST3 = "prueba125";

	private static final String PASS_TO_TEST = "prueba123";

	
	 /* Mantenemos una instancia en el banco porque guardara el estado de la
	 * instancia.*/
	 
	private static UserFacadeDelegate fachadausuario = null;

	private static UserFacadeDelegate fachadausuario2 = null;

	private static AdminFacadeDelegate fachadadmin = null;

	private static BusquedaFacadeDelegate fachadabusqueda = null;
	
	private static TestFacadeDelegate fachadapruebas = null;


	private static long cp = 100;
	
	private static long cp1 = 200;

	private static long cp2 = 300;

	private static long numero = 1;

	private static long idCine = 100;

	private static long numSalas = 100;

	private static String titulo = "Pprueba";

	private static String nombreCine = "Cprueba";

	@BeforeClass
	public static void inicializarTest() throws InternalErrorException,
			InstanceNotFoundException, IncorrectPasswordException,DuplicateInstanceException {
		
		DataSource dataSource = new es.udc.fbellas.j2ee.util.sql.SimpleDataSource();
		es.udc.fbellas.j2ee.util.sql.DataSourceLocator.addDataSource(
				javi.model.util.GlobalNames.CINES_DATA_SOURCE, dataSource);
		fachadausuario = UserFacadeDelegateFactory.getDelegate();
		fachadausuario2 = UserFacadeDelegateFactory.getDelegate();
		fachadadmin = AdminFacadeDelegateFactory.getDelegate();
		fachadabusqueda = BusquedaFacadeDelegateFactory.getDelegate();
		fachadapruebas = TestFacadeDelegateFactory.getDelegate();
		register(USER_TO_TEST1,cp);
		register(USER_TO_TEST2,cp1);
		fachadadmin.anadirPelicula(titulo, "director", "aventuras","descripcion");
		//fachadadmin.anadirCine("USER_TO_TEST3", nombreCine, numSalas,cp2, "ciudad", "direccion", numero);
		fachadausuario2.login(USER_TO_TEST2, PASS_TO_TEST, false);

	}

	public static void register(String Usuario,Long cp) throws InternalErrorException {
		UserProfileDetailsVO usuarioProfile = new UserProfileDetailsVO(
				Usuario, "ape1", "ape2", "cines@yahoo.es", "es","ES");
		// Prueba de registro.
		try {
			fachadausuario.registerUser(Usuario, PASS_TO_TEST, cp, "ciudad",
					"direccion", numero, usuarioProfile);
		} catch (DuplicateInstanceException e) {
			// El usuario ya estaba creado, entonce no hacemos nada
			;
		}
	}

	
	@Test (expected=IncorrectPasswordException.class) 
	public void loginError() throws InstanceNotFoundException, IncorrectPasswordException, InternalErrorException{
		fachadausuario.login(USER_TO_TEST1, "xx", false);
	}


	@Test
	public void login() throws InstanceNotFoundException,
			IncorrectPasswordException, InternalErrorException {

		LoginResultVO login = fachadausuario.login(USER_TO_TEST1, PASS_TO_TEST,false);
		assertEquals(login.getFirstName(), USER_TO_TEST1);

	}
	
	 @Test public void insertarPelicula() throws InstanceNotFoundException, InternalErrorException
	{

		PeliculaVO pelicula = fachadabusqueda.buscaDetallesPelicula(titulo);
		assertEquals(pelicula.getTitulo(), titulo);
		
		
	}

	

	public void insertarCine() throws InstanceNotFoundException,
			InternalErrorException {

		CineVO cine = fachadabusqueda.buscaCine(nombreCine);
		assertEquals(cine.getIdCine(), idCine);
		assertEquals(cine.getNombre(), nombreCine);

	}

	
	@AfterClass
	public static void finalizaTests() throws InternalErrorException,
			DuplicateInstanceException {

		fachadapruebas.eliminarDireccion(cp);
		fachadapruebas.eliminarDireccion(cp1);
		//fachadapruebas.eliminarDireccion(cp2);
		fachadapruebas.eliminarUsuario(USER_TO_TEST1);
		fachadapruebas.eliminarUsuario(USER_TO_TEST2);
		PeliculaVO pelicula = fachadabusqueda.buscaDetallesPelicula(titulo);
		fachadapruebas.eliminarPelicula(pelicula.getIdPelicula());
		//fachadapruebas.eliminarCine(idCine);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
