package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecService {
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit);
    public void save(ProductSpec productSpec);

    public void updateById(ProductSpec productSpec);

    public void deleteById(Long id);

    public List<ProductSpec> findAll();
}
