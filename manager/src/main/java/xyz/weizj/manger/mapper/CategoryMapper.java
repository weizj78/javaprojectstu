package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    public abstract List<Category> selectByParentId(Long parentId);
    public abstract int countByParentId(Long id);

    public abstract List<Category> selectAll();
}