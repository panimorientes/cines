package javi.model.lpedido.vo;

import java.io.Serializable;
import java.util.Calendar;

public class LPedidoVO implements Serializable {

    private Long idPedido;
    private Long idProducto;
    private Long numLinea;
    private Long numUnidades;
    private int tipo;
   
    
    public LPedidoVO(Long idPedido, Long idProducto,  Long numUnidades, int tipo) {
        
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.numUnidades = numUnidades;
    }
    
    public LPedidoVO(Long idPedido, Long idProducto,  Long numUnidades, int tipo, long numLinea) {
        
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.numUnidades = numUnidades;
        this.numLinea = numLinea;
    }
    
    

	public Long getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



	public Long getNumLinea() {
		return numLinea;
	}



	public void setNumLinea(Long numLinea) {
		this.numLinea = numLinea;
	}



	public Long getNumUnidades() {
		return numUnidades;
	}



	public void setNumUnidades(Long numUnidades) {
		this.numUnidades = numUnidades;
	}



	public String toString() {
        return new String("idPedido = " + idPedido + " | " +
            "idProducto = " + idProducto + " | " + 
            "numero de lineas = " + numLinea + " | " + "numero de unidades = "+ numUnidades);
    }



	public int getTipo() {
		return tipo;
	}



	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
    
    /* Test code. Uncomment for testing. */
//    public static void main (String[] args) {
//    
//        UserProfileDetailsVO userProfileDetailsVO = new UserProfileDetailsVO(
//            "Fernando", "Bellas Permuy", "fbellas@udc.es", "gl", "ES");
//        UserProfileVO userProfile = new UserProfileVO("fbellas",
//            "YYYYYYYY", userProfileDetailsVO);
//            
//        System.out.println(userProfile);
//        
//    }    

}

