package com.shytong.core.auth;

import java.io.Serializable;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:11
 */
public class AuthenticationToken  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String identifier;
    private String certificate;
    private String sysIdentifier;
    private String authType;

    public AuthenticationToken(String identifier, String certificate, String authType,String sysIdentifier) {
        this.identifier = identifier;
        this.certificate = certificate;
        this.authType = authType;
        this.sysIdentifier=sysIdentifier;

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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }


}
