package com.hsbc.cmb.connect.oss.controller;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")

public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping
    public ResponseEntity uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return ResponseEntity.success().data("url",url);
    }
}
