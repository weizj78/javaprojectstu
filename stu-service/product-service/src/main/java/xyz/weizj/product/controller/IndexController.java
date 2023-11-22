package xyz.weizj.product.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.weizj.model.entity.product.Category;
import xyz.weizj.model.entity.product.ProductSku;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;
import xyz.weizj.model.vo.h5.IndexVo;
import xyz.weizj.product.service.CategoryService;
import xyz.weizj.product.service.ProductService;

import java.util.List;

@Tag(name = "首页接口管理")
@RestController
//@CrossOrigin
@RequestMapping("/api/product/index")
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData(){
        List<Category> oneCategory = categoryService.findOneCategory();
        List<ProductSku> productSkuBySale = productService.findProductSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(oneCategory);
        indexVo.setProductSkuList(productSkuBySale);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
