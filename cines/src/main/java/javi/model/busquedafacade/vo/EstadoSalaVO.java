package javi.model.busquedafacade.vo;

import java.io.Serializable;

import javax.swing.text.StyledEditorKit.BoldAction;

import javi.model.ticket.vo.TicketVO;

public class EstadoSalaVO implements Serializable{
	
	private TicketVO [][] butacas;
	private long numFilas;
	private long numButacas;
	private boolean numerada;
	          
	public boolean getNumerada() {
		return numerada;
	}

	public void setNumerada( boolean numerada) {
		this.numerada = numerada;
	}

	public EstadoSalaVO(long numFilas, long numButacas){
		
		this.numFilas = numFilas;
		this.numButacas = numButacas;
		
		butacas = new TicketVO [new Long(numFilas).intValue()][new Long(numButacas).intValue()];
		
	}

	/**
	 * @return the numButacas
	 */
	public long getNumButacas() {
		return numButacas;
	}

	/**
	 * @param numButacas the numButacas to set
	 */
	public void setNumButacas(long numButacas) {
		this.numButacas = numButacas;
	}

	/**
	 * @return the numFilas
	 */
	public long getNumFilas() {
		return numFilas;
	}

	/**
	 * @param numFilas the numFilas to set
	 */
	public void setNumFilas(long numFilas) {
		this.numFilas = numFilas;
	}
	
	public TicketVO getButaca(int numFila, int numButaca){
		return butacas[numFila][numButaca];
		
	}
	
	public void setButaca(int numFila, int numButaca, TicketVO butaca){
		this.butacas[numFila][numButaca] = butaca;
	}
	public TicketVO[][] getButacas(){
		return butacas;
	}
	
	
}	          
