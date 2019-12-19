package com.shytong.modules.fileupload.service.impl;

import com.shytong.modules.fileupload.service.IFileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: CL
 * @Date: 2019/12/6 13:30
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {
    @Override
    public String handleFileUpLoad(MultipartFile file) throws FileNotFoundException {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println("上传的文件名为：" + fileName);
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            System.out.println("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String projectPath = ResourceUtils.getURL("classpath:").getPath();
            String packagePath = "static/upfiles/ueditor/";
            String path = projectPath + packagePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
