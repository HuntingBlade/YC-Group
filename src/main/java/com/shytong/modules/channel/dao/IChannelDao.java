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
     * 根据栏目编号获取子栏目
     * @param parentId
     * @return
     */
    List<ChannelDo> getSonChannelListById(Integer parentId);

    /**
     * 获取栏目id下的子栏目
     *
     * @param parentId
     * @return
     */
    List<Map> getChannelListByPId(Integer parentId);

    /**
     * 获取子栏目对应的父栏目
     *
     * @param sonId
     * @return
     */
    List<Map> getChannelListBySonChannelId(Integer sonId);

    /**
     * 获取栏目id下的子栏目及图片等信息
     *
     * @param parentId
     * @return
     */
    List<Map> getChannelListByParentChannelId(Integer parentId);

    /**
     * 根据id查找栏目
     *
     * @param channelId
     * @return
     */
    ChannelDo getChannelById(Integer channelId);

    /**
     * 根据分组获取栏目列表
     * @param group
     * @return
     */
    List<ChannelDo> getSonChannelListByGroup(String group);

    /**
     * 根据子栏目id找父栏目id
     * @param channelId
     * @return
     */
    Integer getParentChannelId(Integer channelId);

    /**
     * 栏目是否存在
     * @param channelId
     * @return
     */
    Integer channelIsExist(Integer channelId);

    /**
     * 根据id获取id所处一致的分组
     * @param channelId
     * @return
     */
    List<ChannelDo> getChannelGroupById(Integer channelId);

    /**
     * 根据父栏目id获取栏目列表和系统配置
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getChannelListAndSysConfigByPId(SyMap params, Integer pageNum, Integer pageSize);

    /**
     * 根据栏目id获取此栏目的信息
     * @param id
     * @return
     */
    Object getChannelAndSysConfigById(Integer id);
}
