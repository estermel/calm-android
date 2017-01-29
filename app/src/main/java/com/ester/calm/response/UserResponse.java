package com.ester.calm.response;

import com.ester.calm.model.User;

/**
 * Created by Ester on 23/11/2016.
 */

public class UserResponse {

    private String error;

    public String getError() {
        return error;
    }

    private User[] akun;

    public User[] getUsers() {
        return akun;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
