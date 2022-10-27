package com.hsbc.cmb.connect.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEmptyException extends RuntimeException{

    private Integer code;

    private String msg;
}
