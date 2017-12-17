
package com.threezebra.onesimple.json;

import java.util.HashMap;
import java.util.Map;

public class UsertypesJobrole {

    private String usertype;
    private String jobrole;
    private String permissionGroup;
    private String distibutionGroup;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getJobrole() {
        return jobrole;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public String getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(String permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public String getDistibutionGroup() {
        return distibutionGroup;
    }

    public void setDistibutionGroup(String distibutionGroup) {
        this.distibutionGroup = distibutionGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
