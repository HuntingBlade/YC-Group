package com.shytong.modules.wx.model;

import com.alibaba.fastjson.JSONObject;
import com.shytong.core.util.HttpSvcUtils;
import com.shytong.core.util.SyStringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;

/**
 * @author sytong
 * @Package com.shytong.modules.wx.model
 * @Description:
 * @date 2018-04-1619:14
 */
public class WxFatDo  implements Serializable {
    private static final long serialVersionUID = 1L;


    private WxMpDo wxMpDo;
    private String wxCode;
    private String accessToken;
    private String jsapiTicket;



    public WxMpUser getUserInfo(WxMpAuth2AccessToken oAuth2AccessToken){

        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/userinfo?");
        url.append("access_token=").append(oAuth2AccessToken.getAccessToken());
        url.append("&openid=").append(oAuth2AccessToken.getOpenId());

            url.append("&lang=zh_CN");
        String s= HttpSvcUtils.doGet(url.toString(),"UTF-8");

        if(!SyStringUtils.isBlank(s)){

            JSONObject j=JSONObject.parseObject(s);
            if(j.getString("errcode")!=null){


                return null;
            }

            WxMpUser wxMpUser=JSONObject.parseObject(s,WxMpUser.class);
            if(wxMpUser!=null){
                wxMpUser.setAppId(this.getWxMpDo().getAppId());
            }
            return wxMpUser;



        }
        return null;

    }


    public WxMpAuth2AccessToken oauth2getAccessToken(String code){

        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        url.append("appid=").append(this.getWxMpDo().getAppId());
        url.append("&secret=").append(this.getWxMpDo().getSecret());
        url.append("&code=").append(code);
        url.append("&grant_type=authorization_code");



           String s= HttpSvcUtils.doGet(url.toString(),"UTF-8");



           if(!SyStringUtils.isBlank(s)){

               JSONObject j=JSONObject.parseObject(s);
               if(j.getString("errcode")!=null){


                   return null;
               }


               WxMpAuth2AccessToken wxMpAuth2AccessToken=   JSONObject.parseObject(s,WxMpAuth2AccessToken.class);
                if(wxMpAuth2AccessToken!=null){
                    wxMpAuth2AccessToken.setAppId(this.getWxMpDo().getAppId());
                }
                return wxMpAuth2AccessToken;

           }
           return null;
    }

    public void auth2(String scope,String url, HttpServletResponse httpServletResponse){


        try {
            url=url+"&wxid="+this.getWxMpDo().getId();
            System.out.println("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + this.wxMpDo.getAppId()
                    + "&redirect_uri="
                    + URLEncoder.encode(url,"UTF-8")
                    + "&response_type=code&scope="
                    + scope + "&state=STATE#wechat_redirect");
            httpServletResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + this.wxMpDo.getAppId()
                    + "&redirect_uri="
                    + URLEncoder.encode(url,"UTF-8")
                    + "&response_type=code&scope="
                    + scope + "&state=STATE#wechat_redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public WxMpDo getWxMpDo() {
        return wxMpDo;
    }

    public void setWxMpDo(WxMpDo wxMpDo) {
        this.wxMpDo = wxMpDo;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }
}
