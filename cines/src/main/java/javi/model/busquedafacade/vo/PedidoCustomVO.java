package javi.model.busquedafacade.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javi.model.lpedido.vo.LPedidoVO;

public class PedidoCustomVO implements Serializable {

	private Long idPedido;
	private Date fechaPedido;
	private String login;
	private List<LPedidoCustomVO> lineasPedido;

	public PedidoCustomVO(Long idPedido, Date fechaPedido, String login,
			List<LPedidoCustomVO> pedidos) {
		this.idPedido = idPedido;
		this.fechaPedido = fechaPedido;
		this.login = login;
		lineasPedido = pedidos;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<LPedidoCustomVO> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(List<LPedidoCustomVO> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}

}
