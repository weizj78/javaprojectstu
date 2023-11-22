package xyz.weizj.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.Product;

@Mapper
public interface ProductMapper {

    Product getById(Long id);
}