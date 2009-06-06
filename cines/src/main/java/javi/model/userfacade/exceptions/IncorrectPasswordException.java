package javi.model.userfacade.exceptions;

import es.udc.fbellas.j2ee.util.exceptions.ModelException;

public class IncorrectPasswordException extends ModelException {

    private String loginName;
    
    public IncorrectPasswordException(String loginName) {
        super("Incorrect password exception => loginName = " + loginName);
        this.loginName = loginName;
    }
    
    public String getLoginName() {
        return loginName;
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
