package com.zjy.paymentdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.paymentdemo.entity.OrderInfo;

public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createOrderByProductId(Long productId);
}
