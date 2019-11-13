package com.shytong.core.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-1821:30
 */
@JsonIgnoreProperties({"subActId"})
public class SyResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String info;
    private String validType;

    private String subActId;
    private String typeCode;
    private String validValue;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getSubActId() {
        return subActId;
    }

    public void setSubActId(String subActId) {
        this.subActId = subActId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getValidValue() {
        return validValue;
    }

    public void setValidValue(String validValue) {
        this.validValue = validValue;
    }
}
