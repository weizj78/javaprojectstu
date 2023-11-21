package xyz.weizj.manger.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.weizj.manger.service.CategoryService;
import xyz.weizj.model.entity.product.Category;
import xyz.weizj.model.vo.common.Result;
import xyz.weizj.model.vo.common.ResultCodeEnum;

import java.util.List;

@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "根据parentId获取下级节点")
    @GetMapping(value = "/findByParentId/{parentId}")
    public Result<List<Category>> findByParentId(@PathVariable Long parentId) {
        List<Category> list = categoryService.findByParentId(parentId);
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }


    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response){
        categoryService.exportData(response);
    }
    @PostMapping("/importDara")
    public Result importData(MultipartFile file){
        categoryService.importData(file);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}