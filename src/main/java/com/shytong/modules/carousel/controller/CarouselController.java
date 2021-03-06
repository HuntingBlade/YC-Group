package com.shytong.modules.carousel.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.resultcode.ResultCodeInfo;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.carousel.model.CarouselDo;
import com.shytong.modules.carousel.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description: 轮播图前端控制类
 * @Author: CL
 * @Date: 2019/11/12 20:13
 */
@RestController
@RequestMapping(value = "/api/carousel", produces = "application/json;charset=utf-8", consumes = "application/json")
public class CarouselController extends BaseController {

    @Autowired
    private ICarouselService carouselService;

    /**
     * 添加轮播项
     *
     * @param servletRequest
     * @param carouselDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCarousel(HttpServletRequest servletRequest, @RequestBody CarouselDo carouselDo, String imgSource) throws ApiBizException {
        System.err.println("imgSource: " + imgSource);
        Integer result = carouselService.insertCarousel(carouselDo);
        return this.normalResp(result);
    }

    /**
     * 删除轮播项(批量删除)
     *
     * @param servletRequest
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/deleted", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String deletedCarousel(HttpServletRequest servletRequest, @RequestBody String[] array) throws ApiBizException {
        String result = carouselService.deletedCarousel(array);
        return this.normalResp(result);
    }

    /**
     * 修改轮播项
     *
     * @param servletRequest
     * @param carouselDo
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCarousel(HttpServletRequest servletRequest, @RequestBody CarouselDo carouselDo) throws ApiBizException {
        SyValidationUtils.valid()
                .len(carouselDo.getTitle(), 255, true, "轮播标题格式错误")
                .len(carouselDo.getUrl(), 255, true, "轮播标题格式错误");
        Integer result = carouselService.updateCarousel(carouselDo);
        return this.normalResp(result);
    }

    /**
     * 获取轮播列表
     *
     * @param servletRequest
     * @param map
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String selectCarouselList(HttpServletRequest servletRequest, @RequestBody Map map) throws ApiBizException {
        SyMap params = new SyMap(map);
        SyValidationUtils.valid()
                .isInt(params.getInteger("pageNum"), 11, true, "页码格式错误")
                .isInt(params.getInteger("pageSize"), 11, true, "每页大小格式错误");
        PageInfo result = carouselService.selectCarouselList(params);
        return this.normalRespPage(result);
    }

}
