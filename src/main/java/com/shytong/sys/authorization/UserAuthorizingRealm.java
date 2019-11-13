package com.shytong.sys.authorization;

import com.shytong.core.auth.AuthorizationInfo;
import com.shytong.core.auth.AuthorizingRealm;
import org.springframework.stereotype.Service;

/**
 * @author sytong
 * @Package com.shytong.modules.sys
 * @Description:
 * @date 2018-04-0320:49
 */

@Service("userAuthorizingRealm")
public class UserAuthorizingRealm implements AuthorizingRealm {

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(String principal)


    {


        return new AuthorizationInfo();
    }


}
