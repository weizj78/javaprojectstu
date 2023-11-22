package xyz.weizj.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.weizj.model.entity.product.Brand;

import java.util.List;

@Mapper
public interface BrandMapper {

    List<Brand> findAll();

}
