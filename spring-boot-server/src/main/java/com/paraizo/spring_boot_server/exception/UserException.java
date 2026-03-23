package com.paraizo.spring_boot_server.exception;

import com.paraizo.spring_boot_server.model.User;

public class UserException extends RuntimeException {
    protected final User user;

    public UserException(User user) {
        this.user = user;
    }

    public User getCustomer() {
        return user;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
