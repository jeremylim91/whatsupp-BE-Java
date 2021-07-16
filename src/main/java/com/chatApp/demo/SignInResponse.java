package com.chatApp.demo;

import com.chatApp.demo.model.User;

public class SignInResponse {
    private boolean isAuthenticated;
    private User user;

    public SignInResponse(boolean isAuthenticated, User user) {
        this.isAuthenticated = isAuthenticated;
        this.user = user;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
