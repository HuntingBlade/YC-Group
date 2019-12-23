package com.shytong.modules.article.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.article.model.ArticleDo;
import com.shytong.modules.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文章控制类
 * @Author: CL
 * @Date: 2019/11/12 20:29
 */
@RestController
@RequestMapping(value = "/api/article", produces = "application/json;charset=utf-8", consumes = "application/json")
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    /**
     * 添加文章
     *
     * @param servletRequest
     * @param articleDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addArticle(HttpServletRequest servletRequest, @RequestBody ArticleDo articleDo) throws ApiBizException {
        String res = articleService.insertArticle(articleDo);
        return this.normalResp(res);
    }

    /**
     * 删除文章(批量删除)
     *
     * @param servletRequest
     * @param ids
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String deletedArticle(HttpServletRequest servletRequest, @RequestBody String[] ids) throws ApiBizException {
        System.out.println(ids);
        String res = articleService.deletedArticle(ids);
        return this.normalResp(res);
    }

    /**
     * 更新文章
     *
     * @param servletRequest
     * @param articleDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateArticle(HttpServletRequest servletRequest, @RequestBody ArticleDo articleDo) throws ApiBizException {
        String res = articleService.updateArticle(articleDo);
        return this.normalResp(res);
    }

    /**
     * 获取文章列表
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String selectArticle(HttpServletRequest servletRequest, @RequestBody Map map, Integer pageNum, Integer pageSize) throws ApiBizException {
        SyMap params = new SyMap(map);
        PageInfo res = articleService.selectArticleList(params, pageNum, pageSize);
        return this.normalRespPage(res);
    }

}
