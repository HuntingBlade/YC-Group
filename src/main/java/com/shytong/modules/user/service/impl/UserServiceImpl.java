package com.shytong.modules.user.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.constant.SysTemCodeConstant;
import com.shytong.core.auth.AuthenticationInfo;
import com.shytong.core.auth.Subject;
import com.shytong.core.auth.SyAuthUtil;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyBeanUtil;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyStringUtils;
import com.shytong.modules.user.dao.IUserDao;
import com.shytong.modules.user.model.UserDo;
import com.shytong.modules.user.model.UserDto;
import com.shytong.modules.user.service.IUserBizService;
import com.shytong.modules.user.service.IUserService;
import com.shytong.modules.wx.model.WxFatDo;
import com.shytong.modules.wx.model.WxMpAuth2AccessToken;
import com.shytong.modules.wx.model.WxMpUser;
import com.shytong.modules.wx.service.IWxAuthDeal;
import com.shytong.modules.wx.service.IWxMpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
/**
 * @author 
 * @Package com.shytong.modules.user.service.impl
 * @Description:
 * @date 2018-04-16 16:46:40
 */
    @Service("userService")
public class UserServiceImpl implements IUserService,IWxAuthDeal {
    @Resource(name ="userDao")
    private IUserDao userDao;
    @Value("${sy.appmode}")

    private String appMode;

    @Value("${sy.main.wxmpid}")

    private String wxmpid;
    @Resource(name ="wxMpService")
    private      IWxMpService wxMpService;
    @Value("${authorizeUrl}")
    private String authorizeUrl;

    private int n=0;

    @Resource(name ="userBizService")
    private IUserBizService userBizService;
    public static List<Object> lock=new ArrayList<>();
    static {
        for(int i=0;i<16;i++){
            lock.add(new Object());
        }
    }

    @Override
    public int insert(UserDo userDo) throws ApiBizException {
        int n=0;

        if(userDo.getId()!=null){
            userDo.setId(SyIdUtils.uuid());
        }

        n=userDao.insert(userDo);
         return n;
    }

    @Override
    public int updateById(UserDo  userDo) throws ApiBizException {

        return this.updateById(userDo,null);


    }

    @Override
    public int updateById(UserDo userDo, String fields) throws ApiBizException {
        int n=0;
        n=userDao.updateById(userDo,fields);
        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;

    }

    @Override
    public UserDo getById(String id) throws ApiBizException {

        return this.getById(id,null);
    }

    @Override
    public UserDo getById(String id, String fields) throws ApiBizException {

        UserDo syDo=userDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return userDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    public int deleteById(String id) throws ApiBizException {
        int n=userDao.deleteById(id);

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }

    @Override
    public void wxlogin(SyMap params, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
//        try {

            //成功后的跳转地址
        String target = params.getString("target");
            //微信授权后跳转地址
        WxFatDo wxFatDo= wxMpService.getWxFatDo(wxmpid);
        String auType = "snsapi_userinfo";
//        String auType = "snsapi_base";

        String url=authorizeUrl+"?mode=2&op=1&target="+target+getParams(httpServletRequest);

        System.out.println("mmmmmmmmmmm"+n);
        n++;
          wxFatDo.auth2(auType,url,httpServletResponse);

    }

    @Override
    public void reg(UserDto userDto) throws ApiBizException {

        synchronized (this){

            if(userDao.search().eq("account",userDto.getAccount()).isExists()){


                throw  new ApiBizException(-1,"用户名已注册");

            }
            UserDo userDo=new UserDo();
            SyBeanUtil.copyBeanToOther(userDto,userDo);
            userDo.setId(SyIdUtils.uuid());

            userDo.setPwd( SyAuthUtil.getCertificate(userDo.getPwd(),userDo.getId()));
            userDo.setStatus(1);
            userDao.insert(userDo);

        }




    }

    @Override
    public void updatePsw(UserDo userDo,String newPwd) throws ApiBizException {
        userDo.setPwd(newPwd);
        userDao.updateById(userDo);
    }

    private String getParams( HttpServletRequest httpServletRequest){


        Enumeration<String> enumeration=httpServletRequest.getParameterNames();
        StringBuilder queryParams=new StringBuilder();
        while (enumeration.hasMoreElements()) {
            String o = enumeration.nextElement();
            if (o != null && o.startsWith("sy")) {
                queryParams.append("&").append(o).append("=").append(httpServletRequest.getParameter(o));
            }
        }

        return  queryParams.toString();

    }


    @Override
    public void dealBaseInfo(SyMap params, WxMpAuth2AccessToken wxMpAuth2AccessToken, HttpServletResponse httpServletResponse) {


        System.out.println("1111111111");
    }

    @Override
    public void dealUserInfo(SyMap params, WxMpUser wxMpUser, HttpServletResponse httpServletResponse) throws ApiBizException {

        System.out.println("^^^^^^^^^^"+n);

        //验证用户是否存在
         UserDo userDo= userDao.findByUnionId(wxMpUser.getUnionId());
        if(userDo==null){
             //不存在就注册用户信息
             userDo=new UserDo();
             userDo.setAvatar(wxMpUser.getHeadImgUrl());
             userDo.setUnionId(wxMpUser.getUnionId());
             userDo.setSex(wxMpUser.getSex());
             userDo.setCountry(wxMpUser.getCountry());
             userDo.setCity(wxMpUser.getCity());
             userDo.setNickName(wxMpUser.getNickname());
             userDo.setStatus(1);
             userDo.setOpenId(wxMpUser.getOpenId());
             userDo.setId(SyIdUtils.uuid());
             synchronized (lock.get(SyStringUtils.hashIndex(userDo.getId(),16))){
                 userDo=  userBizService.addUserByunionId(userDo);
             }
         }

        //登陆
        Subject subject= SyAuthUtil.getSubject(SysTemCodeConstant.SYSTEM_USER);

        if(subject.isAuthented()){

            String userID=subject.getAuthenticationInfo().getUserID();

            if(userDo.getId().equals(userID)){


                return;
            }


        }



            userDo.setPwd("");
            AuthenticationInfo authenticationInfo=new AuthenticationInfo(userDo.getId(), null,userDo.getId(),null,userDo.getUnionId(),userDo);
            subject.login(authenticationInfo);






    }




}
