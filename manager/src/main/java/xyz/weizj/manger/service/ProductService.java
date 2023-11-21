package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.product.ProductDto;
import xyz.weizj.model.entity.product.Product;

public interface ProductService {
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);
    public void save(Product product);

    public Product getById(Long id);

    public void updateById(Product product);

    public void deleteById(Long id);

    public void updateAuditStatus(Long id, Integer auditStatus);

    public void updateStatus(Long id, Integer status);
}
