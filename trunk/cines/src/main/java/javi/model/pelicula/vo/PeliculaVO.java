package javi.model.pelicula.vo;

import java.io.Serializable;

public class PeliculaVO implements Serializable {

	private long idPelicula;
    private String titulo;
    private String director;
    private String clasificacion;
    private String descripcion;
    //private int Preferencia;
    
    public PeliculaVO(long idPelicula, String titulo, String director, String clasificacion,
    		String descripcion/*, int Preferencia*/) {
        
    	this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
        //this.Preferencia = Preferencia;
        
    }
    
    
    public long getIdPelicula() {
		return idPelicula;
	}


	public void setIdPelicula(long idPelicula) {
		this.idPelicula = idPelicula;
	}


	public String getClasificacion() {
		return clasificacion;
	}


	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	/*public int getPreferencia() {
		return Preferencia;
	}


	public void setPreferencia(int preferencia) {
		Preferencia = preferencia;
	}*/


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String toString() {
        return new String("idPelicula = " + idPelicula + " | " +"titulo = " + titulo + " | " +
            "director = " + director + " | " +
            "clasificacion = " + clasificacion /*+ "Preferencia = " + Preferencia*/);
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
