package com.hsbc.cmb.connect.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hsbc.cmb.connect.eduservice.entity.EduTeacher;
import com.hsbc.cmb.connect.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-08-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) throws Exception;

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
