package javi.model.direccion.vo;

import java.io.Serializable;

public class DireccionVO implements Serializable {

    private Long cp;
    private String ciudad;
    private String direccion;
    private Long numero;
    private String login;
    
    public DireccionVO(Long cp, String ciudad, String direccion,
    		Long numero, String login) {
        
        this.cp = cp;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.numero = numero;
        this.login = login;
        
    }
    
    

	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public Long getCp() {
		return cp;
	}



	public void setCp(Long cp) {
		this.cp = cp;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public Long getNumero() {
		return numero;
	}



	public void setNumero(Long numero) {
		this.numero = numero;
	}



	public String toString() {
        return new String("cp = " + cp + " | " +
            "ciudad = " + ciudad + " | " +
            "direccion = " + direccion +" | " + "numero = " + numero+ " | " + "login = " + login);
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
