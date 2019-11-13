package com.shytong.modules.resourceLib.controller;

import com.github.pagehelper.PageInfo;
import com.shytong.common.exception.ApiBizException;
import com.shytong.common.model.SyMap;
import com.shytong.common.web.BaseController;
import com.shytong.core.util.SyJsonUtil;
import com.shytong.core.util.SyValidationUtils;
import com.shytong.modules.resourceLib.model.UpLoadFile;
import com.shytong.modules.resourceLib.service.ResourceLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther: HC
 * @Date: 2018/10/15 15:18
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/resourceLib", produces = "application/json;charset=UTF-8;", consumes = "application/json;")
public class ResourceLibController extends BaseController {

    @Autowired
    private ResourceLibService resourceLibService;

    /**
     * 资源库列表
     *
     * @param servletRequest
     * @param params
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getList(HttpServletRequest servletRequest, @RequestBody SyMap params) throws ApiBizException {
        //参数校验
        SyValidationUtils.valid().isInt(params.get("pageNum"), true, "页码格式错误")
                .isInt(params.get("pageSize"), true, "每页条数格式错误");

        PageInfo list = resourceLibService.getList(params, params.getInteger("pageNum"), params.getInteger("pageSize"));
        System.out.println(list.toString());
        return this.normalRespPage(list);
    }

    /**
     * 资源详情
     *
     * @param servletRequest
     * @param queryParamMap
     * @return
     * @throws ApiBizException
     */
    @RequestMapping(value = "/detail", consumes = "*", method = RequestMethod.GET)
    public String detail(HttpServletRequest servletRequest, @RequestParam Map queryParamMap) throws ApiBizException {
        SyMap queryParams = new SyMap(queryParamMap);
        SyValidationUtils.valid()
                .len(queryParams.get("id"), 32, false, "id格式错误");
        Object object = resourceLibService.detail(queryParamMap);
        return this.normalResp(object);
    }

    /**
     * ueditlor上传图片
     * @param servletRequest
     * @param upfile
     * @return
     * @throws ApiBizException
     * @throws IOException
     */
    @RequestMapping(value = "/euUpLoad", consumes = "*", method = RequestMethod.POST)
    public String uploadImg(HttpServletRequest servletRequest, MultipartFile upfile) throws ApiBizException, IOException {
        String filePath = resourceLibService.addFile(upfile,"img");
        SyMap syMap = new SyMap();
        syMap.put("url",filePath);
        syMap.put("state","SUCCESS");
        syMap.put("original","");
        return SyJsonUtil.Object2Json(syMap);
    }

    @RequestMapping(value = "/uploadFile", consumes = "*", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest servletRequest, MultipartFile upfile) throws ApiBizException, IOException {
        String filePath = resourceLibService.addFile(upfile,"file");
        SyMap syMap = new SyMap();
        syMap.put("url",filePath);
        syMap.put("state","SUCCESS");
        syMap.put("original","");
        return SyJsonUtil.Object2Json(syMap);
    }

    /**
     * 上传文件
     * @param servletRequest
     * @param file
     * @return
     * @throws ApiBizException
     * @throws IOException
     */
    @RequestMapping(value = "/upload", consumes = "*", method = RequestMethod.POST)
    public String upload(HttpServletRequest servletRequest, MultipartFile file) throws ApiBizException, IOException {
        String filePath = resourceLibService.addFile(file,"other");
        return this.normalResp(filePath);
    }


    @RequestMapping(value = "/editUpLoadImg", consumes = "*", method = RequestMethod.POST)
    public String editUpLoadImg(HttpServletRequest servletRequest, MultipartFile file) throws ApiBizException, IOException {
        String o = resourceLibService.addFile(file,"img");
        UpLoadFile upLoadFile = new UpLoadFile();
        upLoadFile.setSrc(o);
        return this.normalRespPage(upLoadFile);
    }

}
