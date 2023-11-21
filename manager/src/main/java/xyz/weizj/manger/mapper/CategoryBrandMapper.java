package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.dto.product.CategoryBrandDto;
import xyz.weizj.model.entity.product.Brand;
import xyz.weizj.model.entity.product.CategoryBrand;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    public abstract List<CategoryBrand> findByPage(CategoryBrandDto CategoryBrandDto);

    public abstract void save(CategoryBrand categoryBrand);

    public abstract void updateById(CategoryBrand categoryBrand);

    public abstract void deleteById(Long id);

    public abstract List<Brand> findBrandByCategoryId(Long categoryId);
}