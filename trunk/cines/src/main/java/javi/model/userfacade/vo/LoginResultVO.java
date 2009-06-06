package javi.model.userfacade.vo;

import java.io.Serializable;

public class LoginResultVO implements Serializable {

    private String firstName;
    private String encryptedPassword;
    private String language;
    private String country;

    public LoginResultVO(String firstName, String encryptedPassword,
        String language, String country) {
        
        this.firstName = firstName;
        this.encryptedPassword = encryptedPassword;
        this.language = language;
        this.country = country;

    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String toString() {
        return new String("nombre = " + firstName + " | " +
            "pass = " + encryptedPassword + " | " +
            "lenguaje = " + language + " | " +
            "pais = " + country);
    }
    
    /* Test code. Uncomment for testing. */
//    public static void main (String[] args) {
//    
//        LoginResultVO loginResultVO = new LoginResultVO("Fernando",
//            "YYYYYYYY", "gl", "ES");
//            
//        System.out.println(loginResultVO);
//        
//    }    

}
