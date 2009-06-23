package javi.model.clasificacion.vo;

import java.io.Serializable;

public class ClasificacionVO implements Serializable {
	
	private String nombre;
	
	public ClasificacionVO(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}

}
