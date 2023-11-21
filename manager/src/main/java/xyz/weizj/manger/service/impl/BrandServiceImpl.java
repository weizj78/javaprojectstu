package xyz.weizj.manger.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.mapper.BrandMapper;
import xyz.weizj.manger.service.BrandService;
import xyz.weizj.model.entity.product.Brand;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper ;

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage() ;
        return new PageInfo(brandList);
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand) ;
    }

    // com.atguigu.spzx.manager.service.impl;
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand) ;
    }

    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id) ;
    }

    // com.atguigu.spzx.manager.service.impl;
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
