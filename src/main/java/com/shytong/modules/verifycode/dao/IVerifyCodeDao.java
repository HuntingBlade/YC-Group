
package com.shytong.modules.verifycode.dao;
import com.shytong.common.dao.ICommDao;
import com.shytong.modules.verifycode.model.VerifyCodeDo;

/**
 * @author shyTong
 * @Package com.shytong.modules.verifycode.Dao
 * @Description:
 * @date 2018-01-17 15:04:25
 */
public interface IVerifyCodeDao extends ICommDao<VerifyCodeDo> {
    public String  checkIsValid(String phone, String code, String type);
    public void updateOtherCodeInValid(String phone, String type);
}
