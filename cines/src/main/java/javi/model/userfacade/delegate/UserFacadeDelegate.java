package javi.model.userfacade.delegate;

import java.io.Serializable;
import java.util.Calendar;

import javi.model.carroCompra.CarroCompra;
import javi.model.carroCompra.CarroNotFoundException;
import javi.model.direccion.vo.DireccionVO;
import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.vo.LoginResultVO;
import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * A facade to model the interaction of the user with the portal. There exist
 * some logical restrictions with regard to the order of method calling. 
 * In particular, <code>findUserProfile</code>,  
 * <code>updateUserProfileDetails</code> and <code>changePassword</code> can 
 * not be called if <code>login</code> or <code>registerUser</code> have not 
 * been previously called. After the user calls one of these two methods, the 
 * user is said to be authenticated.
 */
public interface UserFacadeDelegate extends Serializable {

	public void liberarTicket(long idTicket) throws InternalErrorException;
	
	public CarroCompra getCarroCompra() throws CarroNotFoundException;
	
	public void comprarM(long id) throws InternalErrorException;
	
	public void comprarD(String id) throws InternalErrorException;
	
	public void terminar() throws InternalErrorException;
	
    public void registerUser(String loginName, String clearPassword, Long cp, String ciudad, String direccion, Long numero,
        UserProfileDetailsVO userProfileDetailsVO)
        throws DuplicateInstanceException, InternalErrorException;

    public LoginResultVO login(String loginName, String password,
        boolean passwordIsEncrypted)
        throws InstanceNotFoundException, IncorrectPasswordException,
            InternalErrorException;
        
    public UserProfileVO findUserProfile() throws InternalErrorException;
    
    public DireccionVO findUserDireccion() throws InternalErrorException;
    
    public void updateUserProfileDetails(
        UserProfileDetailsVO userProfileDetailsVO, Long cp, String ciudad, String direccion, Long numero)
        throws InternalErrorException;

    public void changePassword(String oldClearPassword, 
        String newClearPassword) throws IncorrectPasswordException,
        InternalErrorException;
    
    public void registrarTarjeta(Long tarjeta)
            throws DuplicateInstanceException, InternalErrorException;
    
   
    public void anadirPedido(Long idPedido, Calendar fecha)  throws DuplicateInstanceException, InternalErrorException; 

}
