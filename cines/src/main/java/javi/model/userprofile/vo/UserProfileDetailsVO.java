package javi.model.userprofile.vo;

import java.io.Serializable;

public class UserProfileDetailsVO implements Serializable {

    private String nombre;
    private String ape1;
    private String ape2;
    private String email;
    private String language;
    private String country;
    
    
    public UserProfileDetailsVO(String nombre, String ape1, String ape2, String email,
    		 String language, String country) {
        
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.email = email;
        this.language = language;
        this.country = country;
        
        
    }



	public String getApe1() {
		return ape1;
	}



	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}



	public String getApe2() {
		return ape2;
	}



	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String toString() {
        return new String("nombre = " + nombre + " | " +
            "ape1 = " + ape1 + " | " +
            "ape2 = " + ape2 + " | " +
            "email = " + email + " | " +
            "language = " + language + " | " +
            "country = " + country);
    }
    
    /* Test code. Uncomment for testing. */
//    public static void main (String[] args) {
//    
//        UserProfileDetailsVO userProfileDetailsVO = new UserProfileDetailsVO(
//            "Fernando", "Bellas Permuy", "fbellas@udc.es", "gl", "ES");
//            
//        System.out.println(userProfileDetailsVO);
//        
//    }    

}
