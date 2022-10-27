package com.hsbc.cmb.connect.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsbc.cmb.connect.eduservice.entity.EduSubject;
import com.hsbc.cmb.connect.eduservice.entity.excel.ExcelSubjectData;
import com.hsbc.cmb.connect.eduservice.entity.subject.PrimarySubject;
import com.hsbc.cmb.connect.eduservice.entity.subject.SecondarySubject;
import com.hsbc.cmb.connect.eduservice.listener.SubjectExcelListener;
import com.hsbc.cmb.connect.eduservice.mapper.EduSubjectMapper;
import com.hsbc.cmb.connect.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Paul
 * @since 2022-08-30
 */

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {

        try {

                //1 获取文件输入流
                InputStream inputStream = file.getInputStream();

                // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
                EasyExcel.read(inputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<PrimarySubject> getSubjectCategory() {

        //scan all the primary subject
        QueryWrapper<EduSubject> wrapperPri = new QueryWrapper<>();
        wrapperPri.eq("parent_id", "0");
        List<EduSubject> primaryList = baseMapper.selectList(wrapperPri);

        //scan all the secondary subject
        QueryWrapper<EduSubject> wrapperSec = new QueryWrapper<>();
        wrapperSec.ne("parent_id", "0");
        List<EduSubject> secondaryList = baseMapper.selectList(wrapperSec);

        //assemble secondary subjects to primary subjects respectively
        List<PrimarySubject> finalList = new ArrayList<>();
        for (int i = 0; i < primaryList.size() ; i++) {

            PrimarySubject primarySubject = new PrimarySubject();
            BeanUtils.copyProperties(primaryList.get(i), primarySubject);
            finalList.add(primarySubject);

            List<SecondarySubject> secondaryFinalList = new ArrayList<>();
            for (int m = 0; m < secondaryList.size(); m++) {

                SecondarySubject secondarySubject = new SecondarySubject();
                if (secondaryList.get(m).getParentId().equals(primaryList.get(i).getId())) {

                    BeanUtils.copyProperties(secondaryList.get(m), secondarySubject);
                    secondaryFinalList.add(secondarySubject);
                }
            }
            finalList.get(i).setChildren(secondaryFinalList);
        }

        return finalList;
    }
}
