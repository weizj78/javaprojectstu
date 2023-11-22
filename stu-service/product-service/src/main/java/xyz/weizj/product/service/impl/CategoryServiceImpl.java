package xyz.weizj.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.weizj.model.entity.product.Category;
import xyz.weizj.product.mapper.CategoryMapper;
import xyz.weizj.product.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }

    @Override
    public List<Category> findOneCategoryTree() {

        List<Category> allCategoryList = categoryMapper.findAll();
        // 获取所有1级分类
        List<Category> oneCategoryList = allCategoryList.stream()
                .filter(category -> category.getParentId().longValue() == 0).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(oneCategoryList)){
            oneCategoryList.forEach(category -> {
                List<Category> twoCategoryList = allCategoryList.stream().
                        filter(tempCategory ->
                                tempCategory.getParentId().longValue() == category.getId().longValue())
                        .collect(Collectors.toList());
                category.setChildren(twoCategoryList);

                if (!CollectionUtils.isEmpty(twoCategoryList)){
                    twoCategoryList.forEach(twoCategory->{
                        List<Category> threeCategoryList = allCategoryList.stream().
                                filter(tempCategory -> tempCategory.getParentId().longValue() == twoCategory.getId().longValue())
                                .collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        return oneCategoryList;
    }
}
