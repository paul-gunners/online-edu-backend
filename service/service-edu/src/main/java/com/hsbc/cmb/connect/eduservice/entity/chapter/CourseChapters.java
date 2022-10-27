package com.hsbc.cmb.connect.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseChapters {

    private String id;

    private String title;

    //表示小节
    private List<ChapterVideos> children = new ArrayList<>();
}
