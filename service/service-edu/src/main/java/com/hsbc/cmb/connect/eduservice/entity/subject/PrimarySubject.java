package com.hsbc.cmb.connect.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

@Data
public class PrimarySubject {

    private String id;

    private String title;

    private List<SecondarySubject> children;
}
