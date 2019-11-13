package com.shytong.modules.carouselconfig.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.modules.carouselconfig.model.CarouselConfigDo;
import com.shytong.modules.carouselconfig.service.ICarouselConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Author CL
 * @Description 轮播配置控制类
 * @Date 2019/11/13
 */
@RestController
@RequestMapping(value = "/api/carousel/config", produces = "application/json;charset=utf-8", consumes = "application/json")
public class CarouselConfigController extends BaseController {

    @Autowired
    private ICarouselConfigService carouselConfigService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCarouselConfig(HttpServletRequest servletRequest, @RequestBody CarouselConfigDo carouselConfigDo) throws ApiBizException {
        // 参数暂未校验
        Integer result = carouselConfigService.addCarouselConfig(carouselConfigDo);
        return this.normalResp(result);
    }

    @RequestMapping(value = "/deleted", method = RequestMethod.GET)
    public String deletedCarouselConfig(HttpServletRequest servletRequest, @RequestParam Map map) throws ApiBizException {
        // 参数暂未校验
        SyMap params = new SyMap(map);
        Integer result = carouselConfigService.deletedCarouselConfig(params);
        return this.normalResp(result);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateCarouselConfig(HttpServletRequest servletRequest, @RequestBody CarouselConfigDo carouselConfigDo) throws ApiBizException {
        // 参数暂未校验
        Integer result = carouselConfigService.updateCarouselConfig(carouselConfigDo);
        return this.normalResp();
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public String selectCarouselConfig(HttpServletRequest servletRequest, @RequestBody Map map) throws ApiBizException {
        // 参数暂未校验
        SyMap params = new SyMap(map);
        PageInfo result = carouselConfigService.selectCarouselConfig(params);
        return this.normalResp(result);
    }

}
