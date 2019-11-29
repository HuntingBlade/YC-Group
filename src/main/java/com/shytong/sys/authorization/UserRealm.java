package com.shytong.sys.authorization;

import com.alibaba.fastjson.JSON;
import com.shytong.common.exception.ApiBizRunException;
import com.shytong.core.auth.AuthenticationInfo;
import com.shytong.core.auth.AuthenticationToken;
import com.shytong.core.auth.AuthenticingiRealm;
import com.shytong.core.constant.SyConstant;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sytong
 * @Package com.shytong.modules.sys
 * @Description:
 * @date 2018-04-0320:49
 */

@Service("userRealm")
public class UserRealm implements AuthenticingiRealm {
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        return null;
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        return null;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfoByString(String obj) {
        return null;
    }
//    @Resource(name = "userDao")
////    private IUserDao userDao;
//
//    public static void main(String[] args) {
//
//String n=null;
//        System.out.println( DigestUtils.md5Hex(n));
//    }
//    @Override
//    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//        //token.getAuthType()
//        UserDo userDo=null;
//        if("wx".equals(token.getAuthType())){
//             userDo=userDao.findByIdentifier("1",token.getSysIdentifier(),token.getIdentifier());
//        }else{
//            userDo=userDao.findByIdentifier("0",token.getSysIdentifier(),token.getIdentifier());
//
//        }
//
//
//
//      if(userDo==null){
//
//         throw new ApiBizRunException(SyConstant.ERR_AUTH_USER_NOT_FOUND,"用户不存在");
//      }
//        UserDo u=new UserDo();
//
//
//        AuthenticationInfo a=new AuthenticationInfo<UserDo>(userDo.getId()
//                ,userDo.getPwd()
//                ,userDo.getId()
//                ,token.getSysIdentifier()
//                ,token.getIdentifier()
//                ,userDo);
//
//        userDo.setPwd("");
//        return a;
//    }
//
//    @Override
//    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationInfo authenticationInfo) {
//        return authenticationInfo;
//    }
//
//    @Override
//    public AuthenticationInfo getAuthenticationInfoByString(String obj) {
//        return JSON.parseObject(obj,new TypeReference<AuthenticationInfo<UserDo>>(){});
//    }
}
