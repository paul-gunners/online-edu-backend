package com.hsbc.cmb.connect.staservice.service;

import com.hsbc.cmb.connect.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Paul
 * @since 2022-10-23
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    //图表显示，返回两部分数据，日期json数组，数量json数组
    Map<String, Object> getShowData(String type, String begin, String end);
}
