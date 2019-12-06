package com.shytong.modules.fileupload.controller;

import com.shytong.common.web.BaseController;
import com.shytong.modules.fileupload.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @Description: 文件上传控制类
 * @Author: CL
 * @Date: 2019/12/6 13:39
 */
@RequestMapping("/api/file")
@RestController
public class FileUploadController extends BaseController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping("/upload")
    public String handleFileUpLoad(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String result = fileUploadService.handleFileUpLoad(file);
        return this.normalResp(result);
    }
}