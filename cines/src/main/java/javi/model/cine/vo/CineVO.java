package javi.model.cine.vo;

import java.io.Serializable;

public class CineVO implements Serializable {

    private Long idCine;
	private String nombre;
    private Long numSalas;
    private Long cp;
    
    public CineVO(Long idCine, String nombre, Long numSalas, Long cp) {
        
    	this.idCine = idCine;
        this.nombre = nombre;
        this.numSalas = numSalas;
        this.cp = cp;
        
    }
    
 public CineVO( String nombre, Long numSalas, Long cp) {
        
        this.nombre = nombre;
        this.numSalas = numSalas;
        this.cp = cp;
        
    }
    
    

	public Long getIdCine() {
		return idCine;
	}



	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}



	public Long getCp() {
		return cp;
	}



	public void setCp(Long cp) {
		this.cp = cp;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getNumSalas() {
		return numSalas;
	}



	public void setNumSalas(Long numSalas) {
		this.numSalas = numSalas;
	}



	public String toString() {
        return new String("nombre = " + nombre + " | " +
            "numero de salas = " + numSalas + " | " +
            "cp = " + cp);
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

