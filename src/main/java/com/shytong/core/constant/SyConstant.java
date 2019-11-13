 package com.shytong.core.constant;

/**
 * @author sytong
 * @Package com.shytong.core
 * @Description:
 * @date 2018-03-2914:43
 */
public class SyConstant {
    public static final int ERR_AUTH=1000000;
    //配置错误
    public static final int ERR_AUTH_SYS_CONFIG=1000001;
    //认证失败
    public static final int ERR_AUTH_ENTIY_FAIL=1000002;
    //用户不存在
    public static final int ERR_AUTH_USER_NOT_FOUND=1000003;
    //鉴权失败
    public static final int ERR_AUTH_CERTIFICATE_WRONG=1000004;
    //获取资源权限失败
    public static final int ERR_AUTH_RESOURCE_FAIL=1000005;
    //未登陆
    public static final int ERR_AUTH_NO_LOGIN=1100000;

    public static final int ERR_DATA=2000000;
    public static final int ERR_DATA_NULL=2000001;
    public static final int ERR_DATA_HANDLE_FAIL=2000002;

    public static final int ERR_VALID=4000000;
    public static final int ERR_VALID_NULL=4000001;
    public static final int ERR_VALID_CAST=4000002;
    public static final int ERR_VALID_FORMAT=4000003;

    //获取授权
    public static final int ACT_TO_AUTH=5000001;


}
