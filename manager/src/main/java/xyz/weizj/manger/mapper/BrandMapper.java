package xyz.weizj.manger.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.Brand;

import java.util.List;

@Mapper
public interface BrandMapper {
    public abstract List<Brand> findByPage();

    public abstract void save(Brand brand);

    public abstract void updateById(Brand brand);

    public abstract void deleteById(Long id);

    public abstract List<Brand> findAll();


}
