package javi.model.butaca.vo;

import java.io.Serializable;

public class ButacaVO implements Serializable {

    private Long numFila;
    private Long numAsiento;
    private boolean estado;
    private Long idSala;
    private Long idCine;
    
    
    /**
	 * @param numFila
	 * @param numAsiento
	 */
	public ButacaVO(Long numFila, Long numAsiento) {
		super();
		this.numFila = numFila;
		this.numAsiento = numAsiento;
	}



	public ButacaVO(Long numFila, Long numAsiento, boolean estado, Long idSala, Long idCine) {
        
        this.numFila = numFila;
        this.numAsiento = numAsiento;
        this.estado = estado;
        this.idSala = idSala;
        this.idCine = idCine;
    }
    
    

	


	public Long getIdCine() {
		return idCine;
	}



	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	public Long getIdSala() {
		return idSala;
	}



	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}



	public Long getNumAsiento() {
		return numAsiento;
	}



	public void setNumAsiento(Long numAsiento) {
		this.numAsiento = numAsiento;
	}



	public Long getNumFila() {
		return numFila;
	}



	public void setNumFila(Long numFila) {
		this.numFila = numFila;
	}



	public String toString() {
        return new String("numero fila = " + numFila + " | " +
            " numero de asiento = " + numAsiento + " | " +
            " estado = " + estado +  " idSala = "+ idSala + " idCine = "+ idCine);
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

