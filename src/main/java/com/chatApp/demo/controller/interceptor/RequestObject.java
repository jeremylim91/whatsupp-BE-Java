package com.chatApp.demo.controller.interceptor;

import com.chatApp.demo.controller.CookieNames;

public class RequestObject {
        private String loggedInHash;
        private String loggedInUserId;
        private String loggedInUsername;

//=========Constructor============
    public RequestObject(String username, String userId, String hash) {
        this.loggedInHash=hash;
        this.loggedInUserId= userId;
        this.loggedInUsername= username;
    }
//=================================

    @Override
    public String toString() {
        return "RequestObject{" +
                "loggedInHash='" + loggedInHash + '\'' +
                ", loggedInUserId='" + loggedInUserId + '\'' +
                ", loggedInUsername='" + loggedInUsername + '\'' +
                '}';
    }

    public String getLoggedInHash() {
        return loggedInHash;
    }

    public void setLoggedInHash(String loggedInHash) {
        this.loggedInHash = loggedInHash;
    }

    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }
}


