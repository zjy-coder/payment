package com.zjy.paymentdemo.controller;

import com.zjy.paymentdemo.common.Res;
import com.zjy.paymentdemo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@Api(tags = "测试模块")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @ApiOperation(value = "test测试借口")
    @GetMapping("/test")
    public Res test(){
        return Res.success();
    }

    @ApiOperation(value = "列表接口")
    @GetMapping("list")
    public Res list(){
        return Res.success(productService.list());
    }
}
