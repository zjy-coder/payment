package com.zjy.paymentdemo.controller;

import com.zjy.paymentdemo.common.Res;
import com.zjy.paymentdemo.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@Api(tags = "订单接口")
@RequestMapping("/api/order-info")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "订单列表")
    @GetMapping("/list")
    public Res list(){
        return Res.success(orderInfoService.list());
    }
}
