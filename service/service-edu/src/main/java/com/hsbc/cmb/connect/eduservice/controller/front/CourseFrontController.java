package com.hsbc.cmb.connect.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.cmb.connect.commonutils.JwtUtils;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.commonutils.ordervo.CourseWebVoOrder;
import com.hsbc.cmb.connect.eduservice.client.OrdersClient;
import com.hsbc.cmb.connect.eduservice.entity.EduCourse;
import com.hsbc.cmb.connect.eduservice.entity.chapter.CourseChapters;
import com.hsbc.cmb.connect.eduservice.entity.frontvo.CourseFrontVo;
import com.hsbc.cmb.connect.eduservice.entity.frontvo.CourseWebVo;
import com.hsbc.cmb.connect.eduservice.service.EduChapterService;
import com.hsbc.cmb.connect.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")

public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public ResponseEntity getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return ResponseEntity.success().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public ResponseEntity getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<CourseChapters> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = false;
        if (!StringUtils.isEmpty(userId)) {
            buyCourse = ordersClient.isBuyCourse(courseId, userId);
        }

        return ResponseEntity.success().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}