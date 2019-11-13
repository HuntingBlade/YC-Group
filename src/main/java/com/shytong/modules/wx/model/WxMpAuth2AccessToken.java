package com.shytong.modules.wx.model;

import java.io.Serializable;

/**
 * @author sytong
 * @Package com.shytong.modules.wx.model
 * @Description:
 * @date 2018-04-1622:50
 */
public class WxMpAuth2AccessToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accessToken;

    private int expiresIn = -1;

    private String refreshToken;

    private String openId;

    private String scope;

    private String unionId;

    private String appId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccess_token(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpires_in(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefresh_token(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
