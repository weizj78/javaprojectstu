package xyz.weizj.feign.product;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.weizj.model.entity.product.ProductSku;

@FeignClient(value = "service-product")
public interface ProductFeignClient {
    @GetMapping("/api/product/getBySkuId/{skuId}")
    public abstract ProductSku getBySkuId(@PathVariable Long skuId);
}
