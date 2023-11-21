package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.base.ProductUnit;

import java.util.List;

@Mapper
public interface ProductUnitMapper {
    public abstract List<ProductUnit> findAll();
}