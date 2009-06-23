package javi.model.userfacade.plain;

import java.util.Calendar;

import javax.sql.DataSource;


import javi.model.carroCompra.CarroCompra;
import javi.model.carroCompra.CarroNotFoundException;
import javi.model.direccion.vo.DireccionVO;
import javi.model.dvd.vo.dvdVO;
import javi.model.merchandising.vo.MerchandisingVO;
import javi.model.tarjeta.vo.TarjetaVO;
import javi.model.userfacade.delegate.UserFacadeDelegate;
import javi.model.userfacade.exceptions.IncorrectPasswordException;
import javi.model.userfacade.plain.actions.AnadirPedidoAction;
import javi.model.userfacade.plain.actions.ChangePasswordAction;
import javi.model.userfacade.plain.actions.ComprarDAction;
import javi.model.userfacade.plain.actions.ComprarMAction;
import javi.model.userfacade.plain.actions.FindUserDireccionAction;
import javi.model.userfacade.plain.actions.FindUserProfileAction;
import javi.model.userfacade.plain.actions.FindUserTarjetaAction;
import javi.model.userfacade.plain.actions.LiberarTicketAction;
import javi.model.userfacade.plain.actions.LoginAction;
import javi.model.userfacade.plain.actions.RegisterUserAction;
import javi.model.userfacade.plain.actions.RegistrarTarjetaAction;
import javi.model.userfacade.plain.actions.TerminarAction;
import javi.model.userfacade.plain.actions.UpdateUserProfileDetailsAction;
import javi.model.userfacade.vo.LoginResultVO;
import javi.model.userprofile.vo.UserProfileDetailsVO;
import javi.model.userprofile.vo.UserProfileVO;
import javi.model.util.GlobalNames;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.exceptions.ModelException;
import es.udc.fbellas.j2ee.util.sql.DataSourceLocator;
import es.udc.fbellas.j2ee.util.sql.PlainActionProcessor;

public class PlainUserFacadeDelegate implements UserFacadeDelegate {

    private String loginName;
    private CarroCompra carroCompra;
  
    
    public PlainUserFacadeDelegate() {
        loginName = null;
    }
    
    public CarroCompra getCarroCompra() throws CarroNotFoundException{
    	return carroCompra;
    }
    
    public String getLoginName() {
    	return loginName;
    }
    
    
    public void registerUser(String loginName, String clearPassword, Long cp, String ciudad, String direccion, Long numero,
        UserProfileDetailsVO userProfileDetailsVO)
        throws DuplicateInstanceException, InternalErrorException {
        
        try {
        
            RegisterUserAction action = new RegisterUserAction(loginName, 
                clearPassword, cp, ciudad, direccion, numero, userProfileDetailsVO);
                
            PlainActionProcessor.process(getDataSource(), action);
            this.loginName = loginName;
            
        } catch (DuplicateInstanceException e) {
            throw e;
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
        
    public LoginResultVO login(String loginName, String password,
        boolean passwordIsEncrypted)
        throws InstanceNotFoundException, IncorrectPasswordException,
            InternalErrorException {

        try {
        
            LoginAction action = new LoginAction(loginName, 
                password, passwordIsEncrypted);
                
            LoginResultVO loginResultVO = 
                (LoginResultVO) PlainActionProcessor.process(getDataSource(),
                    action);
            
            this.loginName = loginName;
            this.carroCompra = new CarroCompra();
            	
            	
            
            return loginResultVO;
            
        } catch (IncorrectPasswordException e) {
            throw e;
        } catch (InstanceNotFoundException e) {
            throw e;
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
            
    }
        
    public UserProfileVO findUserProfile() throws InternalErrorException {
    
        try {
        
            FindUserProfileAction action = new FindUserProfileAction(loginName);
                
            return (UserProfileVO) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    
    }
    
    public DireccionVO findUserDireccion() throws InternalErrorException {
        
        try {
        
            FindUserDireccionAction action = new FindUserDireccionAction(loginName);
                
            return (DireccionVO) PlainActionProcessor.process(getDataSource(),action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
    
    }
    
    public void updateUserProfileDetails(
        UserProfileDetailsVO userProfileDetailsVO, Long cp, String ciudad, String direccion, Long numero)
        throws InternalErrorException {

        try {
        
            UpdateUserProfileDetailsAction action = 
                new UpdateUserProfileDetailsAction(loginName, 
                    userProfileDetailsVO, cp, ciudad, direccion, numero);
                
            PlainActionProcessor.process(getDataSource(), action);
            
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
      
    public void registrarTarjeta(Long tarjeta)
    throws InternalErrorException {
    
    try {
    
        RegistrarTarjetaAction action = new RegistrarTarjetaAction(loginName, tarjeta);
            
        PlainActionProcessor.process(getDataSource(), action);
        //this.loginName = loginName;
        
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    
}
    

           
    public void changePassword(String oldClearPassword,
        String newClearPassword) throws IncorrectPasswordException,
        InternalErrorException {

        try {
        
            ChangePasswordAction action = new ChangePasswordAction(loginName, 
                oldClearPassword, newClearPassword);
                
            PlainActionProcessor.process(getDataSource(), action);
            
        } catch (IncorrectPasswordException e) {
            throw e;
        } catch (InstanceNotFoundException e) {
            throw new InternalErrorException(e);
        } catch (InternalErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalErrorException(e);
        }
        
    }
    
    
   
    public void anadirPedido(Long idPedido, Calendar fecha)
    throws DuplicateInstanceException, InternalErrorException {
    
    try {
    
        AnadirPedidoAction action = new AnadirPedidoAction(idPedido, fecha, loginName);
            
        PlainActionProcessor.process(getDataSource(), action);
        
    } catch (DuplicateInstanceException e) {
        throw e;
    } catch (InternalErrorException e) {
        throw e;
    } catch (Exception e) {
        throw new InternalErrorException(e);
    }
    }
    
  
    
    
    private DataSource getDataSource() throws InternalErrorException {
        return DataSourceLocator.getDataSource(
            GlobalNames.CINES_DATA_SOURCE);
    }
    
    public void liberarTicket(long idTicket) throws InternalErrorException{
    	
    	try{
    		LiberarTicketAction action = new LiberarTicketAction(idTicket);
    		
    		//Ejecutamos la accion
    		PlainActionProcessor.process(getDataSource(), action);
    		
    	}catch (ModelException e) {
			throw new InternalErrorException(e);
		}
    } 
    
    public void terminar() throws InternalErrorException{
    	try{
    		TerminarAction action = new TerminarAction(loginName, carroCompra);
    		
    		PlainActionProcessor.process(getDataSource(),action);
    	}catch (ModelException e) {
			throw new InternalErrorException(e);
		}
    	    	
    }
    public void comprarM(long id) throws InternalErrorException{
    	try{
    	ComprarMAction action = new ComprarMAction(id);
    	carroCompra.addProducto(
    	(MerchandisingVO)PlainActionProcessor.process(getDataSource(), action)
    	);
    	}catch (Exception e) {
			throw new InternalErrorException(e);
		}
    	
    }
    public void comprarD(String id) throws InternalErrorException{
    	try{
    	ComprarDAction action = new ComprarDAction(id);
    	carroCompra.addProducto(
    	(dvdVO)PlainActionProcessor.process(getDataSource(), action)
    	);
    	}catch (Exception e) {
			throw new InternalErrorException(e);
		}
    	
    }

	public TarjetaVO findUserTarjeta() throws InternalErrorException {
		
		try {
			FindUserTarjetaAction action = new FindUserTarjetaAction(loginName);
			return (TarjetaVO) PlainActionProcessor.process(getDataSource(), action);
		} catch (ModelException e) {
			throw new InternalErrorException(e);
		} catch (InternalErrorException e) {
			throw new InternalErrorException(e);
		}
	}
    
    
}
