package com.hsbc.cmb.connect.eduservice.controller;


import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.entity.subject.PrimarySubject;
import com.hsbc.cmb.connect.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/subject")

public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来文件，把文件内容读取出来
    @PostMapping("addSubject")
    public ResponseEntity addSubject(MultipartFile file) {

        //上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return ResponseEntity.success();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public ResponseEntity getAllSubject() {
        //list集合泛型是一级分类
        List<PrimarySubject> list = subjectService.getSubjectCategory();
        return ResponseEntity.success().data("list", list);
    }
}

