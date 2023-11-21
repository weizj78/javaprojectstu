package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.product.ProductDto;
import xyz.weizj.model.entity.product.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    public abstract List<Product> findByPage(ProductDto productDto);

    public abstract void save(Product product);

    public abstract Product selectById(Long id);

    public abstract  void updateById(Product product);

    public abstract void deleteById(Long id);
}