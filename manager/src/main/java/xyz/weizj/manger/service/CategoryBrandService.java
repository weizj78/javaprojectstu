package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.product.CategoryBrandDto;
import xyz.weizj.model.entity.product.Brand;
import xyz.weizj.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {



    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto CategoryBrandDto);

    public void save(CategoryBrand categoryBrand) ;

    public void updateById(CategoryBrand categoryBrand);

    public void deleteById(Long id);

    public List<Brand> findBrandByCategoryId(Long categoryId);
}
