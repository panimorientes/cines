package javi.model.userprofile.vo;

import java.io.Serializable;

public class UserProfileVO implements Serializable {

    private String loginName;
    private String encryptedPassword;
    private UserProfileDetailsVO userProfileDetailsVO;
    
    public UserProfileVO(String loginName, String encryptedPassword,
        UserProfileDetailsVO userProfileDetailsVO) {
        
        this.loginName = loginName;
        this.encryptedPassword = encryptedPassword;
        this.userProfileDetailsVO = userProfileDetailsVO;
        
    }
    
    public String getLoginName() {
        return loginName;
    }
    
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
    
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    
    public UserProfileDetailsVO getUserProfileDetailsVO() {
        return userProfileDetailsVO;
    }
    
    public void setUserProfileDetailsVO(UserProfileDetailsVO 
        userProfileDetailsVO) {
        
        this.userProfileDetailsVO = userProfileDetailsVO;
        
    }
    
    public String toString() {
        return new String("loginName = " + loginName + " | " +
            "encryptedPassword = " + encryptedPassword + " | " +
            userProfileDetailsVO);
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
