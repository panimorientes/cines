package javi.model.sala.vo;

import java.io.Serializable;

public class SalaVO implements Serializable {

    private Long idSala;
    private Long numFilas;
    private Long asientos;
    private Long idCine;
    
    public SalaVO(Long idSala, Long numFilas, Long asientos, Long idCine) {
        
    	this.idSala = idSala;
    	this.numFilas = numFilas;
    	this.asientos = asientos;
        this.idCine = idCine;
        
    }
    
    

	public Long getAsientos() {
		return asientos;
	}



	public void setAsientos(Long asientos) {
		this.asientos = asientos;
	}

	

	public Long getNumFilas() {
		return numFilas;
	}



	public void setNumFilas(Long numFilas) {
		this.numFilas = numFilas;
	}



	public Long getIdSala() {
		return idSala;
	}



	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}



	


	public Long getIdCine() {
		return idCine;
	}



	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}



	public String toString() {
        return new String("id sala = " + idSala + " | " +
        	"numero de filas = "+ numFilas + " | " +	
            "numero de asientos = " + asientos + " | " +
            "idcine = " + idCine);
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

