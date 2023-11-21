package xyz.weizj.manger.service;

import com.github.pagehelper.PageInfo;
import xyz.weizj.model.entity.product.Brand;

import java.util.List;

public interface BrandService {
    public PageInfo<Brand> findByPage(Integer page, Integer limit);

    public void save(Brand brand);

    public void updateById(Brand brand);

    public void deleteById(Long id);
    public List<Brand> findAll();

}
