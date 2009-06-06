package javi.model.tarjeta.vo;

import java.io.Serializable;

public class TarjetaVO implements Serializable {

    private Long tarjeta;
    private String login;

        
    public TarjetaVO(String login, Long tarjeta) {
        
        this.tarjeta = tarjeta;
        this.login = login; 
    }
     
    public Long getNumTarjeta() {
		return tarjeta;
	}


	public void setNumTarjeta(Long tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	 
	public String getLogin() {
		return login;
	}


	public void setNLogin(String login) {
		this.login = login;
	}



	public String toString() {
        return new String("tarjeta = " + tarjeta + "login = " + login);
    }

}