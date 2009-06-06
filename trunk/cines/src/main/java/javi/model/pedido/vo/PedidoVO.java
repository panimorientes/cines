package javi.model.pedido.vo;

import java.io.Serializable;
import java.sql.Date;


public class PedidoVO implements Serializable {

    private Long idPedido;
    private Date fechaPedido;
    private String login;
    
    public PedidoVO(Date fechaPedido, String login) {
    
        this.fechaPedido = fechaPedido;
        this.login = login;
    }
    
    public PedidoVO(Long idPedido,Date fechaPedido, String login) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.login = login;
    }
    

	public Date getFechaPedido() {
		return fechaPedido;
	}



	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}



	public Long getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String toString() {
        return new String("idPedido = " + idPedido + " | " +
            "fecha del pedido = " + fechaPedido + " | " +
            "login = " + login);
    }   

}

