package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.order.OrderStatisticsDto;
import xyz.weizj.model.entity.order.OrderStatistics;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {

    public abstract void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);

}
