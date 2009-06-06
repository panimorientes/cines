package javi.model.carroCompra;

public class CarroNotFoundException extends Exception{

	@Override
	public String toString() {
		
		return "Error accediendo al carro " + super.toString() ;
		
	}
}
