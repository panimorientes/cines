package javi.model.producto.vo;

import java.io.Serializable;

public abstract class ProductoVO implements Serializable {

	
    private int tipo;
    private double precio;
    private String descripcion;
  
  
    
    public ProductoVO() {
		super();
	}



	public ProductoVO( int tipo,double precio,String descripcion) {  	
    	this.tipo = tipo;
    	this.precio = precio;
    	this.descripcion = descripcion;
    }
    



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}