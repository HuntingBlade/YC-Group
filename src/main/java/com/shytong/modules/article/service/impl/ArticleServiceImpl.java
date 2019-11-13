package com.shytong.modules.article.service.impl;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.core.util.SyIdUtils;
import com.shytong.modules.article.dao.IArticleDao;
import com.shytong.modules.article.model.ArticleDo;
import com.shytong.modules.article.service.IArticleService;
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
    public PageInfo<ArticleDo> selectArticleList(SyMap params) throws ApiBizException {
        Integer pageNum = params.getInteger("pageNum");
        Integer pageSize = params.getInteger("pageSize");
        if (pageNum <= 0 || pageSize <= 0) {
            throw new ApiBizException(-1, "参数错误");
        }
        PageInfo<ArticleDo> list = articleDao.selectArticleList(pageNum, pageSize, params);
        return list;
    }
}
