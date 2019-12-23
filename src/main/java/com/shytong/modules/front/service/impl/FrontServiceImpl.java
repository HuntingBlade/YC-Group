package com.shytong.modules.front.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.model.SyMap;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.article.model.ArticleDo;
import com.shytong.modules.channel.comm.TemplateType;
import com.shytong.modules.channel.dao.IChannelDao;
import com.shytong.modules.channel.model.ChannelDo;
import com.shytong.modules.front.service.IFrontService;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import com.shytong.modules.urlconfig.dao.IUrlConfigDao;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

import static com.shytong.modules.channel.comm.TemplateType.*;

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
     * 设置头部和底部
     *
     * @param model
     */
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
    public void setIndexContent(ModelMap model) {
        // 轮播
        SyMap params = new SyMap();
        params.put("sysKey", 1);
        params.put("sysGroup", "yc");
        model.addAttribute("carouselList", sysConfigDao.getSysConfigList(params, 1, 10).getList());
        // 首页文章
        model.addAttribute("articleList", this.getIndexArticle(1));
        // 签字
        model.addAttribute("signature", sysConfigDao.getSysConfig("signature", "yc"));
        // 模块
        model.addAttribute("indexContentModel", this.getSonChannelList(1));
        // 首页推荐文章配图
        model.addAttribute("recommendArticleImg", sysConfigDao.getSysConfig("newsRecommendImg", "yc"));
        // 首页新闻中心更多按钮
        model.addAttribute("newsMoreBtn", sysConfigDao.getSysConfig("newsMoreBtn", "yc"));
        // 首页推荐文章
        model.addAttribute("recommendArticle", articleDao.getRecommendArticle(17));
        // 首页新闻文章
        model.addAttribute("newsArticleList", this.getSonChannelList(5));
    }

    @Override
    public void setContent(ModelMap model, Integer channelId, Integer pageNum, Integer pageSize, String group) {
        SyMap params = new SyMap();
        Map map = new HashMap(16);
        PageInfo pageList;

        if (pageNum == null) {
            pageNum = 1;
        }

        // 判断分组是否存在
        List<ChannelDo> sonChannelList = channelDao.getSonChannelListByGroup(group);
        if (sonChannelList == null || sonChannelList.size() <= 0) {
            params.put("channelId", channelId);
        } else {
            if (channelId != null) {
                // 该栏目编号是否存在
                Integer state = channelDao.channelIsExist(channelId);
                if (state <= 0) {
                    throw new RuntimeException("栏目不存在");
                }
                params.put("channelId", channelId);
                model.addAttribute("template", channelDao.getChannelById(channelId).getListTemplate());
            } else {
                // 找出栏目中排序最大的一个 (越小越大)
                Integer maxValue = Integer.MAX_VALUE;
                int index = -1;
                for (int i = 0; i < sonChannelList.size(); i++) {
                    Integer sort = sonChannelList.get(i).getSort();
                    if (sort < maxValue) {
                        maxValue = sort;
                        index = i;
                    }
                    if (-1 == index) {
                        throw new RuntimeException();
                    }
                    channelId = sonChannelList.get(index).getId();
                    model.addAttribute("template", sonChannelList.get(index).getListTemplate());
                    params.put("channelId", channelId);
                }
            }
            model.addAttribute("navList", sonChannelList);
        }
        pageList = articleDao.getArticleByChannelId(params, pageNum, pageSize);
        map.put("total", pageList.getTotal());
        map.put("list", pageList.getList());
        this.setTopImgAndTitle(model, channelId);
        model.addAttribute("content", map);
        model.addAttribute("activeNavName", channelDao.getChannelById(channelId).getName());

        // 查找父栏目是否还有其他模块
        int count = 0;
        while (true) {
            Integer parentChannelId = channelDao.getParentChannelId(channelId);
            if (parentChannelId == 0) {
                params.put("channelId", channelId);
                List articleContent = articleDao.getArticleByChannelId(params, 1, 10).getList();
                if (articleContent.size() > 0) {
                    model.addAttribute("articleContent", articleContent);
                }
                break;
            }
            channelId = parentChannelId;
            // 循环限制  避免死循环
            count++;
            if (count > 10) {
                break;
            }
        }
    }

    @Override
    public void getArticleDetail(ModelMap model, Integer channelId, String articleId) {
        this.setTopImgAndTitle(model, channelId);
        Integer parentChannelId = channelDao.getParentChannelId(channelId);
        if (parentChannelId != null) {
            // 父栏目的id 用于判断当前选中的栏目
            model.addAttribute("parentChannelId", parentChannelId);
            model.addAttribute("childrenChannelId", channelId);
        }
        // 获取此栏目的模板类型
        TemplateType template = valueOf(channelDao.getChannelById(channelId).getListTemplate());
        switch (template) {
            case IMG_TYPE_ONE:
                // 图片一类型模板
                model.addAttribute("templateType", IMG_TYPE_ONE.toString());
                break;
            case IMG_TYPE_TWO:
                // 图片二类型模板(有顶部图片和栏目标题、子栏目)
                model.addAttribute("templateType", IMG_TYPE_TWO.toString());
                break;
            case TITLE_TYPE:
                this.setTopImgAndTitle(model, channelId);
                model.addAttribute("navList", channelDao.getChannelGroupById(channelId));
                model.addAttribute("templateType", TITLE_TYPE.toString());
                break;
            case RICH_TYPE:
            default:
                break;
        }
        // 文章子栏目
        model.addAttribute("navList", channelDao.getSonChannelListById(parentChannelId));
        // 文章详细内容
        model.addAttribute("detailContent", articleDao.getArticleInfoById(articleId));
    }

    @Override
    public void getSysConfig(ModelMap model, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("carouselPageSize", "yc");
        if (sysConfigDo == null) {
            pageSize = 10;
        } else {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        SyMap params = new SyMap();
        params.put("sysKey", 1);
        params.put("sysGroup", "yc");
        PageInfo carouselList = sysConfigDao.getSysConfigAllList(params, pageNum, pageSize);
        model.addAttribute("carouselPageSize", pageSize);
        model.addAttribute("carouselPageNum", carouselList.getPageNum());
        model.addAttribute("carouselTotal", carouselList.getTotal());
        model.addAttribute("carouselList", carouselList.getList());
    }

    @Override
    public void getFirstChannel(ModelMap model, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("firstClassPageSize", "yc");
        if (sysConfigDo == null) {
            pageSize = 10;
        } else {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        SyMap params = new SyMap();
        params.put("parentId", 0);
        PageInfo sonChannelList = channelDao.getChannelListAndSysConfigByPId(params, pageNum, pageSize);
        model.addAttribute("firstClassPageNum", sonChannelList.getPageNum());
        model.addAttribute("firstClassPageSize", pageSize);
        model.addAttribute("firstClassTotal", sonChannelList.getTotal());
        model.addAttribute("firstClassList", sonChannelList.getList());
    }

    @Override
    public void getSysConfigById(ModelMap model, String id) {
        SyMap params = new SyMap();
        params.put("id", id);
        model.addAttribute("carouselList", sysConfigDao.getList(params));
    }

    @Override
    public void getChannelAndSysConfigById(ModelMap model, Integer id) {
        model.addAttribute("firstClassObj", channelDao.getChannelAndSysConfigById(id));
    }

    @Override
    public void getSecondClass(ModelMap model, SyMap params) {
        int pageNum = 1;
        int pageSize = 10;
        int parentId = 0;
        String type = params.getString("type");
        if (params.get("pageNum") != null) {
            pageNum = params.getInteger("pageNum");
        }
        // 查询每页显示大小判断
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("secondClassPageSize", "yc");
        if (sysConfigDo != null) {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        if (params.get("parentId") != null) {
            parentId = Integer.parseInt(params.get("parentId").toString());
        }
        // 设置一二级栏目下拉框
        setFirstAndSecondChannel(model, parentId, type);
        // 判断类型 1-一级栏目  2-二级栏目  null-全部
        PageInfo channelList = null;
        if (type == null) {
            params.put("group", "index");
            channelList = channelDao.getNotIncludeGroupChannelByGroup(params, pageNum, pageSize);
        } else if ("firstChannel".equals(type)) {
            channelList = channelDao.getSonChannelListByParentId(params, pageNum, pageSize);
        } else if ("secondChannel".equals(type)) {
            channelList = channelDao.getParentChannelListBySonId(params, pageNum, pageSize);
        }
        model.addAttribute("channelList", channelList.getList());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", channelList.getPageNum());
        model.addAttribute("total", channelList.getTotal());
    }

    @Override
    public void getAddSecondClassInfo(ModelMap model) {
        // 一级栏目
        List<ChannelDo> channelList = channelDao.getSonChannelListNotIncludeIndexById(0);
        model.addAttribute("channelList", channelList);
    }

    @Override
    public void editSecondClass(ModelMap model, Integer id) {
        // 一级栏目
        List<ChannelDo> channelList = channelDao.getSonChannelListNotIncludeIndexById(0);
        ChannelDo channelDo = channelDao.getChannelById(id);
        if (channelDo == null) {
            throw new RuntimeException();
        }
        model.addAttribute("channelList", channelList);
        model.addAttribute("channelDo", channelDo);
        model.addAttribute("activeFirst", channelDo.getParentId());
        model.addAttribute("activeType", channelDo.getListTemplate());
    }

    @Override
    public void setOtherChannel(ModelMap model, Integer pageNum, Integer pageSize) {
        SyMap params = new SyMap();
        params.put("parentId", 1);
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("otherClassPageSize", "yc");
        if (pageNum == null) {
            pageNum = 1;
        }
        if (sysConfigDo == null) {
            pageSize = 10;
        } else {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        PageInfo sonChannelList = channelDao.getChannelInfoAndConfigAndParentNameByPId(params, pageNum, pageSize);
        List<Map> sonList = sonChannelList.getList();
        List channelList = new ArrayList();
        int index = 0;
        for (int i = 0; i < sonList.size(); i++) {
            Integer id = (Integer) sonList.get(i).get("id");
            params.put("parentId", id);
            PageInfo sonChannel = channelDao.getChannelInfoAndConfigAndParentNameByPId(params, pageNum, pageSize);
            List temp = sonChannel.getList();
            for (int j = 0; j < temp.size(); j++) {
                channelList.add(index++, temp.get(j));
            }
        }
        model.addAttribute("channelList", channelList);
    }

    @Override
    public void setEditOtherChannel(ModelMap model, Integer id) {
        model.addAttribute("channelDo", channelDao.getChannelAndSysConfigById(id));
    }

    @Override
    public void setAdminIndex(ModelMap model) {
        model.addAttribute("modulesList", channelDao.getSonChannelListNotIncludeIndexById(0));
    }

    @Override
    public void setModules(ModelMap model, SyMap params, Integer pageNum, Integer pageSize) {
        ArrayList list = new ArrayList<>();
        // 查询数据库文章显示大小
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("articlePageSize", "yc");
        if (pageNum == null) {
            pageNum = 1;
        }
        if (sysConfigDo == null) {
            pageSize = 10;
        } else {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        String channelId = params.getString("channelId");
        // 二级栏目列表
        List<ChannelDo> channelList = null;
        // 文章
        PageInfo articleList = null;
        // 一级栏目
        String firstChannelId = null;
        String navName = null;
        // 判断是一级栏目还是二级
        ChannelDo channelDo = channelDao.getChannelById(Integer.parseInt(channelId));
        if (channelDo.getParentId() == 0) {
            channelList = channelDao.getSonChannelListById(Integer.parseInt(channelId));
            navName = channelDo.getName();
            firstChannelId = channelId;
            // 子栏目
            List<ChannelDo> sonChannelDoList = channelDao.getSonChannelListById(Integer.parseInt(channelId));
            if (sonChannelDoList.size() > 0) {
                // 有子栏目
                for (int i = 0; i < sonChannelDoList.size(); i++) {
                    Integer chaId = sonChannelDoList.get(i).getId();
                    list.add(chaId);
                }
            } else {
                list.add(Integer.parseInt(channelId));
            }
        } else {
            firstChannelId = channelDo.getParentId().toString();
            navName = channelDao.getChannelById(Integer.parseInt(firstChannelId)).getName();
            channelList = channelDao.getSonChannelListById(Integer.parseInt(firstChannelId));
            list.add(Integer.parseInt(channelId));
        }
        params.put("array", list);
        articleList = articleDao.getArticleAndChannelInfoByChannelId(params, pageNum, pageSize);
        // 导航栏
        model.addAttribute("navName", navName);
        // 二级栏目下拉框
        model.addAttribute("channelList", channelList);
        // 所属模块的文章列表
        model.addAttribute("articleList", articleList.getList());
        // 父栏目Id
        model.addAttribute("firstChannelId", firstChannelId);
        // 父栏目Id
        model.addAttribute("activeChannelId", channelId);
        // 每页显示大小
        model.addAttribute("pageSize", pageSize);
        // 页码
        model.addAttribute("pageNum", pageNum);
        // 总数
        model.addAttribute("total", articleList.getTotal());

    }

    @Override
    public void setAddArticleDefaultData(ModelMap model, String firstChannelId) {
        List<ChannelDo> belongChannels = new ArrayList<>();
        int index = 0;
        // 查找二级栏目
        List<ChannelDo> secondChannelList = channelDao.getSonChannelListById(Integer.parseInt(firstChannelId));
        if (secondChannelList.size() <= 0) {
            ChannelDo firstChannelDo = channelDao.getChannelById(Integer.parseInt(firstChannelId));
            belongChannels.add(index++, firstChannelDo);
        } else {
            for (int i = 0; i < secondChannelList.size(); i++) {
                belongChannels.add(index++, secondChannelList.get(i));
            }
        }
        model.addAttribute("belongChannels", belongChannels);
        model.addAttribute("firstChannelId", firstChannelId);
    }

    @Override
    public void setAdminModulesEdit(ModelMap model, String firstChannelId, String articleId) {
        Object articleDo = articleDao.getArticleInfoById(articleId);
        List<ChannelDo> belongChannels = new ArrayList<>();
        int index = 0;
        // 查找二级栏目
        List<ChannelDo> secondChannelList = channelDao.getSonChannelListById(Integer.parseInt(firstChannelId));
        if (secondChannelList.size() <= 0) {
            ChannelDo firstChannelDo = channelDao.getChannelById(Integer.parseInt(firstChannelId));
            belongChannels.add(index++, firstChannelDo);
        } else {
            for (int i = 0; i < secondChannelList.size(); i++) {
                belongChannels.add(index++, secondChannelList.get(i));
            }
        }
        model.addAttribute("belongChannels", belongChannels);
        model.addAttribute("articleDo", articleDo);
    }

    // -----------------------------------------------------------------------------------------

    /**
     * 设置顶部图片和标题
     *
     * @param model
     * @param channelId
     */
    public void setTopImgAndTitle(ModelMap model, Integer channelId) {
        // 查找父栏目id
        Integer parentId = channelDao.getParentChannelId(channelId);
        if (parentId == null || parentId == 0) {
            parentId = channelId;
        }
        // 父栏目栏目顶部图片
        model.addAttribute("channelTopImg", sysConfigDao.getSysConfig(parentId.toString(), "yc"));
        // 父栏目栏目标题
        model.addAttribute("channelTitle", channelDao.getChannelById(parentId));
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
     * 根据分组获取链接配置列表项
     *
     * @param urlGroup
     * @return
     */
    public List getUrlConfigListByGroup(String urlGroup) {
        return urlConfigDao.getUrlConfigListByGroup(urlGroup);
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
     * 设置一二级栏目下拉框
     *
     * @param model
     * @param parentId
     */
    public void setFirstAndSecondChannel(ModelMap model, Integer parentId, String type) {
        Integer activeFirst = 0;
        Integer activeSecond = 0;
        List<ChannelDo> firstChannelList = channelDao.getSonChannelListNotIncludeIndexById(0);
        List<ChannelDo> secondChannelList = new ArrayList<>();
        if (type == null) {
            secondChannelList = this.IterationSonChannel(firstChannelList, secondChannelList);
        } else if ("firstChannel".equals(type)) {
            activeFirst = parentId;
            secondChannelList = channelDao.getSonChannelListNotIncludeIndexById(parentId);
        } else if ("secondChannel".equals(type)) {
            ChannelDo channelDo = channelDao.getChannelById(parentId);
            activeFirst = channelDo.getParentId();
            activeSecond = parentId;
            secondChannelList = Arrays.asList(channelDao.getChannelById(parentId));
        }
        model.addAttribute("activeFirst", activeFirst);
        model.addAttribute("activeSecond", activeSecond);
        model.addAttribute("firstChannelList", firstChannelList);
        model.addAttribute("secondChannelList", secondChannelList);
    }

    public List<ChannelDo> IterationSonChannel(List<ChannelDo> firstChannelList, List<ChannelDo> secondChannelList) {
        int index = 0;
        for (int i = 0; i < firstChannelList.size(); i++) {
            int id = firstChannelList.get(i).getId();
            List<ChannelDo> sonChannelList = channelDao.getSonChannelListById(id);
            if (sonChannelList.size() <= 0) {
                continue;
            }
            for (int j = 0; j < sonChannelList.size(); j++) {
                secondChannelList.add(index++, sonChannelList.get(j));
            }
        }
        return secondChannelList;
    }
}
