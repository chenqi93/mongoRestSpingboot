package com.threezebra.restapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author vikas.sharma
 *
 */
public class CreateUserDto {

    @NotNull
    @Size(min = 1)
    private String fullName;

    @NotNull
    @Size(min = 1)
    private String userName;

    @NotNull
    @Size(min = 1)
    private String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
