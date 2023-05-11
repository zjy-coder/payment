package com.zjy.paymentdemo.controller;

import com.zjy.paymentdemo.common.Res;
import com.zjy.paymentdemo.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@Api(tags = "支付接口")
@RestController
@Slf4j
@RequestMapping("api/pay")
public class PayController {

    @Resource
    private PayService payService;

    @ApiOperation(value = "下单支付接口")
    @PostMapping("/trade/page/pay/{productId}")
    public Res tradePagePay(@PathVariable Long productId){
        return Res.success(payService.tradePagePay(productId));
    }

}
