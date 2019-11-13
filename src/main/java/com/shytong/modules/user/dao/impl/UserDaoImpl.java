

package com.shytong.modules.user.dao.impl;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.common.model.SyMap;
import com.shytong.modules.user.dao.IUserDao;
import com.shytong.modules.user.model.UserDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.user.dao.impl
 * @Description:
 * @date 2018-04-16 16:44:21
 */
@Repository("userDao")
public class UserDaoImpl extends CommDao<UserDo>  implements IUserDao {
    public UserDaoImpl() {
        super("user.insert"
                , "user.updateSelective"
                ,"user.selective"
                ,"user.select"
                ,"user_ext.selectList"
                ,"user.delete");
        super.setCommDo(new UserDo());
    }

    @Override
    public UserDo findByIdentifier(String type, String sysIdentifier, String identifier) {
        UserDo userDo=null;

        if("0".equals(type)){


            userDo=findByXml("user_ext.findByAccount", SyMap.map("account",identifier));

        }else{
            userDo=findByXml("user_ext.findByIdentifier", SyMap.map("type",type)
                    .syPut("sysIdentifier",sysIdentifier)
                    .syPut("identifier",identifier));
        }


        return userDo;
    }

    @Override
    public UserDo findByOpenId(String openId) {
        return  findByXml("user_ext.findByAccount", SyMap.map("openId",openId));
    }

    @Override
    public UserDo findByUnionId(String unionId) {
        return   findByXml("user_ext.findByAccount", SyMap.map("unionId",unionId));
    }
}
