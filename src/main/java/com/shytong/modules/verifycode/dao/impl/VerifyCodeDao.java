package com.shytong.modules.verifycode.dao.impl;

import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.modules.verifycode.dao.IVerifyCodeDao;
import com.shytong.modules.verifycode.model.VerifyCodeDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shyTong
 * @Package com.shytong.modules.verifycode.Dao.impl
 * @Description:
 * @date 2018-01-17 15:04:25
 */
@Repository("verifyCodeDao")
public class VerifyCodeDao extends CommDao<VerifyCodeDo>  implements IVerifyCodeDao {
    public VerifyCodeDao() {
        super("verifyCode.insert"
                , "verifyCode.updateSelective"
                ,"verifyCode.selective"
                ,"verifyCode.select"
                ,"verifyCode.selectList"
                ,"verifyCode.delete");
    }
    @Override
    public String checkIsValid(String phone, String code, String type) {

        List<VerifyCodeDo> l=this.commlist("verifyCode_ext.checkIsValid", SyMap.map("phone",phone)
        .syPut("code",code)
        .syPut("type",type));
        if(l==null||l.size()==0){
            return  null;
        }
        return l.get(0).getId();


    }
    @Override
    public void updateOtherCodeInValid(String phone, String type) {
        this.commUpdate("verifyCode_ext.updateOtherCodeInValid",SyMap.map("phone",phone)
                .syPut("type",type));


    }
}
