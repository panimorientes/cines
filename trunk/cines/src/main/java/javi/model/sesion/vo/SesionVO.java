package javi.model.sesion.vo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

public class SesionVO implements Serializable {

	private Long idSesion;
    private Calendar fecha;
    private Time hora;
    private double precio;
    private boolean numerada;
    private Long idPelicula;
    private Long idSala;
    private Long idCine;
    
    public SesionVO(Calendar fecha, Time hora, double precio,boolean numerada,Long idPelicula, Long idSala, Long idCine) {
        
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.numerada = numerada;
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.idCine = idCine;
        
    }
    
    


	/**
	 * @param idSesion
	 * @param fecha
	 * @param hora
	 * @param precio
	 * @param numButaca
	 * @param numerada
	 * @param titulo
	 * @param idSala
	 * @param nombre
	 */
	public SesionVO(Long idSesion, Calendar fecha, Time hora, double precio, boolean numerada, long idPelicula, Long idSala, Long idCine) {
		super();
		this.idSesion = idSesion;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.numerada = numerada;
		this.idPelicula = idPelicula;
		this.idSala = idSala;
		this.idCine = idCine;
	}




	public Calendar getFecha() {
		return fecha;
	}


	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}



	public Time getHora() {
		return hora;
	}



	public void setHora(Time hora) {
		this.hora = hora;
	}



	public Long getIdSala() {
		return idSala;
	}



	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}


	public boolean isNumerada() {
		return numerada;
	}



	public void setNumerada(boolean numerada) {
		this.numerada = numerada;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	


	public long getIdPelicula() {
		return idPelicula;
	}




	public void setIdPelicula(long idPelicula) {
		this.idPelicula = idPelicula;
	}




	


	public Long getIdCine() {
		return idCine;
	}




	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}




	public String toString() {
        return new String("fecha = " + fecha + " | " +
            "hora = " + hora + " | " +
            "precio = " + precio +  "numerada = " + numerada + 
            "idPelicula = "+ idPelicula+ "idSala = "+ idSala + "idCine = " + idCine);
    }



	/**
	 * @return the idSesion
	 */
	public Long getIdSesion() {
		return idSesion;
	}



	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
	}




	/**
	 * @param idSesion
	 * @param fecha
	 * @param hora
	 * @param numerada
	 * @param titulo
	 * @param idSala
	 * @param nombre
	 */
	public SesionVO(Long idSesion, Calendar fecha, Time hora, boolean numerada, long idPelicula, Long idSala, Long idCine) {
		super();
		this.idSesion = idSesion;
		this.fecha = fecha;
		this.hora = hora;
		this.numerada = numerada;
		this.idPelicula = idPelicula;
		this.idSala = idSala;
		this.idCine = idCine;
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
