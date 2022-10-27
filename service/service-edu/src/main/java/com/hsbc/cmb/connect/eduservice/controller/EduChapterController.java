package com.hsbc.cmb.connect.eduservice.controller;


import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.entity.EduChapter;
import com.hsbc.cmb.connect.eduservice.entity.chapter.CourseChapters;
import com.hsbc.cmb.connect.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")

public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表,根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public ResponseEntity getChapterVideo(@PathVariable String courseId) {

        List<CourseChapters> list = chapterService.getChapterVideoByCourseId(courseId);
        return ResponseEntity.success().data("allChapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public ResponseEntity addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return ResponseEntity.success();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public ResponseEntity getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return ResponseEntity.success().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public ResponseEntity updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return ResponseEntity.success();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public ResponseEntity deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return ResponseEntity.success();
        } else {
            return ResponseEntity.fail();
        }

    }
}

