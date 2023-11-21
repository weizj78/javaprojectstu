package xyz.weizj.manger.service;

import jakarta.servlet.http.HttpServletResponse;
import xyz.weizj.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findByParentId(Long parentId);

    public abstract void exportData(HttpServletResponse response);
}
