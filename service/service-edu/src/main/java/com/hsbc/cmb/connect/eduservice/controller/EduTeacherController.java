package com.hsbc.cmb.connect.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.entity.EduTeacher;
import com.hsbc.cmb.connect.eduservice.entity.vo.TeacherQuery;
import com.hsbc.cmb.connect.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Paul Q D Li
 * @since 2022-08-12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
  //解决跨域
public class EduTeacherController {

    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ResponseEntity findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return ResponseEntity.success().data("items", list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public ResponseEntity removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {

        return teacherService.removeById(id) ? ResponseEntity.success() : ResponseEntity.fail();
    }

    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
/*    @GetMapping("pageTeacher/{current}/{limit}")
    public ResponseEntity pageListTeacher(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return ResponseEntity.success().data("total",total).data("rows",records);
    }*/

    @ApiOperation(value = "分页条件查询讲师列表")
    @PostMapping("pageTeacherCondition/{page}/{limit}")
    public ResponseEntity pageQuery(

        @ApiParam(name = "page", value = "当前页码", required = true)
        @PathVariable Long page,

        @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit,

        @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
        @RequestBody TeacherQuery teacherQuery) throws Exception {

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  ResponseEntity.success().data("total", total).data("rows", records);
    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public ResponseEntity addTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return ResponseEntity.success();
        } else {
            return ResponseEntity.fail();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public ResponseEntity getById(
        @ApiParam(name = "id", value = "讲师ID", required = true)
        @PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);
        return ResponseEntity.success().data("item", teacher);
    }

/*    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public ResponseEntity updateById(
        @ApiParam(name = "id", value = "讲师ID", required = true)
        @PathVariable String id,

        @ApiParam(name = "teacher", value = "讲师对象", required = true)
        @RequestBody EduTeacher teacher){

        teacher.setId(id);
        boolean flag = teacherService.updateById(teacher);
        return flag ? ResponseEntity.success() : ResponseEntity.fail();
    }*/

    //讲师修改功能
    @PostMapping("updateTeacher")
    public ResponseEntity updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return ResponseEntity.success();
        } else {
            return ResponseEntity.fail();
        }
    }
}

