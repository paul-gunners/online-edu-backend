package com.hsbc.cmb.connect.eduorder.controller;


import com.hsbc.cmb.connect.commonutils.ResponseEntity;
import com.hsbc.cmb.connect.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Paul
 * @since 2022-10-18
 */
@RestController
@RequestMapping("/eduorder/paylog")

public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    //生成微信支付二维码接口
    //参数是订单号
    @GetMapping("createNative/{orderNo}")
    public ResponseEntity createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNatvie(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return ResponseEntity.success().data(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public ResponseEntity queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return ResponseEntity.fail().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return ResponseEntity.success().message("支付成功");
        }
        return ResponseEntity.success().code(25000).message("支付中");

    }
}

