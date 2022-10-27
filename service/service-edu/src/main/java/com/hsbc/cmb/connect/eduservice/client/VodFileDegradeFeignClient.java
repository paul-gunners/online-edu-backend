package com.hsbc.cmb.connect.eduservice.client;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{
//出错后执行
    @Override
    public ResponseEntity removeAlyVideo(String videoId) {
        return ResponseEntity.fail().message("删除视频出错了>_<");
    }

    @Override
    public ResponseEntity deleteBatch(List videoIdList) {
        return ResponseEntity.fail().message("删除多个视频出错了~_~");
    }
}
