package com.shytong.modules.front.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.front.service.IFrontService;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.urlconfig.dao.IUrlConfigDao;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

/**
 * @description:
 * @author: CL
 * @time: 2019/11/11 14:34
 */
@Service
public class FrontServiceImpl implements IFrontService {

    @Autowired
    private ISysConfigDao sysConfigDao;
    @Autowired
    private IChannelDao channelDao;
    @Autowired
    private IUrlConfigDao urlConfigDao;
    @Autowired
    private IArticleDao articleDao;

    /**
     * 根据分组获取链接配置列表项
     *
     * @param urlGroup
     * @return
     */
    public List getUrlConfigListByGroup(String urlGroup) {
        return urlConfigDao.getUrlConfigListByGroup(urlGroup);
    }

    /**
     * 栏目是否存在
     *
     * @param channelId
     * @return
     */
    public Integer channelIsExist(Integer channelId) {
        if (channelId == null) {
            return 1;
        }
        return channelId;
    }

    /**
     * 获取首页文章
     *
     * @param channelId
     * @return
     */
    public Object getIndexArticle(Integer channelId) {
        SyMap params = new SyMap();
        params.put("channelId", channelId);
        PageInfo articleByChannelId = articleDao.getArticleByChannelId(params, 1, 1);
        List articleList = articleByChannelId.getList();
        Object obj = null;
        for (int i = 0; i < 1; i++) {
            obj = articleList.get(i);
        }
        return obj;
    }

    /**
     * 递归查找子栏目编号及文章信息
     *
     * @param pId
     * @return
     */
    public List getSonChannelList(Integer pId) {
        List<Map> channelListByParentChannelId = channelDao.getChannelListByParentChannelId(pId);
        channelListByParentChannelId.forEach(channelMap -> {
            Integer sonId = MapUtils.getInteger(channelMap, "id", null);
            List sonChannelList = this.getSonChannelList(sonId);
            if (sonChannelList != null && sonChannelList.size() > 0) {
                channelMap.put("children", sonChannelList);
            }
        });
        return channelListByParentChannelId;
    }

    /**
     * 设置顶部图片和标题
     *
     * @param model
     * @param channelId
     */
    public void setTopImgAndTitle(ModelMap model, Integer channelId) {
        // 如果有父栏目 就用父栏目的id
        Integer parentId = channelDao.getParentChannelId(channelId);
        if (parentId == null || parentId == 0) {
            parentId = channelId;
        }
        // 栏目顶部图片
        model.addAttribute("channelTopImg", sysConfigDao.getSysConfig(parentId.toString(), "yc"));
        // 栏目标题
        model.addAttribute("channelTitle", channelDao.getChannelById(parentId));
    }

    @Override
    public void setHtml(ModelMap model) {
        // logo图片
        model.addAttribute("logoImg", sysConfigDao.getSysConfig("logo", "yc"));
        // 底部logo
        model.addAttribute("footLogoImg", sysConfigDao.getSysConfig("footLogo", "yc"));
        // 栏目
        model.addAttribute("channelList", channelDao.getSonChannelListById(0));
        // 底部下拉框一
        model.addAttribute("footInputOne", this.getUrlConfigListByGroup("1"));
        // 底部下拉框二
        model.addAttribute("footInputTwo", this.getUrlConfigListByGroup("2"));
        // 底部下拉框三
        model.addAttribute("footInputThree", this.getUrlConfigListByGroup("3"));
        // 版权
        model.addAttribute("copyright", sysConfigDao.getSysConfig("copyright", "yc"));
        // ICP
        model.addAttribute("footICP", sysConfigDao.getSysConfig("footICP", "yc"));
        // 公司
        model.addAttribute("company", sysConfigDao.getSysConfig("company", "yc"));
    }

    @Override
    public void setIndexContent(ModelMap model, Integer channelId) {
        channelId = this.channelIsExist(channelId);
        // 轮播
        model.addAttribute("carouselList", sysConfigDao.getSysConfigList("1", "yc"));
        // 首页文章
        model.addAttribute("articleList", this.getIndexArticle(channelId));
        // 签字
        model.addAttribute("signature", sysConfigDao.getSysConfig("signature", "yc"));
        // 模块
        model.addAttribute("indexContentModel", this.getSonChannelList(channelId));
        // 首页推荐文章配图
        model.addAttribute("recommendArticleImg", sysConfigDao.getSysConfig("newsRecommendImg", "yc"));
        // 首页推荐文章
        model.addAttribute("recommendArticle", articleDao.getRecommendArticle(17));
        // 首页新闻中心更多按钮
        model.addAttribute("newsMoreBtn", sysConfigDao.getSysConfig("newsMoreBtn", "yc"));
        // 首页新闻文章
        model.addAttribute("newsArticleList", this.getSonChannelList(5));
    }

    @Override
    public Map getSonChannelInfo(Integer channelId, Integer pageNum, Integer pageSize) {
        SyMap params = new SyMap();
        params.put("channelId", channelId);
        PageInfo articleByChannelId = articleDao.getArticleByChannelId(params, pageNum, pageSize);
        long total = articleByChannelId.getTotal();
        List list = articleByChannelId.getList();
        Map map = new HashMap();
        map.put("list", list);
        map.put("total", total);
        return map;
    }


    @Override
    public void setAboutContent(ModelMap model, Integer channelId) {
        this.setTopImgAndTitle(model, channelId);
        SyMap params = new SyMap();
        params.put("channelId", channelId);
        model.addAttribute("content", articleDao.getArticleByChannelId(params, 1, 1).getList());
    }

    @Override
    public void setQualificationContent(ModelMap model, Integer channelId, Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        SyMap params = new SyMap();
        params.put("channelId", channelId);
        // 栏目头部图片和标题
        this.setTopImgAndTitle(model, channelId);
        // 栏目文章
        model.addAttribute("qualificationArticleList", articleDao.getArticleByChannelId(params, 1, 1).getList());
        // 获取栏目下的子栏目
        model.addAttribute("navList", channelDao.getChannelListByPId(channelId));
    }

    @Override
    public void setProjectCaseContent(ModelMap model, Integer channelId, Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        SyMap params = new SyMap();
        params.put("channelId", channelId);
        // 栏目头部图片和标题
        this.setTopImgAndTitle(model, channelId);
        // 栏目文章
        model.addAttribute("projectArticleList", articleDao.getArticleByChannelId(params, 1, 1).getList());
        // 获取栏目下的子栏目
        model.addAttribute("navList", channelDao.getChannelListByPId(channelId));
    }

    @Override
    public void setNewsCenterCaseContent(ModelMap model, Integer channelId, Integer pageNum) {
        if (channelId == null || pageNum == null) {
            throw new RuntimeException();
        }
        // 栏目头部图片和标题
        this.setTopImgAndTitle(model, channelId);
        // 获取栏目下的子栏目
        List<ChannelDo> newsChannel = channelDao.getSonChannelListByGroup("news");
        model.addAttribute("navList", newsChannel);
        // 查找是否存在子栏目
        SyMap params = new SyMap();
        List<ChannelDo> sonChannelListById = channelDao.getSonChannelListById(channelId);
        if (sonChannelListById != null && sonChannelListById.size() > 0) {
            params.put("channelId", channelId);
            model.addAttribute("navList", newsChannel);
            Integer maxValue = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < newsChannel.size(); i++) {
                Integer sort = newsChannel.get(i).getSort();
                if (sort < maxValue) {
                    maxValue = sort;
                    index = i;
                }
            }
            params.put("channelId", newsChannel.get(index).getId());
            model.addAttribute("newsContentList", articleDao.getArticleByChannelId(params, pageNum, 6).getList());
        } else {
            params.put("channelId", channelId);
            model.addAttribute("newsContentList", articleDao.getArticleByChannelId(params, pageNum, 6).getList());
        }
    }
}
