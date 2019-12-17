package com.shytong.modules.article.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.article.model.ArticleDo;
import com.shytong.modules.article.service.IArticleService;
import com.shytong.modules.sysconfig.dao.ISysConfigDao;
import com.shytong.modules.sysconfig.model.SysConfigDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: CL
 * @Date: 2019/11/12 19:40
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;
    @Autowired
    private ISysConfigDao sysConfigDao;

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer insertArticle(ArticleDo articleDo) throws ApiBizException {
        if (articleDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        articleDo.setId(SyIdUtils.uuid());
        Integer result = articleDao.insertArticle(articleDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer deletedArticle(List list) throws ApiBizException {
        if (list.size() <= 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = articleDao.deletedArticle(list);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Integer updateArticle(ArticleDo articleDo) throws ApiBizException {
        if (articleDo == null) {
            throw new ApiBizException(-1, "参数错误");
        }
        Integer result = articleDao.updateArticle(articleDo);
        if (result < 0) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public PageInfo<ArticleDo> selectArticleList(SyMap params, Integer pageNum, Integer pageSize) throws ApiBizException {
        if (pageNum == null) {
            pageNum = 1;
        }
        SysConfigDo sysConfigDo = (SysConfigDo) sysConfigDao.getSysConfig("articlePageSize", "yc");
        if (sysConfigDo == null) {
            pageSize = 10;
        } else {
            pageSize = Integer.parseInt(sysConfigDo.getSysValue());
        }
        PageInfo<ArticleDo> list = articleDao.selectArticleList(pageNum, pageSize, params);
        return list;
    }
}
