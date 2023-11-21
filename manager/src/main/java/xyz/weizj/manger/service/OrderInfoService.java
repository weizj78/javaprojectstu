package xyz.weizj.manger.service;

import xyz.weizj.model.dto.order.OrderStatisticsDto;
import xyz.weizj.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
