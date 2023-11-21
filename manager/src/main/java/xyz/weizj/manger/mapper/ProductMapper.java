package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.product.ProductDto;
import xyz.weizj.model.entity.product.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    public abstract List<Product> findByPage(ProductDto productDto);
}