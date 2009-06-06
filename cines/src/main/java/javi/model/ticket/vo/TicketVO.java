package javi.model.ticket.vo;

import java.io.Serializable;

import javi.model.producto.vo.ProductoVO;

/**
 * @author javi.model
 *
 */
public class TicketVO extends ProductoVO {

	
	//PROPIEDADES
	private long idTicket;
	private long idSesion;

	//Butaca ticket
	private long fila;
	private long butaca;
	
	/**  0 - Libre
	 *   1 - Ocupada
	 *   2 - Pendiente confirmar
	 */
	
	private int estado;
	
	
	//METODOS

	/**
	 * @param idSesion
	 * @param pelicula
	 * @param fila
	 * @param butaca
	 * @param estado
	 * @param precio
	 */
	public TicketVO(long idSesion, long fila, long butaca, int estado,double precio) {
		super(0,precio,"ticket butaca"+fila+" "+butaca);
		
		this.idSesion = idSesion;

		this.fila = fila;
		this.butaca = butaca;
		this.estado = estado;
		}
	/**
	 * @param idTicket
	 * @param idSesion
	 * @param pelicula
	 * @param fila
	 * @param butaca
	 * @param estado
	 * @param precio
	 */
	public TicketVO(long idTicket,  long idSesion, long fila, long butaca, int estado, double precio) {
		super(0,precio,"ticket butaca"+fila+" "+butaca);
		
		this.idTicket = idTicket;
		this.idSesion = idSesion;
		this.fila = fila;
		this.butaca = butaca;
		this.estado = estado;
			}
	/**
	 * @return the butaca
	 */
	public long getButaca() {
		return butaca;
	}
	/**
	 * @param butaca the butaca to set
	 */
	public void setButaca(long butaca) {
		this.butaca = butaca;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the fila
	 */
	public long getFila() {
		return fila;
	}
	/**
	 * @param fila the fila to set
	 */
	public void setFila(long fila) {
		this.fila = fila;
	}
	/**
	 * @return the idSesion
	 */
	public long getIdSesion() {
		return idSesion;
	}
	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(long idSesion) {
		this.idSesion = idSesion;
	}

		public long getIdTicket() {
		return idTicket;
	}
	/**
	 * @param idTicket the idTicket to set
	 */
	public void setIdTicket(long idTicket) {
		this.idTicket = idTicket;
	}
	
	
}
