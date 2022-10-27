package com.hsbc.cmb.connect.eduservice.mapper;

import com.hsbc.cmb.connect.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.cmb.connect.eduservice.entity.frontvo.CourseWebVo;
import com.hsbc.cmb.connect.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
