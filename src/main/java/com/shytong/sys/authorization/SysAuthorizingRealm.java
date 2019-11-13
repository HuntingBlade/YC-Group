package com.shytong.sys.authorization;

import com.shytong.core.auth.AuthorizationInfo;
import com.shytong.core.auth.AuthorizingRealm;
import com.shytong.modules.sys.role.dao.ISysResourceRoleDao;
import com.shytong.modules.sys.role.dao.IUserRoleDao;
import com.shytong.modules.sys.role.model.UserRoleDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author sytong
 * @Package com.shytong.modules.sys
 * @Description:
 * @date 2018-04-0320:49
 */

@Service("sysAuthorizingRealm")
public class SysAuthorizingRealm implements AuthorizingRealm {
    @Resource(name="userRoleDao")
    private IUserRoleDao userRoleDao;
    @Resource(name="sysResourceRoleDao")
    private ISysResourceRoleDao sysResourceRoleDao;
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(String principal)


    {//获取角色
         AuthorizationInfo authorizationInfo=new AuthorizationInfo();
         List<UserRoleDo> userRoleDos=userRoleDao.getByUserId(principal);
         if(userRoleDos!=null&&!userRoleDos.isEmpty()){
             authorizationInfo.setRoles(new HashSet<>());
             authorizationInfo.setResouces(new HashSet<>());
             authorizationInfo.setResouceObjects(new HashSet<>());
             for (UserRoleDo userRoleDo:userRoleDos) {
                 authorizationInfo.getRoles().add(userRoleDo.getRoleId());
                 List<CommResource>  sysResourceDos= sysResourceRoleDao.getRoleResource(userRoleDo.getRoleId());
                for (CommResource commResource:sysResourceDos){




                    authorizationInfo.getResouces().add(commResource.getCode());
                    //权限集中起来
                    authorizationInfo.getResouceObjects().add(commResource);


                }

             }


         }
        //获取权限
        return authorizationInfo;
    }


}
