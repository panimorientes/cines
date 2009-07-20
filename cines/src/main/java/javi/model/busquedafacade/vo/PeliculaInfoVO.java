package javi.model.busquedafacade.vo;

import java.sql.Time;
import java.util.Calendar;

public class PeliculaInfoVO {

	private Long idPelicula;
	private String titulo;
	private Long idSesion;
	private Calendar fecha;
	private Time hora;
	private Long idCine;
	private String nombre;

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Long idSesion) {
		this.idSesion = idSesion;
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

	public Long getIdCine() {
		return idCine;
	}

	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PeliculaInfoVO(Long idPelicula, String titulo, Long idSesion,
			Calendar fecha, Time hora, Long idCine, String nombre) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.idSesion = idSesion;
		this.fecha = fecha;
		this.hora = hora;
		this.idCine = idCine;
		this.nombre = nombre;
	}

}
