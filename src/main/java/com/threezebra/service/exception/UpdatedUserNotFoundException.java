package com.threezebra.service.exception;


/**
 * @author vikas.sharma
 *
 */
public class UpdatedUserNotFoundException extends Exception {

    String userName;

    public UpdatedUserNotFoundException(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
