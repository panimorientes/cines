package javi.model.carroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javi.model.producto.vo.ProductoVO;

public class CarroCompra implements Serializable{

	/*Productos en el carrito*/
	private List<ProductoVO> lineas;
	/*Total compra*/
	private double total;
	
	

	
	public CarroCompra() {
		super();
		lineas = new ArrayList<ProductoVO>();
	}
	
	public int getNumLineas(){
		return lineas.size();
	}
	

	public List<ProductoVO> getLineas() {
		return lineas;
	}

	public double getTotal() {
		return total;
	}
	
	public void addProducto(ProductoVO item){
		lineas.add(item);
		
		/*Suma al total*/
		total += item.getPrecio();
	}
	
	
	public void eliminarProducto(int pos){
		/*Resta al total*/
		total -=  lineas.get(pos).getPrecio();
		lineas.remove(pos);
	}
	public void borrarTotal(){
		total = 0;
	}
	
}
