package com.shytong.sys.authorization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shytong.common.exception.ApiBizRunException;
import com.shytong.core.auth.AuthenticationInfo;
import com.shytong.core.auth.AuthenticationToken;
import com.shytong.core.auth.AuthenticingiRealm;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.util.SyBeanUtil;
import com.shytong.modules.sys.user.dao.ISysUserDao;
import com.shytong.modules.sys.user.model.SysUserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sytong
 * @Package com.shytong.modules.sys
 * @Description:
 * @date 2018-04-0320:49
 */

@Service("sysUserMerchantRealm")
public class SysUserMerchantRealm implements AuthenticingiRealm {
    @Resource(name = "sysUserDao")
    private ISysUserDao sysUserDao;


    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

      SysUserDo sysUserDo= sysUserDao.getByAccount(token.getIdentifier(),"2",token.getSysIdentifier());

      if(sysUserDo==null){

         throw new ApiBizRunException(SyConstant.ERR_AUTH_USER_NOT_FOUND,"用户不存在");
      }

        AuthSysUser u=new AuthSysUser();
        SyBeanUtil.copyBeanToOther(sysUserDo,u);
        AuthenticationInfo a=new AuthenticationInfo<AuthSysUser>(sysUserDo.getId()
                ,sysUserDo.getPwd()
                ,sysUserDo.getId()
                ,null
                ,token.getIdentifier()
                ,u);

        u.setPwd("");
        return a;
    }

    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationInfo authenticationInfo) {


        return null;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfoByString(String obj) {

//        User<SysUserDo>


        return JSON.parseObject(obj,new TypeReference<AuthenticationInfo<AuthSysUser>>(){});
    }
}
