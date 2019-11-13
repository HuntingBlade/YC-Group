package com.shytong.sys.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.io.IOException;
import java.util.Map;

/**
 * @author sytong
 * @Package com.shytong.sys.sms.service
 * @Description:
 * @date 2018-01-1711:37
 */


public class AliSmsSender extends SmsSender {

    private AliConfig aliConfig;
    private  TaobaoClient client;
    private ObjectMapper objectMapper=new ObjectMapper();
    public AliSmsSender( AliConfig aliConfig){

        this.aliConfig=aliConfig;
        client= new DefaultTaobaoClient(aliConfig.getUrl(), aliConfig.getAppkey(), aliConfig.getSecret());
    }
    @Override
    public SmsErrMsg sendMsg(String sendAppId,String type, String phone,Object param) {
        SmsErrMsg s=new SmsErrMsg();
        if(aliConfig.getCodes()==null||aliConfig.getCodes().get(type)==null){
            s.errCode=-1;
            s.errInfo="类型未找到";
            return s;
        }
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(sendAppId);
        req.setSmsFreeSignName(aliConfig.getFreeSignName());
        req.setRecNum(phone);
        try {
            req.setSmsParam(objectMapper.writeValueAsString(param));
        } catch (JsonProcessingException e) {
            s.errCode=98;
            s.errInfo="参数异常";
            e.printStackTrace();
        }
        req.setSmsTemplateCode(aliConfig.getCodes().get(type));
        req.setSmsType("normal");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
          String rr=  rsp.getBody();
          if(StringUtils.isEmpty(rr)){
                s.errCode=99;
          }
            Map  nn=  objectMapper.readValue(rr,Map.class);
          if(nn.get("alibaba_aliqin_fc_sms_num_send_response")==null){
              s.errCode=96;
              Map  nn1=  objectMapper.readValue(nn.get("error_response")+"",Map.class);
              String sub_code=nn1.get("sub_code").toString();
              s.errInfo=nn1.get("sub_msg").toString();
          };
//          objectMapper.readValue(rr)

        } catch (ApiException e) {
            s.errCode=99;
            s.errInfo=e.getErrMsg()+":"+e.getErrMsg();
            e.printStackTrace();
        } catch (Exception e) {
            s.errCode=97;
            s.errInfo="错误";
            e.printStackTrace();
        }




        return s;
    }

    public static void main(String[] args) {
       ObjectMapper objectMapper=new ObjectMapper();



       try {
           Map  nn=  objectMapper.readValue(" {\n" +
                      "            \"error_response\":{\n" +
                      "            \"sub_msg\":\"非法参数\",\n" +
                      "                    \"code\":50,\n" +
                      "                    \"sub_code\":\"isv.invalid-parameter\",\n" +
                      "                    \"msg\":\"Remote service error\"\n" +
                      "        }\n" +
                      "        }", Map.class);

           System.out.println(nn.get("error_response"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
