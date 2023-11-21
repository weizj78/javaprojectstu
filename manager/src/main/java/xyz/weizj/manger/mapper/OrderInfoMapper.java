package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.order.OrderStatistics;

@Mapper
public interface OrderInfoMapper {

    // 查询指定日期产生的订单数据
    public abstract OrderStatistics selectOrderStatistics(String creatTime);
}
