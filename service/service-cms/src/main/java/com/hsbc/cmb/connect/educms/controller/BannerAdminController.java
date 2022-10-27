package com.hsbc.cmb.connect.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.educms.entity.CrmBanner;
import com.hsbc.cmb.connect.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-09-25
 */
@RestController
@RequestMapping("/educms/banneradmin")

public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public ResponseEntity pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return ResponseEntity.success().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    //2 添加banner
    @PostMapping("addBanner")
    public ResponseEntity addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return ResponseEntity.success();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return ResponseEntity.success().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResponseEntity updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return ResponseEntity.success();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseEntity remove(@PathVariable String id) {
        bannerService.removeById(id);
        return ResponseEntity.success();
    }
}

