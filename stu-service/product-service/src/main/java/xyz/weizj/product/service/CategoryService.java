package xyz.weizj.product.service;

import xyz.weizj.model.entity.product.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findOneCategory();

    List<Category> findOneCategoryTree();
}
