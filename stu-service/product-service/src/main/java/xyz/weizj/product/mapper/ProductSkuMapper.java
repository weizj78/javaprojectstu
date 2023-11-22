package xyz.weizj.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.h5.ProductSkuDto;
import xyz.weizj.model.entity.product.ProductSku;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    ProductSku getById(Long id);

    List<ProductSku> findByProductId(Long productId);
}
