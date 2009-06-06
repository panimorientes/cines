package javi.model.userfacade.exceptions;

import es.udc.fbellas.j2ee.util.exceptions.ModelException;

public class TarjetaDuplicadaException extends ModelException {

   
	private int tarjeta;
    
    public TarjetaDuplicadaException(int tarjeta) {
        super("Tarjeta duplicada exception => tarjeta = " + tarjeta);
        this.tarjeta = tarjeta;
    }
    
    public int getTarjeta() {
        return tarjeta;
    }
    
    /* Test code. Uncomment for testing. */
//    public static void main(String[] args) {
//    
//        try {
//            throw new IncorrectPasswordException("fbellas");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    
//    }    

}

