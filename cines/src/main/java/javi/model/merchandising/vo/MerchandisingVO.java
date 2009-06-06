package javi.model.merchandising.vo;

import java.io.Serializable;

import javi.model.producto.vo.ProductoVO;

public class MerchandisingVO extends ProductoVO{

	private long idMerchandising;
	private String descrip;
    private Long referencia;
    private String talla;
    private boolean disponibilidad;
    
    public MerchandisingVO(long idMerchandising,String descrip, Long referencia, String talla, double precio,boolean disponibilidad) {
        super(1,precio,"Compra merchandasing "+referencia);
    	this.idMerchandising = idMerchandising;
    	this.descrip = descrip;
        this.referencia = referencia;
        this.talla = talla;
        this.disponibilidad = disponibilidad;
    
        
    }
    
    public MerchandisingVO(String descrip, Long referencia, String talla, double precio,
    		boolean disponibilidad) {
        super(1,precio,"Compra merchandasing ");

    	this.descrip = descrip;
        this.referencia = referencia;
        this.talla = talla;
        this.disponibilidad = disponibilidad;
    
        
    }
    
	
    	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}





	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}





	public String getDescrip() {
		return descrip;
	}



	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}



	public long getIdMerchandising() {
		return idMerchandising;
	}



	public void setIdMerchandising(long idMerchandising) {
		this.idMerchandising = idMerchandising;
	}






	public Long getReferencia() {
		return referencia;
	}





	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}




	public String getTalla() {
		return talla;
	}





	public void setTalla(String talla) {
		this.talla = talla;
	}
	
	public String getDescripcion() {
		return "Merchandising";
	}

}
