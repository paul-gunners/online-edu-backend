package com.hsbc.cmb.connect.eduservice.controller;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
  //解决跨域
public class EduLoginController {

    //login
    @PostMapping("login")
    public ResponseEntity login() {
        return ResponseEntity.success().data("token","admin");
    }

    //info
    @GetMapping("info")
    public ResponseEntity info() {
        return ResponseEntity.success().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //logout
    @PostMapping("logout")
    public ResponseEntity logout() {
        return ResponseEntity.success();
    }
}
