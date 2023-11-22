package xyz.weizj.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.weizj.model.entity.product.Brand;
import xyz.weizj.product.mapper.BrandMapper;
import xyz.weizj.product.service.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Cacheable(value = "brandList", unless="#result.size() == 0")
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }

}
