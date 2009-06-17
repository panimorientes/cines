package javi.model.ticket.dao;

import java.sql.Connection;
import java.util.List;

import javi.model.ticket.vo.TicketVO;

import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * DAO de acceso a la entidad persistente Ticket.
 */

public interface SQLTicketDAO {
	
	
	//XXX no entiendo aun porque existe la clase Ticket y que es un Ticket. 
	
	/**
	 * Creacion del ticket, se utiliza esta solucion en lugar de recurrir a la 
	 *  manera tradicional de pasar como parametro el objeto, dado que la naturaleza
	 *  de ticket nos obliga a que los datos no esten completos en el momento de la misma,
	 *  por lo que el VO no pude ser recubierto en este momento lo que puede llevar a la
	 *  confusion del programador.
	 * 
	 * @param connection
	 * @param sesion >> sesion a la que pertenece
	 * @param fila >> fila de la butaca
	 * @param asiento >> asiento de la butaca
	 */
	public void create(Connection connection, long sesion, long fila, long asiento, double precio)
	 	throws InternalErrorException;
	
	
	/**
	 * Devuelve el ticket del asiento en una sesion o nulo si el asiento no existe.
	 *
	 * @param connection
	 * @param idSesion
	 * @param idFila
	 * @param idButaca
	 * @return
	 * @throws InternalErrorException
	 */
	public TicketVO find(Connection connection, long idSesion, long idFila, long idButaca)
	throws InternalErrorException;
	
	/**
	 * Devuelve el ticket del asiento en una sesion o nulo si el asiento no existe.
	 * 
	 * @param connection
	 * @param idTicket
	 * @return
	 * @throws InternalErrorException
	 */
	public TicketVO find(Connection connection, long idTicket)
	throws InternalErrorException;
	
	public List<TicketVO> find(Connection connection)
	throws InternalErrorException;
	
	
	/**
	 * @param connection
	 * @param idSesion
	 * @param idFila
	 * @param idButaca
	 * @return
	 * @throws InternalErrorException
	 */
	
	//XXX declaracion de UPDATE esta mal hecha,pone "ticke".
	//¿¿¿¿ADEMAS, tiene sentido hacer un UPDATE de un ticket????
	public void update(Connection connection, TicketVO ticke)
	throws InstanceNotFoundException,InternalErrorException;
	
	/**
	 * @param connection
	 * @param sesion
	 * @throws InstanceNotFoundException
	 * @throws InternalErrorException
	 */
	public void remove(Connection connection, long sesion)
	throws InstanceNotFoundException,InternalErrorException;


}
