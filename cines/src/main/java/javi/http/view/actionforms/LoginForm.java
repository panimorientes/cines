package javi.http.view.actionforms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class LoginForm extends DefaultActionForm {

    private String login;
    private String pass;
    private boolean rememberMyPassword;
    
    public LoginForm() {
        reset();
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String loginName) {
        this.login = loginName.trim();
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String password) {
        this.pass = password;
    }
    
    public boolean getRememberMyPassword() {
        return rememberMyPassword;
    }
    
    public void setRememberMyPassword(boolean rememberMyPassword) {
        this.rememberMyPassword = rememberMyPassword;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();

        PropertyValidator.validateMandatory(errors, "login", login);
        PropertyValidator.validateMandatory(errors, "pass", pass);
        
        return errors;
        
    }
    
    private void reset() {
        login = null;
        pass = null;
        rememberMyPassword = false;
    }

}
