package com.shytong.core.auth;

import com.shytong.sys.authorization.CommResource;

import java.io.Serializable;
import java.util.Set;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:26
 */
public class AuthorizationInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //角色
    private Set<String> roles;
    //权限
    private Set<String> resouces;
    //权限
    private Set<CommResource> resouceObjects;



    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getResouces() {
        return resouces;
    }

    public void setResouces(Set<String> resouces) {
        this.resouces = resouces;
    }

    public Set<CommResource> getResouceObjects() {
        return resouceObjects;
    }

    public void setResouceObjects(Set<CommResource> resouceObjects) {
        this.resouceObjects = resouceObjects;
    }

}
