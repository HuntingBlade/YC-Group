package com.shytong.sys.user;

import java.io.Serializable;

/**
 * @author sytong
 * @Package com.shytong.sys.user
 * @Description:
 * @date 2018-01-3010:57
 */
public class UserDo<T>  implements Serializable {
    private static final long serialVersionUID = 1L;
    private T userDo;

    /**
     *id
     **/
    private String id;

    /**
     *昵称
     **/
    private String nickName;

    /**
     *账号
     **/
    private String account;

    /**
     *状态
     **/
    private Integer status;

    /**
     *手机号
     **/
    private String phone;

    /**
     *密码
     **/
    private String pwd;

    /**
     *头像
     **/
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public T getUserDo() {
        return userDo;
    }

    public void setUserDo(T userDo) {
        this.userDo = userDo;
    }
}
