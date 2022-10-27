package com.hsbc.cmb.connect.eduservice.entity.chapter;

import lombok.Data;

@Data
public class ChapterVideos {

    private String id;

    private String title;

    private String videoSourceId;   //视频id

    private String isFree;   //是否免费

}
