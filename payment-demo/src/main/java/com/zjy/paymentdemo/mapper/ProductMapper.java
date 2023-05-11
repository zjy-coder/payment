package com.zjy.paymentdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjy.paymentdemo.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
