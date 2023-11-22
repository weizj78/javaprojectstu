package xyz.weizj.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    List<Category> findAll();
}
