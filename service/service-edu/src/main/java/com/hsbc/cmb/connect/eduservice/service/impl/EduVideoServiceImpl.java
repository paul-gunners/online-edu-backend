package com.hsbc.cmb.connect.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.client.VodClient;
import com.hsbc.cmb.connect.eduservice.entity.EduVideo;
import com.hsbc.cmb.connect.eduservice.mapper.EduVideoMapper;
import com.hsbc.cmb.connect.eduservice.service.EduVideoService;
import com.hsbc.cmb.connect.servicebase.exception.FileEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {

        //1 根据课程id查询课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);

        // List<EduVideo>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }

        //根据多个视频id删除多个视频
        if(videoIds.size()>0) {
            ResponseEntity result = vodClient.deleteBatch(videoIds);
            if(result.getCode() == 20001) {
                throw new FileEmptyException(20001,result.getMessage());
            }
        }

        // 先删视频再删小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
