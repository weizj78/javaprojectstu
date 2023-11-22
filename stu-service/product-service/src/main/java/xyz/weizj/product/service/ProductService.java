package xyz.weizj.product.service;

import xyz.weizj.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {

    List<ProductSku> findProductSkuBySale();
}
