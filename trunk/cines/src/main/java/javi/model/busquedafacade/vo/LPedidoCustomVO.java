package javi.model.busquedafacade.vo;

import java.io.Serializable;

public class LPedidoCustomVO implements Serializable {

	private Long idProducto;
	private long numUnidades;
	private String nombre;
	private double precio;

	public LPedidoCustomVO(Long idProducto, long numUnidades,
			String nombre, double precio) {
		this.idProducto = idProducto;
		this.numUnidades = numUnidades;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public long getNumUnidades() {
		return numUnidades;
	}

	public void setNumUnidades(long numUnidades) {
		this.numUnidades = numUnidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
