package com.hsbc.cmb.connect.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsbc.cmb.connect.eduservice.entity.EduSubject;
import com.hsbc.cmb.connect.eduservice.entity.subject.PrimarySubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Paul
 * @since 2022-08-30
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<PrimarySubject> getSubjectCategory();
}
