package com.shytong.core.auth;

/**
 * @author sytong
 * @Package com.shytong.core.auth
 * @Description:
 * @date 2018-04-0114:27
 */
public interface AuthenticingiRealm {

     AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token);
    AuthenticationInfo doGetAuthenticationInfo(AuthenticationInfo authenticationInfo);

    AuthenticationInfo getAuthenticationInfoByString(String obj);

}
