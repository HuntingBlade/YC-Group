package com.shytong.common.resultcode;

public class ResultCode {

    // 成功
    public static final String SUCCESS = "1";

    // 用户不存在
    public static final String USER_NOT_EXIST = "20001";
    // 用户未登陆
    public static final String USER_NOT_LOGGED_IN = "20002";
    // 用户名或密码错误
    public static final String USER_ACCOUNT_ERROR = "20003";
    // 用户账户已被禁用
    public static final String USER_ACCOUNT_FORBIDDEN = "20004";
    // 用户已存在
    public static final String USER_HAS_EXIST = "20005";

    // 验证码错误
    public static final String VALIDATE_CODE_ERROR = "21001";

    // 参数错误
    public static final String PARAMETER_ERROR = "40001";

}
