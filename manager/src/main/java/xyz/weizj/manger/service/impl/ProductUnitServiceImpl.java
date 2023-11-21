package xyz.weizj.manger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weizj.manger.mapper.ProductUnitMapper;
import xyz.weizj.manger.service.ProductUnitService;
import xyz.weizj.model.entity.base.ProductUnit;

import java.util.List;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper ;

    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll() ;
    }
}
