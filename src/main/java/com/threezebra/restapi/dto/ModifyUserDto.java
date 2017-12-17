package com.threezebra.restapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author vikas.sharma
 *
 */
public class ModifyUserDto {

    @NotNull
    @Size(min = 1)
    String fullName;

    String password;

    String roles[];

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
