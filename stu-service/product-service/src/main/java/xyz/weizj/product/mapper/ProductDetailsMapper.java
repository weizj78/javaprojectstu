package xyz.weizj.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.ProductDetails;

@Mapper
public interface ProductDetailsMapper {

    ProductDetails getByProductId(Long productId);

}
