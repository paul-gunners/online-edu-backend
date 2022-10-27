package com.hsbc.cmb.connect.staservice.client;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public ResponseEntity countRegister(@PathVariable("day") String day);
}
