package com.hsbc.cmb.connect.eduservice.client;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAliyVideo/{id}")
    public ResponseEntity removeAlyVideo(@PathVariable("id") String id);

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public ResponseEntity deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
