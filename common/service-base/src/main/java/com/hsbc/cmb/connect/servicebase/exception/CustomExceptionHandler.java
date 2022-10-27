package com.hsbc.cmb.connect.servicebase.exception;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity error(Exception e){

        e.printStackTrace();
        return ResponseEntity.fail().message("全局异常处理");
    }

    @ExceptionHandler(GustavoException.class)
    @ResponseBody
    public ResponseEntity error(GustavoException e){

        e.printStackTrace();
        return ResponseEntity.fail().message(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(FileEmptyException.class)
    @ResponseBody
    public ResponseEntity error(FileEmptyException e){

        e.printStackTrace();
        return ResponseEntity.fail().message(e.getMsg()).code(e.getCode());
    }
}
