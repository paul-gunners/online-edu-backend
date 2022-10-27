package com.hsbc.cmb.connect.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsbc.cmb.connect.eduservice.entity.EduChapter;
import com.hsbc.cmb.connect.eduservice.entity.chapter.CourseChapters;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
public interface EduChapterService extends IService<EduChapter> {

    List<CourseChapters> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
