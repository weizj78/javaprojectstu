package xyz.weizj.product.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.weizj.model.entity.product.Category;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.product.service.CategoryService;

import java.util.List;

@Tag(name = "分类接口管理")
@RestController
@RequestMapping("/api/product/category")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<List<Category>> findCategoryTree(){
        List<Category> list = categoryService.findOneCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
