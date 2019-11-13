package com.shytong.modules.verifycode.service.impl;

import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyIdUtils;
import com.shytong.core.util.SyStringUtils;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.verifycode.dao.IVerifyCodeDao;
import com.shytong.modules.verifycode.dto.SmsMsg;
import com.shytong.modules.verifycode.model.VerifyCodeDo;
import com.shytong.modules.verifycode.service.IVerifyCodeService;
import com.shytong.sys.sms.service.ISmsService;
import com.shytong.sys.sms.service.SmsErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author shyTong
 * @Package com.shytong.modules.verifycode.service.impl
 * @Description:
 * @date 2018-01-17 16:58:08
 */
@Service("verifyCodeService")
public class VerifyCodeService implements IVerifyCodeService  {

    @Resource(name ="verifyCodeDao")
    private IVerifyCodeDao verifyCodeDao;
    @Autowired
    ISmsService iSmsService;

    @Override
    public int addVerifyCode( VerifyCodeDo verifyCodeDo, int time) throws ApiBizException {
        //插入校验
        SyValidationUtils.validateEntity(verifyCodeDo);

        verifyCodeDo.setId(SyIdUtils.uuid());
        verifyCodeDo.setGmtCreate(new Date());
        verifyCodeDo.setExpireTime(new Date(System.currentTimeMillis()+time*1000));
        //更新之前发的短信为无效
        verifyCodeDao.updateOtherCodeInValid(verifyCodeDo.getPhone(),verifyCodeDo.getType());
        return  verifyCodeDao.insert(verifyCodeDo);
    }

    @Override
    public String sendSmsCode(SmsMsg smsMsg, int time) throws ApiBizException {

        VerifyCodeDo verifyCodeDo=new VerifyCodeDo();
        verifyCodeDo.setPhone(smsMsg.getPhone());
        verifyCodeDo.setType(smsMsg.getType());
        verifyCodeDo.setAppId("0");
        verifyCodeDo.setStatus("1");
        verifyCodeDo.setCode(SyStringUtils.randNum(6));
        this.addVerifyCode(verifyCodeDo,time*60);
        SmsErrMsg s= iSmsService.sendMsg(smsMsg.getType(),smsMsg.getPhone(), SyMap.map("code",verifyCodeDo.getCode()).syPut("time",time+""));
        if(s.errCode!=0){
            System.out.println(s.errInfo);
            throw  new ApiBizException(-1,"发送失败");
        }
        return verifyCodeDo.getCode();
    }

    @Override
    public boolean checkSmsCode(String phone, String code, String type, String appID) {
      String id=   verifyCodeDao.checkIsValid(phone,code,type);



        if(id!=null){
            try {
                VerifyCodeDo vd=new VerifyCodeDo();
                vd.setId(id);
                vd.setStatus("2");
                verifyCodeDao.updateById(vd,"status") ;

            }catch (Exception e){


            }
            return true;
        }
        return false;
    }


}
