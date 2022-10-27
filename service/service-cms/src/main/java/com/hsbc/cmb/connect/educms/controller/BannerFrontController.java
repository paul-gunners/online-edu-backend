package com.hsbc.cmb.connect.educms.controller;

import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.educms.entity.CrmBanner;
import com.hsbc.cmb.connect.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/educms/bannerfront")

public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public ResponseEntity getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return ResponseEntity.success().data("list",list);
    }
}
