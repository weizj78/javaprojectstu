package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.ProductSku;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    public abstract void save(ProductSku productSku);

    public abstract List<ProductSku> selectByProductId(Long id);

    public abstract  void updateById(ProductSku productSku);

    public abstract void deleteByProductId(Long id);
}
