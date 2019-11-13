package com.shytong.sys.user.service;

/**
 * @author sytong
 * @Package com.shytong.sys.user.service
 * @Description:
 * @date 2018-01-2415:47
 */
public class UserConfig {
    private String loginType;
    private String systemCode;
    private String regType;
    private String resetPwdType;
    private String nameRule;
    private int nameLenMin;
    private int namelenMax;
    private String key;
    private String appId;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getResetPwdType() {
        return resetPwdType;
    }

    public void setResetPwdType(String resetPwdType) {
        this.resetPwdType = resetPwdType;
    }

    public int getNameLenMin() {
        return nameLenMin;
    }

    public void setNameLenMin(int nameLenMin) {
        this.nameLenMin = nameLenMin;
    }

    public int getNamelenMax() {
        return namelenMax;
    }

    public void setNamelenMax(int namelenMax) {
        this.namelenMax = namelenMax;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getNameRule() {
        return nameRule;
    }

    public void setNameRule(String nameRule) {
        this.nameRule = nameRule;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
