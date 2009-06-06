package javi.model.busquedafacade.plain.actions;

import java.sql.Connection;


import javi.model.busquedafacade.vo.EstadoSalaVO;
import javi.model.sala.dao.SQLSalaDAOFactory;
import javi.model.sala.vo.SalaVO;
import javi.model.sesion.dao.SQLSesionDAOFactory;
import javi.model.sesion.vo.SesionVO;
import javi.model.ticket.dao.SQLTicketDAOFactory;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class ConsultarSesionAction implements TransactionalPlainAction {

    private long idSesion;
	
    public ConsultarSesionAction(long idSesion) {
    	this.idSesion = idSesion;
    }
    
  
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException {

    	EstadoSalaVO estadoSala = null;
    	
    	SesionVO sesion = SQLSesionDAOFactory.getDAO().find(connection, idSesion);
    	SalaVO sala = SQLSalaDAOFactory.getDAO().find(connection, sesion.getIdCine(), sesion.getIdSala());
    	
    	/*Obtenemos la forma de la sala*/
    	int numFilas = sala.getNumFilas().intValue();
    	int numAsientos = sala.getAsientos().intValue();
    	
    	/*Inicializamos el estado se la sala*/
    	estadoSala = new EstadoSalaVO(numFilas, numAsientos);
    	
    	
    	for(int i = 0; i <  numFilas; i ++){
    		for(int j = 0; j < numAsientos; j++){
    			estadoSala.setButaca(i, j,
    					SQLTicketDAOFactory.getDAO().find(connection, idSesion, i+1, j+1));	
    		}
    	}
    	
    	estadoSala.setNumerada(sesion.isNumerada());
    	return estadoSala;
    }

}
