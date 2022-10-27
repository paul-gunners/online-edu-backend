package com.hsbc.cmb.connect.educenter.controller;


import com.hsbc.cmb.connect.commonutils.JwtUtils;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.commonutils.ordervo.UcenterMemberOrder;
import com.hsbc.cmb.connect.educenter.entity.UcenterMember;
import com.hsbc.cmb.connect.educenter.entity.vo.RegisterVo;
import com.hsbc.cmb.connect.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-10-04
 */
@RestController
@RequestMapping("/educenter/member")

public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public ResponseEntity loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return ResponseEntity.success().data("token",token);
    }

    //注册
    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return ResponseEntity.success();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public ResponseEntity getMemberInfo(HttpServletRequest request) {

        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return ResponseEntity.success().data("userInfo",member);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public ResponseEntity countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return ResponseEntity.success().data("countRegister",count);
    }
}

