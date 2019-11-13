package com.shytong.sys.sms.service;

import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:33
 */
public class AliConfig extends SmsConfig {
    private  String url;
    private  String appkey;
    private  String secret;
    private  String freeSignName;
    private  Map<String,String> codes;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getFreeSignName() {
        return freeSignName;
    }

    public void setFreeSignName(String freeSignName) {
        this.freeSignName = freeSignName;
    }

    public Map<String, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }
}
