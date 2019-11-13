package com.shytong.core.auth;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:26
 */
public interface AuthorizingRealm {

    AuthorizationInfo doGetAuthorizationInfo(String principal);

}
