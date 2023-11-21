package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.ProductDetails;

@Mapper
public interface ProductDetailsMapper {
    public abstract void save(ProductDetails productDetails);

    public abstract ProductDetails selectByProductId(Long id);

    public abstract void updateById(ProductDetails productDetails);

    public abstract void deleteByProductId(Long id);
}
