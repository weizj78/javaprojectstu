package xyz.weizj.product.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.dto.h5.ProductSkuDto;
import xyz.weizj.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {

    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);
}
