package com.hsbc.cmb.connect.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.entity.EduCourse;
import com.hsbc.cmb.connect.eduservice.entity.vo.CourseInfoVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CoursePublishVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CourseQuery;
import com.hsbc.cmb.connect.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/eduservice/course")

public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public ResponseEntity addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return ResponseEntity.success().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public ResponseEntity getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return ResponseEntity.success().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public ResponseEntity updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return ResponseEntity.success();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public ResponseEntity getPublishCourseInfo(@PathVariable String id) {

        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return ResponseEntity.success().data("publishCourse", coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public ResponseEntity publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return ResponseEntity.success();
    }

    //课程列表 基本实现
    @PostMapping("pageCourseCondition/{page}/{limit}")
    public ResponseEntity getCourseList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody CourseQuery courseQuery
    ) {

        Page<EduCourse> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  ResponseEntity.success().data("total", total).data("rows", records);
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public ResponseEntity deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return ResponseEntity.success();
    }
}

