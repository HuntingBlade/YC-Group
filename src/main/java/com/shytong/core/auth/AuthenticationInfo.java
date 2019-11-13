package com.shytong.core.auth;

import java.io.Serializable;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:27
 */
public class AuthenticationInfo<T>  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  userID;
    private String  certificate;
    private String  salt;
    private String  sysIdentifier;
    private String  identifier;
    private String   loginType;
    private T userInfo;
    public AuthenticationInfo(){


    }

    public AuthenticationInfo(String userID, String certificate, String salt, String sysIdentifier, String identifier, T userInfo) {
        this.userID = userID;
        this.certificate = certificate;
        this.salt = salt;
        this.sysIdentifier = sysIdentifier;
        this.identifier = identifier;
        this.userInfo = userInfo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getSysIdentifier() {
        return sysIdentifier;
    }

    public void setSysIdentifier(String sysIdentifier) {
        this.sysIdentifier = sysIdentifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
