package com.threezebra.restapi.exception;

/**
 * @author vikas.sharma
 *
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userName) {

        super(
                String.format("User %s not found.", userName)
        );

    }
}
