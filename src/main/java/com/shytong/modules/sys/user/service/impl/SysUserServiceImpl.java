package com.shytong.modules.sys.user.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.constant.VerifyCodeConsts;
import com.shytong.core.auth.AuthenticationToken;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyStringUtils;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.sys.user.dao.ISysUserDao;
import com.shytong.modules.sys.user.model.SysUserDo;
import com.shytong.modules.sys.user.model.SysUserDto;
import com.shytong.modules.sys.user.service.ISysUserService;
import com.shytong.sys.authorization.AuthSysUser;
import com.shytong.sys.authorization.SyAuthSystemUtil;
import com.shytong.sys.authorization.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
/**
 * @author 
 * @Package com.shytong.modules.sys.user.service.impl
 * @Description:
 * @date 2018-04-02 22:03:27
 */
    @Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService  {
    @Resource(name ="sysUserDao")
    private ISysUserDao sysUserDao;

    @Override
    public int insert(SysUserDo sysUserDo) throws ApiBizException {
        int n=0;
        //判断用户是否存在
        synchronized (this){

            sysUserDo.setType("1");
            SysUserDo oldSysUserDo=sysUserDao.getByAccount(sysUserDo.getAccount(),"1",null);
            if(oldSysUserDo!=null){
                throw new ApiBizException(-1,"用户已存在");
            }

            sysUserDo.setId(SyIdUtils.uuid());
            sysUserDo.setPwd(DigestUtils.md5Hex(sysUserDo.getPwd()+sysUserDo.getId()));
            n = sysUserDao.insert(sysUserDo);
        }

        return n;
    }

    @Override
    public int updateById(SysUserDo  sysUserDo) throws ApiBizException {
        return  this.updateById(sysUserDo,null);


    }

    @Override
    public int updateById(SysUserDo sysUserDo, String fields) throws ApiBizException {
        int n=0;
        synchronized (this){


            if(sysUserDo.getAccount()!=null){
                SysUserDo oldSysUserDo=sysUserDao.getByAccount(sysUserDo.getAccount(),"1",null);
                if(oldSysUserDo!=null&&!oldSysUserDo.getId().equals(sysUserDo.getId())){

                    throw new ApiBizException(-1,"用户已存在");
                }
            }



            n= sysUserDao.updateById(sysUserDo,fields);
            if(n==0){
                throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

            }
        }

        return n;

    }

    @Override
    public SysUserDo getById(String id) throws ApiBizException {


        return this.getById(id,null);
    }

    @Override
    public SysUserDo getById(String id, String fields) throws ApiBizException {

        SysUserDo syDo=sysUserDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return sysUserDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    public int deleteById(String id) throws ApiBizException {
        int n=sysUserDao.deleteById(id);

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }

    @Override
    public int login(SysUserDto sysUserDto) throws ApiBizException {

         if(1!=1)
        SyValidationUtils.checkVerifyCode(VerifyCodeConsts.MANAGER_LOGIN,sysUserDto.getVerifyCode());


      String systemCode=sysUserDto.getSystemCode();

      if(SyStringUtils.isBlank(systemCode)){
          systemCode= SysTemCodeConstant.SYSTEM_MANAGER;
      }
      Subject s= SyAuthUtil.getSubject(systemCode);
      AuthenticationToken authenticationToken=new AuthenticationToken(sysUserDto.getAccount(),sysUserDto.getPwd(),"login",sysUserDto.getOrgId());
              if(s.isAuthented()){
                  s.loginOut();
                  s= SyAuthUtil.getSubject(systemCode);
              }
              s.login(authenticationToken);
              return 1;
    }

    @Override
    public Object getPersonal(User user) throws ApiBizException {
        return user;
    }

    @Override
    public Integer loginOut() throws ApiBizException {


        Subject s= SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_MANAGER);
        s.loginOut();
        return 1;
    }

    @Override
    public PageInfo getViewResources() throws ApiBizException {
       Subject s= SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_MANAGER);

       if(!s.isAuthented()){

           throw  new ApiBizException(-1,"请先登陆");
       }

        PageInfo p=new PageInfo();
        p.setList(s.getViewResource());
        return p;
    }

    @Override
    public int editPersonal(SysUserDo sysUserDo,String fields) throws ApiBizException {
            int n=0;
        n= sysUserDao.updateById(sysUserDo,fields);
        if(n==0){
            throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }


        return n;


    }


    @Override
    public int changePwd(SysUserDto syUserDto) throws ApiBizException {


        SysUserDo s  =   new SysUserDo();
        s.setId(syUserDto.getId());
        //验证密码是否一致
//        String oldPwd=SyAuthUtil.getCertificate(syUserDto.getOldPwd(),u.getId());
//        if(!oldPwd.equals(s.getPwd())){
//            throw new ApiBizException(98,"密码错误");
//
//        }
            s.setPwd(SyAuthUtil.getCertificate(syUserDto.getPwd(),syUserDto.getId()));
           return sysUserDao.updateById(s,"pwd");

    }
}
