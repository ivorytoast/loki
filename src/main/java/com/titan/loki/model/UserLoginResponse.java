package com.titan.loki.model;

public class UserLoginResponse {

    private boolean isValidUser;

    public UserLoginResponse() {}

    public UserLoginResponse(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }

}
