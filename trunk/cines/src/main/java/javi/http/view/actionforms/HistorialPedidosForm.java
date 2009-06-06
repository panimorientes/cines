package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;

public class HistorialPedidosForm extends DefaultActionForm {

    private Double idProducto;
    private Double numLineas;
    private Double numUnidades;
    private Double idPedido;
    
    public HistorialPedidosForm() {
        reset();
    }
    
   
   

	public Double getIdPedido() {
		return idPedido;
	}




	public void setIdPedido(Double idPedido) {
		this.idPedido = idPedido;
	}




	public Double getIdProducto() {
		return idProducto;
	}




	public void setIdProducto(Double idProducto) {
		this.idProducto = idProducto;
	}



/*
	public Double getNumLineas() {
		return numLineas;
	}




	public void setNumLineas(Double numLineas) {
		this.numLineas = numLineas;
	}
*/



	public Double getNumUnidades() {
		return numUnidades;
	}




	public void setNumUnidades(Double numUnidades) {
		this.numUnidades = numUnidades;
	}




	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
   
    private void reset() {
       idProducto=null;
       numLineas=null;
       numUnidades=null;
       idPedido=null;
        
    }

}
