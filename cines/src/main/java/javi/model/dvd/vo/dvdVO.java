package javi.model.dvd.vo;

import java.io.Serializable;

import javi.model.producto.vo.ProductoVO;

public class dvdVO extends ProductoVO {

		private long idDvd; 
	 	private String titulo;
	    private String director;
	    private String clasificacion;
	    private String descripcion;
	    private boolean disponibilidad;
	    
	    public dvdVO(long idDvd, String titulo, String director, String clasificacion,
	    		String descripcion,double precio,boolean disponibilidad) {
	    	 super(idDvd, 2,precio,"Compra dvd "+idDvd);
	        this.idDvd = idDvd;
	    	this.titulo = titulo;
	        this.director = director;
	        this.clasificacion = clasificacion;
	        this.descripcion = descripcion;
	        this.disponibilidad= disponibilidad;
	        
	    }
	    
//	    public dvdVO(String titulo, String director, String clasificacion,
//	    		String descripcion,double precio,boolean disponibilidad) {
//	    	 super(2,precio,"Compra dvd ");
//	    	this.titulo = titulo;
//	        this.director = director;
//	        this.clasificacion = clasificacion;
//	        this.descripcion = descripcion;
//	        this.disponibilidad= disponibilidad;
//	        
//	    }
	    
	    
	    
	    public long getIdDvd() {
			return idDvd;
		}


		public void setIdDvd(long idDvd) {
			this.idDvd = idDvd;
		}


		public boolean isDisponibilidad() {
			return disponibilidad;
		}


		public void setDisponibilidad(boolean disponibilidad) {
			this.disponibilidad = disponibilidad;
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



		public String getTitulo() {
			return titulo;
		}
		
		
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}


		public String toString() {
	        return new String("titulo = " + titulo + " | " +
	            "director = " + director + " | " +
	            "clasificacion = " + clasificacion /*+ "Preferencia = " + Preferencia*/);
	    }
	    
	    /* Test code. Uncomment for testing. */
//	    public static void main (String[] args) {
//	    
//	        UserProfileDetailsVO userProfileDetailsVO = new UserProfileDetailsVO(
//	            "Fernando", "Bellas Permuy", "fbellas@udc.es", "gl", "ES");
//	        UserProfileVO userProfile = new UserProfileVO("fbellas",
//	            "YYYYYYYY", userProfileDetailsVO);
//	            
//	        System.out.println(userProfile);
//	        
//	    }    

}

