package xyz.weizj.manger.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import xyz.weizj.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findByParentId(Long parentId);

    public abstract void exportData(HttpServletResponse response);

    public abstract void importData(MultipartFile file);
}
