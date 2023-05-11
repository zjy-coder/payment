package com.zjy.paymentdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.zjy.paymentdemo.config.AlipayConfig;
import com.zjy.paymentdemo.entity.OrderInfo;
import com.zjy.paymentdemo.service.OrderInfoService;
import com.zjy.paymentdemo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Resource
    private AlipayClient alipayClient;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private AlipayConfig alipayConfig;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String tradePagePay(Long productId) {
        try {
            //下单,创建订单
            OrderInfo orderInfo = orderInfoService.createOrderByProductId(productId);

            AlipayTradePagePayRequest alipayTradePayRequest = new AlipayTradePagePayRequest();
            alipayTradePayRequest.setReturnUrl(alipayConfig.getReturnUrl());
//            alipayTradePayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no",orderInfo.getOrderNo());
            BigDecimal totalAmount = new BigDecimal(orderInfo.getTotalFee().toString()).divide(new BigDecimal("100"));
            bizContent.put("total_amount",totalAmount);
            bizContent.put("subject",orderInfo.getTitle());
            bizContent.put("product_code","FAST_INSTANT_TRADE_PAY");
            alipayTradePayRequest.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse payResponse = null;
            payResponse = alipayClient.pageExecute(alipayTradePayRequest);
            if(payResponse.isSuccess()){
                log.info("调用成功：{}",payResponse.getBody());
            }else{
                log.info("调用失败：{}",payResponse.getMsg());
                throw new RuntimeException("创建支付交易失败");
            }
            return payResponse.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException("创建支付交易失败");
        }
    }
}
