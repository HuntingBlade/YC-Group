package com.shytong.modules.channel.dao;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.channel.model.ChannelDo;

import java.util.List;
import java.util.Map;

/**
 * @Author: CL
 * @Date: 2019/11/12 17:46
 */
public interface IChannelDao {
    /**
     * 添加栏目项
     *
     * @param channelDo
     * @return
     */
    Integer insertChannel(ChannelDo channelDo);

    /**
     * 更新栏目项
     *
     * @param channelDo
     * @return
     */
    Integer updateChannel(ChannelDo channelDo);

    /**
     * 获取栏目列表
     *
     * @param pageNum
     * @param pageSize
     * @param params
     * @return
     */
    PageInfo<ChannelDo> selectList(Integer pageNum, Integer pageSize, SyMap params);

    /**
     * 获取栏目id下的子栏目
     * @param parentId
     * @return
     */
    List getChannelListByPId(Integer parentId);

    /**
     * 获取栏目id下的子栏目及图片等信息
     * @param parentId
     * @return
     */
    List<Map> getChannelListByParentChannelId(Integer parentId);

    /**
     * 根据id查找栏目
     * @param channelId
     * @return
     */
    ChannelDo getChannelById(Integer channelId);
}
