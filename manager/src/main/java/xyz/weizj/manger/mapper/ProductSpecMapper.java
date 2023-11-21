package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.ProductSpec;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    public abstract List<ProductSpec> findByPage();
    public abstract void save(ProductSpec productSpec);
    public abstract void updateById(ProductSpec productSpec);
    public abstract void deleteById(Long id);
    public abstract List<ProductSpec> findAll();
}
