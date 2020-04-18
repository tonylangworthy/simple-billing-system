package com.langworthytech.simplebillingsystem.util.dto;

public class ResponseWrapperImpl {

    private String authFirstName;

    private String authLastName;

    private String authEmail;

    public String getAuthUsername() {
        return authFirstName + " " + authLastName;
    }

    public String getAuthFirstName() {
        return authFirstName;
    }

    public void setAuthFirstName(String authFirstName) {
        this.authFirstName = authFirstName;
    }

    public String getAuthLastName() {
        return authLastName;
    }

    public void setAuthLastName(String authLastName) {
        this.authLastName = authLastName;
    }

    public String getAuthEmail() {
        return authEmail;
    }

    public void setAuthEmail(String authEmail) {
        this.authEmail = authEmail;
    }
}
