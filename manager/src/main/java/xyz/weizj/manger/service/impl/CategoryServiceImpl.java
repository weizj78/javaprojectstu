package xyz.weizj.manger.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.weizj.manger.listener.ExcelListener;
import xyz.weizj.manger.mapper.CategoryMapper;
import xyz.weizj.manger.service.CategoryService;
import xyz.weizj.model.entity.product.Category;
import xyz.weizj.model.vo.product.CategoryExcelVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper ;

    @Override
    public List<Category> findByParentId(Long parentId) {

        // 根据分类id查询它下面的所有的子分类数据
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)) {

            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {

                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }

            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("分类数据","UTF-8");
            response.setHeader("Content-disposition","attachment;filename=" + fileName + ".xlsx");

            List<Category> categoryList = categoryMapper.selectAll();

            List<CategoryExcelVo> categoryExcelVoList = categoryList.stream().map(category -> {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                return categoryExcelVo;
            }).toList();
            EasyExcel.write(response.getOutputStream(),CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importData(MultipartFile file) {
        ExcelListener<CategoryExcelVo> excelVoExcelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelVoExcelListener).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
