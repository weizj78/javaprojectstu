package xyz.weizj.manger.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.mapper.CategoryBrandMapper;
import xyz.weizj.manger.service.CategoryBrandService;
import xyz.weizj.model.dto.product.CategoryBrandDto;
import xyz.weizj.model.entity.product.Brand;
import xyz.weizj.model.entity.product.CategoryBrand;

import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper ;

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto CategoryBrandDto) {
        PageHelper.startPage(page , limit) ;
        List<CategoryBrand> categoryBrandList = categoryBrandMapper.findByPage(CategoryBrandDto) ;
        return new PageInfo<>(categoryBrandList);
    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand) ;
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand) ;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }

}
