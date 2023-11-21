package xyz.weizj.manger.service.impl;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.product.ProductDto;
import xyz.weizj.model.entity.product.Product;

public interface ProductService {
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);
}
