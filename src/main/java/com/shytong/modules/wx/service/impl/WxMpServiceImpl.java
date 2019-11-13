package com.shytong.modules.wx.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.core.constant.SyConstant;
import com.shytong.core.constant.SyConstantInfo;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyStringUtils;
import com.shytong.modules.wx.dao.IWxMpDao;
import com.shytong.modules.wx.model.WxFatDo;
import com.shytong.modules.wx.model.WxMpAuth2AccessToken;
import com.shytong.modules.wx.model.WxMpDo;
import com.shytong.modules.wx.model.WxMpUser;
import com.shytong.modules.wx.service.IWxAuthDeal;
import com.shytong.modules.wx.service.IWxMpService;
import com.shytong.modules.wx.service.WxManager;
import com.taobao.api.internal.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 
 * @Package com.shytong.modules.wx.service.impl
 * @Description:
 * @date 2018-04-16 19:39:21
 */
    @Service("wxMpService")
public class WxMpServiceImpl implements IWxMpService  {
    @Resource(name ="wxMpDao")
    private IWxMpDao wxMpDao;

    @Value("${authorizeUrl}")
    private String authorizeUrl;
    @Value("${authorizeErrUrl}")
    private String authorizeErrUrl;
    @Value("${sy.appmode}")
    private String appmode;
    public static void main(String[] args) {
        try {


            System.out.println(new String(Base64.decode("aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2RhbmduaWFubWluZ3l1ZV9nZy9hcnRpY2xlL2RldGFpbHMvNDczNzM2OTk="),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private static volatile ConcurrentHashMap<String,WxFatDo> wxFatDos=new ConcurrentHashMap();

    @Override
    public int insert(WxMpDo wxMpDo) throws ApiBizException {
        int n=0;
        wxMpDo.setId(SyIdUtils.uuid());


        if(wxMpDao.search().eq("wxCode",wxMpDo.getWxCode()).isExists()){
            throw new ApiBizException(-1,"微信号已存在");
        }
        if(wxMpDao.search().eq("appId",wxMpDo.getAppId()).isExists()){
            throw new ApiBizException(-1,"微信号已存在");
        }
             n=wxMpDao.insert(wxMpDo);
         return n;
    }

    @Override
    public int updateById(WxMpDo  wxMpDo) throws ApiBizException {




        return this.updateById(wxMpDo,null);


    }

    @Override
    public int updateById(WxMpDo wxMpDo, String fields) throws ApiBizException {
        int n=0;

        if(wxMpDao.search().eq("wxCode",wxMpDo.getWxCode()).neq("id",wxMpDo.getId()).isExists()){
            throw new ApiBizException(-1,"微信号已存在");
        }
        if(wxMpDao.search().eq("appId",wxMpDo.getAppId()).neq("id",wxMpDo.getId()).isExists()){
            throw new ApiBizException(-1,"微信号已存在");
        }


        n=wxMpDao.updateById(wxMpDo,fields);
        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"更新失败");

        }
        return n;

    }

    @Override
    public WxMpDo getById(String id) throws ApiBizException {

        return this.getById(id,null);
    }

    @Override
    public WxMpDo getById(String id, String fields) throws ApiBizException {

        WxMpDo syDo=wxMpDao.getById(id,fields);
        if(syDo==null){
        throw new ApiBizException(SyConstant.ERR_DATA_NULL, SyConstantInfo.ERR_DATA_NULL);
        }
        return syDo;
    }

    @Override
    public PageInfo listOfPage(Map<String,Object> params, int pageNum, int pageSize) throws ApiBizException {

        return wxMpDao.listOfPage(params ,  pageNum,  pageSize);
    }
    @Override
    public int deleteById(String id) throws ApiBizException {
        int n=wxMpDao.deleteById(id);

        if(n==0){
        throw  new ApiBizException(SyConstant.ERR_DATA_HANDLE_FAIL,"删除失败");

        }

        return n;
    }

    @Override
    public WxFatDo getWxFatDo(String id) {
        if(id==null){

            return null;
        }
        WxFatDo wxFatDo=  wxFatDos.get(id);
        if(wxFatDo==null){
            synchronized (this){
                if( wxFatDos.get(id)==null){
                    WxMpDo s= wxMpDao.getById(id);
                    if(s!=null){
                        wxFatDo=new WxFatDo();
                        wxFatDo.setWxMpDo(s);
                        wxFatDos.put(id,wxFatDo);
                    }else {
                        return  null;
                    }
                }
            }
        }
        return  wxFatDos.get(id);
    }

    @Override
    public void authorize(SyMap params, HttpServletResponse httpServletResponse) {

        String code=params.getString("code");
        String mode=params.getString("mode");
        String op=params.getString("op");
        String target=params.getString("target");

        String wxid=params.getString("wxid");
        for(Object value:params.entrySet()){
            System.out.println(value);
        }
        WxFatDo wxFatDo=null;
            try {
                if(WxManager.ops.get(op)==null){

                    httpServletResponse.sendRedirect(authorizeErrUrl);
                }
                if(SyStringUtils.isBlank(code)||SyStringUtils.isBlank(wxid)){
                    httpServletResponse.sendRedirect(authorizeErrUrl);
                    return;
                }
                wxFatDo=this.getWxFatDo(wxid);
                if(wxFatDo==null){
                    httpServletResponse.sendRedirect(authorizeErrUrl);
                    return;
                }
                WxMpAuth2AccessToken wxMpAuth2AccessToken=null;

                WxMpUser wxMpUser=null;
                if ("dep".equals(appmode)) {

                    wxMpAuth2AccessToken=new WxMpAuth2AccessToken();
                    wxMpAuth2AccessToken.setAppId(wxFatDo.getWxMpDo().getAppId());
                    wxMpAuth2AccessToken.setOpenId("1111111111");
                    wxMpAuth2AccessToken.setUnionId("2222222222");

                }else{
                    wxMpAuth2AccessToken=   wxFatDo.oauth2getAccessToken(code);
                    if(wxMpAuth2AccessToken==null){
                        httpServletResponse.sendRedirect(authorizeErrUrl);
                        return;
                    }
                }





                IWxAuthDeal wxAuthDeal= WxManager.ops.get(op);
                if(mode.equals("2")){
                    if ("dep".equals(appmode)) {
                        wxMpUser.setAppId(wxFatDo.getWxMpDo().getAppId());
                        wxMpUser.setCity("测试");
                        wxMpUser.setCountry("中国");
                        wxMpUser.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eptFuvPNGWwOQ9TdIJlw1icagLZ9heHTrUzrImibBw87EyPLvIWgOiafXzet6MCAEsHoDsKdCibNibWFIg/132");
                        wxMpUser.setSex("2");
                        wxMpUser.setNickname("shytong");
                        wxMpUser.setOpenId(wxMpAuth2AccessToken.getOpenId());
                        wxMpUser.setUnionId(wxMpAuth2AccessToken.getUnionId());
                        wxMpUser.setProvince("福建省");

                    }else{

                        wxMpUser=wxFatDo.getUserInfo(wxMpAuth2AccessToken);
                    }






                    if(wxMpUser!=null){
                        wxAuthDeal.dealUserInfo(params,wxMpUser,httpServletResponse);
                    }else{
                        httpServletResponse.sendRedirect(authorizeErrUrl);
                    }
                }else{

                    wxAuthDeal.dealBaseInfo(params,wxMpAuth2AccessToken,httpServletResponse);

                }

                if(target==null){
                    httpServletResponse.sendRedirect(authorizeErrUrl);

                }
                String url=new String(Base64.decode(target));
                httpServletResponse.sendRedirect(new String(Base64.decode(target)));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ApiBizException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void auth2(SyMap params, HttpServletResponse httpServletResponse) {


        //成功后的跳转地址
        String target = params.getString("target");
        String mode= params.getString("mode");
        //微信授权后跳转地址
        WxFatDo wxFatDo= this.getWxFatDo(params.getString("wxmpid"));

        if(wxFatDo==null){


            return ;
        }
        String auType = "snsapi_base";
        if("2".equals(params.getString("mode"))){

                    auType = "snsapi_userinfo";
        }else{

            mode="1";
        }
        String url=authorizeUrl+"?mode="+mode+"&op="+params.getString("op")+"&target="+target+getParams(params);
        if ("dep".equals(appmode)) {
            try {
                httpServletResponse.sendRedirect(url+"&code=1&wxid="+params.getString("wxmpid"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ;
        }

        wxFatDo.auth2(auType,url,httpServletResponse);



    }
    private String getParams(SyMap params){
        StringBuilder queryParams=new StringBuilder();
        for(Object key :params.keySet()){

            if (key != null && key.toString().startsWith("sy")) {
                queryParams.append("&").append(key.toString()).append("=").append(params.getString(key));
            }

        }

        return  queryParams.toString();

    }

    public String getAuthorizeErrUrl() {
        return authorizeErrUrl;
    }

    public void setAuthorizeErrUrl(String authorizeErrUrl) {
        this.authorizeErrUrl = authorizeErrUrl;
    }
}
