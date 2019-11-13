

package com.shytong.modules.wx.dao.impl;
import org.springframework.stereotype.Service;
import com.shytong.common.dao.impl.CommDao;
import com.shytong.modules.wx.dao.IWxMpDao;
import com.shytong.modules.wx.model.WxMpDo;
import org.springframework.stereotype.Repository;

/**
 * @author 
 * @Package com.shytong.modules.wx.dao.impl
 * @Description:
 * @date 2018-04-16 19:04:41
 */
@Repository("wxMpDao")
public class WxMpDaoImpl extends CommDao<WxMpDo>  implements IWxMpDao {
    public WxMpDaoImpl() {
        super("wxMp.insert"
                , "wxMp.updateSelective"
                ,"wxMp.selective"
                ,"wxMp.select"
                ,"wxMp_ext.selectList"
                ,"wxMp.delete");
        super.setCommDo(new WxMpDo());
    }
}
