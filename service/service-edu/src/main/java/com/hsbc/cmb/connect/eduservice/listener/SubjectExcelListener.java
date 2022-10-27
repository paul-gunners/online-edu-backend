package com.hsbc.cmb.connect.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsbc.cmb.connect.eduservice.entity.EduSubject;
import com.hsbc.cmb.connect.eduservice.entity.excel.ExcelSubjectData;
import com.hsbc.cmb.connect.eduservice.service.EduSubjectService;
import com.hsbc.cmb.connect.servicebase.exception.FileEmptyException;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel内容，一行一行进行读取
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {

        //TODO: please optimize this exception handler
        if (excelSubjectData == null) {
            throw new FileEmptyException(20001, "文件数据为空");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        EduSubject primarySubject = this.existPrimarySubject(subjectService, excelSubjectData.getPrimarySubjectName());
        if(primarySubject == null) { //没有相同一级分类，进行添加
            primarySubject = new EduSubject();
            primarySubject.setParentId("0");
            primarySubject.setTitle(excelSubjectData.getPrimarySubjectName());//一级分类名称
            subjectService.save(primarySubject);
        }

        //获取一级分类id值
        String pid = primarySubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject secondarySubject = this.existSecondarySubject(subjectService, excelSubjectData.getSecondarySubjectName(), pid);
        if(secondarySubject == null) {
            secondarySubject = new EduSubject();
            secondarySubject.setParentId(pid);
            secondarySubject.setTitle(excelSubjectData.getSecondarySubjectName());//二级分类名称
            subjectService.save(secondarySubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existPrimarySubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existSecondarySubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
