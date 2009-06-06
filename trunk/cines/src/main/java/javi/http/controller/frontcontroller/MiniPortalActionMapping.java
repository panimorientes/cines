package javi.http.controller.frontcontroller;

import org.apache.struts.action.ActionMapping;

public class MiniPortalActionMapping extends ActionMapping {

    private boolean authenticationRequired;
    private boolean adminRequired;
    
    public MiniPortalActionMapping() {
        authenticationRequired = false;
        adminRequired = false;
    }
    
    public boolean getAuthenticationRequired() {
        return authenticationRequired;
    }
    
    public void setAuthenticationRequired(boolean authenticationRequired) {
        this.authenticationRequired = authenticationRequired;
    }

	public boolean isAdminRequired() {
		return adminRequired;
	}

	public void setAdminRequired(boolean adminRequired) {
		this.adminRequired = adminRequired;
	}

   
}
