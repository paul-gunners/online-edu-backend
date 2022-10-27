package com.hsbc.cmb.connect.eduservice.controller;


import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduservice.client.VodClient;
import com.hsbc.cmb.connect.eduservice.entity.EduVideo;
import com.hsbc.cmb.connect.eduservice.service.EduVideoService;
import com.hsbc.cmb.connect.servicebase.exception.FileEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-09-03
 */
@RestController
@RequestMapping("/eduservice/video")

public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public ResponseEntity addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return ResponseEntity.success();
    }

    //删除小节
    @DeleteMapping("{id}")
    public ResponseEntity deleteVideo(@PathVariable String id) {

        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        // 小节里有视频Id就远程调用删除视频
        if (!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            ResponseEntity result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new FileEmptyException(20001,result.getMessage());
            }
        }
        // 删除数据库中的小节
        videoService.removeById(id);
        return ResponseEntity.success();
    }

    //修改章节
    @PostMapping("updateVideo")
    public ResponseEntity updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return ResponseEntity.success();
    }

    //根据章节id查询
    @GetMapping("getVideoInfo/{videoId}")
    public ResponseEntity getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        return ResponseEntity.success().data("video",eduVideo);
    }
}

