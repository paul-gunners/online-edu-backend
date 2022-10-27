package com.hsbc.cmb.connect.eduorder.service;

import com.hsbc.cmb.connect.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Paul
 * @since 2022-10-18
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberIdByJwtToken);
}
