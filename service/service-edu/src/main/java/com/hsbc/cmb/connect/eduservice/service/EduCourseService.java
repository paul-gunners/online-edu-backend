package com.hsbc.cmb.connect.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.cmb.connect.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsbc.cmb.connect.eduservice.entity.frontvo.CourseFrontVo;
import com.hsbc.cmb.connect.eduservice.entity.frontvo.CourseWebVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CourseInfoVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CoursePublishVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CourseQuery;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
